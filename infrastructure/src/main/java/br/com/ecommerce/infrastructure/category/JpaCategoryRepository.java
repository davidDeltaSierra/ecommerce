package br.com.ecommerce.infrastructure.category;

import br.com.ecommerce.domain.category.Category;
import br.com.ecommerce.domain.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaCategoryRepository implements CategoryRepository {
    private final CategoryJpaEntityRepository categoryJpaEntityRepository;

    @Override
    public Optional<Category> findById(String id) {
        return categoryJpaEntityRepository.findById(id)
                .map(CategoryJpaEntity::mapperToDomain);
    }

    @Override
    public boolean existsById(String id) {
        return categoryJpaEntityRepository.existsById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryJpaEntityRepository.save(CategoryJpaEntity.mapperFromDomain(category))
                .mapperToDomain();
    }
}
