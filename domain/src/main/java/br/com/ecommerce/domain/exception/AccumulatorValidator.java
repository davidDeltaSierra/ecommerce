package br.com.ecommerce.domain.exception;

import java.util.ArrayList;
import java.util.List;

public class AccumulatorValidator implements ValidatorHandler {
    private final List<String> errors = new ArrayList<>();

    @Override
    public List<String> getErrors() {
        return errors;
    }

    @Override
    public void append(String constrains) {
        errors.add(constrains);
    }

    @Override
    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
