package br.com.ecommerce.domain.product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAllByIdIn(List<String> ids);

    Product save(Product product);
}
