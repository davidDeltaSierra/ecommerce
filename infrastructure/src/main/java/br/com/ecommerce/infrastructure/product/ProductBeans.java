package br.com.ecommerce.infrastructure.product;

import br.com.ecommerce.domain.category.CategoryService;
import br.com.ecommerce.domain.product.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ProductBeans {
    @Bean
    ProductService productService(JpaProductRepository productRepository,
                                  CategoryService categoryService) {
        return new ProductService(productRepository, categoryService);
    }
}
