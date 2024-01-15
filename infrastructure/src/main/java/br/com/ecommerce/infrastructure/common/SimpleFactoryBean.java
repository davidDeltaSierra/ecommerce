package br.com.ecommerce.infrastructure.common;

public interface SimpleFactoryBean<T> {
    T getInstance(String id);
}
