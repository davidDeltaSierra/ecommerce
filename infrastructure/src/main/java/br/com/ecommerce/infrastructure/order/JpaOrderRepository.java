package br.com.ecommerce.infrastructure.order;

import br.com.ecommerce.domain.order.Order;
import br.com.ecommerce.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaOrderRepository implements OrderRepository {
    private final OrderJpaEntityRepository orderJpaEntityRepository;

    @Override
    public Optional<Order> findById(String id) {
        return orderJpaEntityRepository.findById(id)
                .map(OrderJpaEntity::mapperToDomain);
    }

    @Override
    public Order save(Order order) {
        return orderJpaEntityRepository.save(OrderJpaEntity.mapperFromDomain(order))
                .mapperToDomain();
    }
}
