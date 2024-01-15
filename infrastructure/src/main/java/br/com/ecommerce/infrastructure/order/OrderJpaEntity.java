package br.com.ecommerce.infrastructure.order;

import br.com.ecommerce.domain.order.Order;
import br.com.ecommerce.domain.order.OrderItem;
import br.com.ecommerce.infrastructure.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "`order`")
public class OrderJpaEntity extends AbstractEntity {
    @Column
    private String client;

    @Column
    private Integer priceCents;

    @Convert(converter = OrderItemCollectionConverter.class)
    @Column(columnDefinition = "json")
    private List<OrderItem> orderItems;

    public Order mapperToDomain() {
        return Order.with(
                getId(),
                getClient(),
                getPriceCents(),
                getOrderItems()
        );
    }

    public static OrderJpaEntity mapperFromDomain(Order order) {
        return OrderJpaEntity.builder()
                .id(order.getId())
                .client(order.getClient())
                .priceCents(order.getPriceCents())
                .orderItems(order.getOrderItems())
                .build();
    }
}
