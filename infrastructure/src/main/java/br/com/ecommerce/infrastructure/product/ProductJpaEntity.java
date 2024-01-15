package br.com.ecommerce.infrastructure.product;

import br.com.ecommerce.domain.product.Product;
import br.com.ecommerce.infrastructure.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductJpaEntity extends AbstractEntity {
    @Column
    private String name;

    @Column
    private Integer priceCents;

    @JoinColumn(name = "category_id")
    private String category;

    public Product mapperToDomain() {
        return Product.with(
                getId(),
                getName(),
                getPriceCents(),
                getCategory()
        );
    }

    public static ProductJpaEntity mapperFromDomain(Product product) {
        return ProductJpaEntity.builder()
                .id(product.getId())
                .name(product.getName())
                .priceCents(product.getPriceCents())
                .category(product.getCategory())
                .build();
    }
}
