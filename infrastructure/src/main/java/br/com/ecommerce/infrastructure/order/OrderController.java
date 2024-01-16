package br.com.ecommerce.infrastructure.order;

import br.com.ecommerce.domain.order.OrderService;
import br.com.ecommerce.domain.order.command.OrderSaveCommand;
import br.com.ecommerce.infrastructure.order.dto.OrderResponse;
import br.com.ecommerce.infrastructure.order.dto.OrderSaveRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/orders")
class OrderController {
    private final OrderService orderService;

    @GetMapping("{id}")
    OrderResponse findById(@PathVariable String id) {
        return orderService.findById(id)
                .map(OrderResponse::mapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order %s not found".formatted(id)));
    }

    @PostMapping
    OrderResponse save(@Valid @RequestBody OrderSaveRequest body) {
        var order = orderService.save(new OrderSaveCommand(
                body.client(),
                body.products()
        ));
        return OrderResponse.mapper(order);
    }
}
