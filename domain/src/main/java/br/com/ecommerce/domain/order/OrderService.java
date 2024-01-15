package br.com.ecommerce.domain.order;

import br.com.ecommerce.domain.ServiceLogger;
import br.com.ecommerce.domain.exception.AccumulatorValidator;
import br.com.ecommerce.domain.exception.ValidatorUtils;
import br.com.ecommerce.domain.order.command.OrderSaveCommand;
import br.com.ecommerce.domain.product.ProductNotFound;
import br.com.ecommerce.domain.product.ProductService;

import java.util.Objects;
import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ServiceLogger log;

    public OrderService(OrderRepository orderRepository, ProductService productService, ServiceLogger log) {
        Objects.requireNonNull(orderRepository);
        Objects.requireNonNull(productService);
        Objects.requireNonNull(log);
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.log = log;
    }

    public Optional<Order> findById(String id) {
        ValidatorUtils.stringNotNullOrBlank("id", id, new AccumulatorValidator())
                .throwsIfContainsException();
        log.debug("Find order by id: {}", id);
        return orderRepository.findById(id);
    }

    public Order save(OrderSaveCommand command) {
        log.debug("Save order with command: {}", command);
        command.validate(new AccumulatorValidator())
                .throwsIfContainsException();
        var products = productService.findAllByIdIn(command.products());

        var notFoundCollection = command.products()
                .stream()
                .filter(id -> products.stream().noneMatch(product -> product.getId().equals(id)))
                .toList();

        if (!notFoundCollection.isEmpty()) {
            throw new ProductNotFound("Products not found " + notFoundCollection);
        }

        var totalPrice = productService.totalPrice(products);
        return orderRepository.save(
                Order.newOrder(
                        command.client(),
                        totalPrice,
                        products.stream()
                                .map(it -> new OrderItem(it.getId(), it.getPriceCents()))
                                .toList()
                )
        );
    }
}
