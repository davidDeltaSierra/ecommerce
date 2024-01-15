package br.com.ecommerce.domain.exception;

import java.util.List;

public interface ValidatorHandler {
    List<String> getErrors();

    void append(String constrains);

    boolean hasErrors();

    default void throwsIfContainsException() throws EntityException.ConstraintException {
        if (hasErrors()) {
            throw new EntityException.ConstraintException(getErrors());
        }
    }
}
