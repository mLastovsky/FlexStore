package com.mlastovsky.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper=false)
@Data
public class ProductPurchaseException extends RuntimeException {

    private final String message;

}
