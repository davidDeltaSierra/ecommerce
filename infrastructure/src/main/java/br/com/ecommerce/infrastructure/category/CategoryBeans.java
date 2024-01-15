package br.com.ecommerce.infrastructure.category;

import br.com.ecommerce.domain.category.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
class CategoryBeans {
    @Bean
    CategoryService categoryService(JpaCategoryRepository categoryJpaRepository) {
        return new CategoryService(categoryJpaRepository);
    }
}
