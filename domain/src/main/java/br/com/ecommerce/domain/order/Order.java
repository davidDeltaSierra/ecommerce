package br.com.ecommerce.domain.order;

import br.com.ecommerce.domain.Entity;

import java.util.List;
import java.util.UUID;

public class Order extends Entity<String> {
    private final String client;
    private final int priceCents;
    private final List<OrderItem> orderItems;

    private Order(String id, String client, int priceCents, List<OrderItem> orderItems) {
        super(id);
        this.client = client;
        this.priceCents = priceCents;
        this.orderItems = orderItems;
    }

    public static Order newOrder(String client, int priceCents, List<OrderItem> orderItems) {
        return new Order(
                UUID.randomUUID().toString(),
                client,
                priceCents,
                orderItems
        );
    }

    public static Order with(String id, String client, int priceCents, List<OrderItem> orderItems) {
        return new Order(
                id,
                client,
                priceCents,
                orderItems
        );
    }

    public String getClient() {
        return client;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}
