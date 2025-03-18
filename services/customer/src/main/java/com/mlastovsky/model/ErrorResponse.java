package com.mlastovsky.model;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
