package br.com.ecommerce.infrastructure.product.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record ProductResponse(String id, String name, int priceCents, String category) {
}
