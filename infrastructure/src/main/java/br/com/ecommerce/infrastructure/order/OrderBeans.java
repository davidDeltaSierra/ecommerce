package br.com.ecommerce.infrastructure.order;

import br.com.ecommerce.domain.order.OrderService;
import br.com.ecommerce.domain.product.ProductService;
import br.com.ecommerce.infrastructure.common.ServiceLogDelegate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class OrderBeans {
    @Bean
    OrderService orderService(JpaOrderRepository jpaOrderRepository,
                              ProductService productService,
                              ServiceLogDelegate serviceLogDelegate) {
        return new OrderService(jpaOrderRepository, productService, serviceLogDelegate);
    }
}
