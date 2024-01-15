package br.com.ecommerce.infrastructure.product;

import br.com.ecommerce.domain.product.Product;
import br.com.ecommerce.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaProductRepository implements ProductRepository {
    private final ProductJpaEntityRepository productJpaEntityRepository;

    @Override
    public List<Product> findAllByIdIn(List<String> ids) {
        return productJpaEntityRepository.findAllById(ids)
                .stream()
                .map(ProductJpaEntity::mapperToDomain)
                .toList();
    }

    @Override
    public Product save(Product product) {
        return productJpaEntityRepository.save(ProductJpaEntity.mapperFromDomain(product))
                .mapperToDomain();
    }
}
