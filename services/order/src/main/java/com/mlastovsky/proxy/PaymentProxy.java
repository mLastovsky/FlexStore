package com.mlastovsky.proxy;

import com.mlastovsky.model.PaymentRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "payment-service",
        url = "${application.config.payment-url}"
)
@Component
public interface PaymentProxy {

    @PostMapping
    Long requestOrderPayment(@RequestBody PaymentRequest request);

}
