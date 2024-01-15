package br.com.ecommerce.infrastructure.product;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProductJpaEntityRepository extends JpaRepository<ProductJpaEntity, String> {

}
