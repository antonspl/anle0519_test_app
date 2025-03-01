package org.anle.controller;

import org.anle.dto.CustomerDto;
import org.anle.dto.CustomerUpdateDto;
import org.anle.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        return ok(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Long id) {
        return ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerUpdateDto user) {
        return ok(customerService.saveCustomer(user));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomerFields(@PathVariable Long id, @RequestBody CustomerUpdateDto updatedFields) {
        CustomerDto updatedUser = customerService.updateCustomer(id, updatedFields);
        return ok(updatedUser);
    }
}
