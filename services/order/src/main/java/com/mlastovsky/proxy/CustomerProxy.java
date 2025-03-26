package com.mlastovsky.proxy;

import com.mlastovsky.model.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
@Component
public interface CustomerProxy {

    @GetMapping("/{id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable String id);

}
