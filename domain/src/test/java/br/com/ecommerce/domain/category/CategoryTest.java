package br.com.ecommerce.domain.category;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CategoryTest {
    @Test
    void givenValidParams_whenCallNewCategory_thenSuccess() {
        var category = Category.newCategory("Eletronicos");
        assertThat(category.getName())
                .isNotNull()
                .isNotEmpty();
        assertThat(category.getId())
                .isNotNull()
                .isNotEmpty();
    }
}
