package br.com.ecommerce.domain.category;

import br.com.ecommerce.domain.exception.AccumulatorValidator;
import br.com.ecommerce.domain.exception.ValidatorUtils;

import java.util.Objects;
import java.util.Optional;

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        Objects.requireNonNull(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findById(String id) {
        ValidatorUtils.stringNotNullOrBlank("id", id, new AccumulatorValidator())
                .throwsIfContainsException();
        return categoryRepository.findById(id);
    }

    public boolean existsById(String id) {
        ValidatorUtils.stringNotNullOrBlank("id", id, new AccumulatorValidator())
                .throwsIfContainsException();
        return categoryRepository.existsById(id);
    }
}
