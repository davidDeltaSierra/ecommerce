package br.com.ecommerce.infrastructure.order;

import br.com.ecommerce.domain.order.OrderItem;
import br.com.ecommerce.infrastructure.common.JsonAttributeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class OrderItemCollectionConverter extends JsonAttributeConverter<List<OrderItem>> {
    public OrderItemCollectionConverter(ObjectMapper objectMapper) {
        super(objectMapper);
    }
}
