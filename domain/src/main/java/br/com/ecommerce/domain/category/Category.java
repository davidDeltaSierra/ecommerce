package br.com.ecommerce.domain.category;

import br.com.ecommerce.domain.Entity;

import java.util.UUID;

public class Category extends Entity<String> {
    private final String name;

    private Category(String id, String name) {
        super(id);
        this.name = name;
    }

    public static Category newCategory(String name) {
        return new Category(UUID.randomUUID().toString(), name);
    }

    public static Category with(String id, String name) {
        return new Category(id, name);
    }

    public String getName() {
        return name;
    }
}
