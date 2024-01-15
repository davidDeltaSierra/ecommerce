package br.com.ecommerce.infrastructure.category;

import org.springframework.data.jpa.repository.JpaRepository;

interface CategoryJpaEntityRepository extends JpaRepository<CategoryJpaEntity, String> {

}
