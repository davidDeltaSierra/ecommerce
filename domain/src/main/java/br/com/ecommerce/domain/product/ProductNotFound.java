package br.com.ecommerce.domain.product;

import br.com.ecommerce.domain.exception.EntityException;

public class ProductNotFound extends EntityException.NotFound {
    public ProductNotFound(String message) {
        super(message);
    }
}
