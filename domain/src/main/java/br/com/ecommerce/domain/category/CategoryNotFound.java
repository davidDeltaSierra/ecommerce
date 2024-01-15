package br.com.ecommerce.domain.category;

import br.com.ecommerce.domain.exception.EntityException;

public class CategoryNotFound extends EntityException.NotFound {
    public CategoryNotFound(String message) {
        super(message);
    }
}
