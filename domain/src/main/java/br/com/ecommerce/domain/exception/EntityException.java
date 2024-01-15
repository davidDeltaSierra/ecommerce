package br.com.ecommerce.domain.exception;

import java.util.List;

public class EntityException extends RuntimeException {
    public EntityException(String message) {
        this(message, null);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause, true, false);
    }

    public static class NotFound extends EntityException {
        public NotFound(String message) {
            this(message, null);
        }

        public NotFound(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class ConstraintException extends EntityException {
        private final List<String> constraints;

        public ConstraintException(String message) {
            this(List.of(message), null);
        }

        public ConstraintException(List<String> constraints) {
            this(constraints, null);
        }

        public ConstraintException(List<String> constraints, Throwable cause) {
            super(constraints.toString(), cause);
            this.constraints = constraints;
        }

        public List<String> getConstraints() {
            return constraints;
        }
    }
}
