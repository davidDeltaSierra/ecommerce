package br.com.ecommerce.domain.product;

import br.com.ecommerce.domain.Entity;

import java.util.UUID;

public class Product extends Entity<String> {
    private final String name;
    private final int priceCents;
    private final String category;

    private Product(String id, String name, int priceCents, String category) {
        super(id);
        this.name = name;
        this.priceCents = priceCents;
        this.category = category;
    }

    public static Product newProduct(String name, int priceCents, String category) {
        return new Product(UUID.randomUUID().toString(), name, priceCents, category);
    }

    public static Product with(String id, String name, int priceCents, String category) {
        return new Product(id, name, priceCents, category);
    }

    public String getName() {
        return name;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public String getCategory() {
        return category;
    }
}
