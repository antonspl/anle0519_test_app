package org.anle.service;

import org.anle.dto.CustomerUpdateDto;
import org.anle.entity.Customer;
import org.anle.repository.CustomerRepository;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    private final CustomerRepository customerRepository = mock(CustomerRepository.class);
    private final CustomerService customerService = new CustomerService(customerRepository);
    private static final Customer testCustomer;

    static {
        testCustomer = new Customer();
        testCustomer.setId(1L);
        testCustomer.setName("Name");
        testCustomer.setEmail("email");
        testCustomer.setDescription("description");
        testCustomer.setDateCreated(OffsetDateTime.now());
    }

    @Test
    public void finds_all_users() {
        //given
        when(customerRepository.findAll()).thenReturn(List.of(testCustomer));

        //when
        var users = customerService.getAllCustomers();

        //then
        assertEquals(1, users.size());
        var user = users.get(0);
        assertEquals(testCustomer.getId(), user.id());
        assertEquals(Optional.of(testCustomer.getName()), user.name());
        assertEquals(Optional.of(testCustomer.getEmail()), user.email());
        assertEquals(Optional.of(testCustomer.getDescription()), user.description());
        assertEquals(testCustomer.getDateCreated(), user.dateCreated());
        verify(customerRepository, times(1)).findAll();
    }

    @Test
    public void finds_user_by_id() {
        //given
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));

        //when
        var user = customerService.getCustomerById(1L);

        //then
        assertEquals(testCustomer.getId(), user.id());
        assertEquals(Optional.of(testCustomer.getName()), user.name());
        assertEquals(Optional.of(testCustomer.getEmail()), user.email());
        assertEquals(Optional.of(testCustomer.getDescription()), user.description());
        assertEquals(testCustomer.getDateCreated(), user.dateCreated());
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void does_not_find_user_by_id() {
        //given
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        //then
        assertThrows(CustomerService.CustomerNotFoundException.class, () -> customerService.getCustomerById(1L));
        verify(customerRepository, times(1)).findById(1L);
    }

    @Test
    public void creates_user() {
        //given
        Customer existingCustomer = new Customer();
        existingCustomer.setName("Name");
        existingCustomer.setEmail("email@example.com");
        existingCustomer.setDescription("description");
        existingCustomer.setDateCreated(OffsetDateTime.now());
        CustomerUpdateDto updatedFields = new CustomerUpdateDto(
                Optional.of("Name"),
                Optional.of("email@example.com"),
                Optional.of("description"));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        //when
        var result = customerService.saveCustomer(updatedFields);

        //then
        assertNotNull(result);
        assertEquals(Optional.of("Name"), result.name());
        assertEquals(Optional.of("email@example.com"), result.email());
        assertEquals(Optional.of("description"), result.description());
    }

    @Test
    public void updates_user_fields() {
        //given
        Customer existingCustomer = new Customer();
        existingCustomer.setName("Old Name");
        existingCustomer.setEmail("old.email@example.com");
        existingCustomer.setDescription("old description");
        existingCustomer.setDateCreated(OffsetDateTime.now());
        CustomerUpdateDto updatedFields = new CustomerUpdateDto(
                Optional.of("New Name"),
                Optional.of("new.email@example.com"),
                Optional.of("new description"));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));
        when(customerRepository.save(any(Customer.class))).thenReturn(existingCustomer);

        //when
        var result = customerService.updateCustomer(1L, updatedFields);

        //then
        assertNotNull(result);
        assertEquals(Optional.of("New Name"), result.name());
        assertEquals(Optional.of("new.email@example.com"), result.email());
        assertEquals(Optional.of("new description"), result.description());
    }
}
