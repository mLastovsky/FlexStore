package com.mlastovsky.proxy;

import com.mlastovsky.model.PurchaseRequest;
import com.mlastovsky.model.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
@Component
public interface ProductProxy {

    @PostMapping("/purchase")
    List<PurchaseResponse> purchaseProducts(List<PurchaseRequest> requests);

}
