package br.com.ecommerce.domain;

public interface ServiceLogger {
    void debug(String msg, Object... args);
}
