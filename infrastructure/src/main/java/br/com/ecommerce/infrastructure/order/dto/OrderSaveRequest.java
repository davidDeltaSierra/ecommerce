package br.com.ecommerce.infrastructure.order.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record OrderSaveRequest(
        @NotEmpty
        String client,
        @NotEmpty
        List<String> products
) {
}
