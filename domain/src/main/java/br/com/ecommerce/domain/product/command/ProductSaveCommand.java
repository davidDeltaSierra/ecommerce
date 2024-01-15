package br.com.ecommerce.domain.product.command;

import br.com.ecommerce.domain.exception.ValidatorHandler;

public record ProductSaveCommand(String name, int priceCents, String category) {
    public ValidatorHandler validate(ValidatorHandler validator) {
        validateName(validator);
        validatePrice(validator);
        validateCategory(validator);
        return validator;
    }

    private void validatePrice(ValidatorHandler validator) {
        if (priceCents < 0) {
            validator.append("Price should be greater than 0");
        }
    }

    private void validateName(ValidatorHandler validator) {
        if (name == null) {
            validator.append("Name should be not null");
            return;
        }
        if (name.isBlank()) {
            validator.append("Name should be not blank");
        }
    }

    private void validateCategory(ValidatorHandler validator) {
        if (category == null) {
            validator.append("Category should be not null");
            return;
        }
        if (category.isBlank()) {
            validator.append("Category should be not blank");
        }
    }
}
