package br.com.ecommerce.domain.exception;

import java.util.List;

public final class ValidatorUtils {
    private ValidatorUtils() {
    }

    public static ValidatorHandler stringNotNullOrBlank(String target, ValidatorHandler validator) {
        return stringNotNullOrBlank("String arg", target, validator);
    }

    public static ValidatorHandler stringNotNullOrBlank(String id, String target, ValidatorHandler validator) {
        if (target == null) {
            validator.append("%s should be not null".formatted(id));
            return validator;
        }
        if (target.isBlank()) {
            validator.append("%s should be not blank".formatted(id));
        }
        return validator;
    }

    public static ValidatorHandler listNotNullOrEmpty(String id, List<?> target, ValidatorHandler validator) {
        if (target == null) {
            validator.append("%s should be not null".formatted(id));
            return validator;
        }
        if (target.isEmpty()) {
            validator.append("%s should be not empty".formatted(id));
        }
        return validator;
    }
}
