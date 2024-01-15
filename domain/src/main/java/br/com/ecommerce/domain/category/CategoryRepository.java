package br.com.ecommerce.domain.category;

import java.util.Optional;

public interface CategoryRepository {
    Optional<Category> findById(String id);

    boolean existsById(String id);

    Category save(Category category);
}
