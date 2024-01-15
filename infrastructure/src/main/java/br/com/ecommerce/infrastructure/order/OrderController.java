package br.com.ecommerce.infrastructure.order;

import br.com.ecommerce.domain.order.OrderService;
import br.com.ecommerce.domain.order.command.OrderSaveCommand;
import br.com.ecommerce.infrastructure.order.dto.OrderResponse;
import br.com.ecommerce.infrastructure.order.dto.OrderSaveRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/orders")
class OrderController {
    private final OrderService orderService;

    @PostMapping
    OrderResponse save(@Valid @RequestBody OrderSaveRequest body) {
        var order = orderService.save(new OrderSaveCommand(
                body.client(),
                body.products()
        ));
        return OrderResponse.builder()
                .id(order.getId())
                .client(order.getClient())
                .priceCents(order.getPriceCents())
                .orderItems(order.getOrderItems())
                .build();
    }
}
