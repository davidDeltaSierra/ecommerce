package br.com.ecommerce.infrastructure.order;

import org.springframework.data.jpa.repository.JpaRepository;

interface OrderJpaEntityRepository extends JpaRepository<OrderJpaEntity, String> {
}
