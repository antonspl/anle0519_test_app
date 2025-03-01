package org.anle.service;

import org.anle.dto.CustomerDto;
import org.anle.dto.CustomerUpdateDto;
import org.anle.entity.Customer;
import org.anle.mapper.CustomerMapper;
import org.anle.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.anle.mapper.CustomerMapper.mapToDto;
import static org.anle.mapper.CustomerMapper.mapToEntity;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<CustomerDto> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(CustomerMapper::mapToDto)
                .toList();
    }

    public CustomerDto getCustomerById(Long id) {
        Optional<Customer> res = customerRepository.findById(id);
        if (res.isEmpty()) {
            throw new CustomerNotFoundException("User not found with id: %d".formatted(id));
        }
        return mapToDto(res.get());
    }

    public CustomerDto saveCustomer(CustomerUpdateDto user) {
        return mapToDto(customerRepository.save(mapToEntity(user)));
    }

    public CustomerDto updateCustomer(Long id, CustomerUpdateDto dto) {
        return customerRepository.findById(id).map(existingCustomer -> {
            dto.name().ifPresent(existingCustomer::setName);
            dto.email().ifPresent(existingCustomer::setEmail);
            dto.description().ifPresent(existingCustomer::setDescription);
            return mapToDto(customerRepository.save(existingCustomer));
        }).orElseThrow(() -> new CustomerNotFoundException("User not found with id: %d".formatted(id)));
    }

    public static class CustomerNotFoundException extends RuntimeException {
        public CustomerNotFoundException(String message) {
            super(message);
        }
    }
}
