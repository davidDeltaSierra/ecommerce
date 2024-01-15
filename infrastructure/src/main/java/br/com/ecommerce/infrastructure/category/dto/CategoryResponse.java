package br.com.ecommerce.infrastructure.category.dto;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record CategoryResponse(String id, String name) {
}
