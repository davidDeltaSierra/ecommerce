package br.com.ecommerce.infrastructure.category;

import br.com.ecommerce.domain.category.CategoryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CategoryBeans {
    @Bean
    CategoryService categoryService(JpaCategoryRepository categoryJpaRepository) {
        return new CategoryService(categoryJpaRepository);
    }
}
