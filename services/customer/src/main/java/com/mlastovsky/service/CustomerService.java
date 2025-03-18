package com.mlastovsky.service;

import com.mlastovsky.exception.CustomerNotFoundException;
import com.mlastovsky.mapper.CustomerMapper;
import com.mlastovsky.model.Customer;
import com.mlastovsky.model.CustomerRequest;
import com.mlastovsky.repository.CustomerRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.lang.String.format;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with the provided ID:: %s", request.id())
                ));
        mergerCustomer(request, customer);
        customerRepository.save(customer);
    }

    private static void mergerCustomer(CustomerRequest request, Customer customer) {
        if (isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }

        if (isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }

        if (isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }

        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }
}
