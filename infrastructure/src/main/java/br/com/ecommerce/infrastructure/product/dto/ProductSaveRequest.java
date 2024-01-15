package br.com.ecommerce.infrastructure.product.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record ProductSaveRequest(
        @NotEmpty
        String name,
        @NotNull
        Integer priceCents,
        @NotEmpty
        String category
) {
}
