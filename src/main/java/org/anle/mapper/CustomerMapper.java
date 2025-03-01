package org.anle.mapper;

import org.anle.dto.CustomerDto;
import org.anle.dto.CustomerUpdateDto;
import org.anle.entity.Customer;

import java.util.Optional;

public class CustomerMapper {

    public static CustomerDto mapToDto(Customer customer) {
        return new CustomerDto(customer.getId(),
                Optional.of(customer.getName()),
                Optional.of(customer.getEmail()),
                Optional.of(customer.getDescription()),
                customer.getDateCreated());
    }

    public static Customer mapToEntity(CustomerUpdateDto userDto) {
        Customer customer = new Customer();
        userDto.name().ifPresent(customer::setName);
        userDto.email().ifPresent(customer::setEmail);
        userDto.description().ifPresent(customer::setDescription);
        return customer;
    }
}
