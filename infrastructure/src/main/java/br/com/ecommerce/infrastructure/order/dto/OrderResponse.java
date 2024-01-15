package br.com.ecommerce.infrastructure.order.dto;

import br.com.ecommerce.domain.order.OrderItem;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record OrderResponse(
        String id,
        String client,
        int priceCents,
        List<OrderItem> orderItems
) {
}
