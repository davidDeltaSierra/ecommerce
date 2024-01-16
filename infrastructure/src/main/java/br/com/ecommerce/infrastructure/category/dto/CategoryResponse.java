package br.com.ecommerce.infrastructure.category.dto;

import br.com.ecommerce.domain.category.Category;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record CategoryResponse(String id, String name) {
    public static CategoryResponse mapper(Category category) {
        return new CategoryResponse(category.getId(), category.getName());
    }
}
