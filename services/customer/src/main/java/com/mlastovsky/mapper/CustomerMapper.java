package com.mlastovsky.mapper;

import com.mlastovsky.model.Customer;
import com.mlastovsky.model.CustomerRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(@Valid CustomerRequest request) {
        if (request == null) {
            return Customer.builder().build();
        }

        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .address(request.address())
                .email(request.email())
                .build();
    }

}
