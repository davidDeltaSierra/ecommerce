package br.com.ecommerce.infrastructure.category;

import br.com.ecommerce.domain.category.Category;
import br.com.ecommerce.infrastructure.common.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "category")
public class CategoryJpaEntity extends AbstractEntity {
    @Column
    private String name;

    public Category mapperToDomain() {
        return Category.with(
                getId(),
                getName()
        );
    }

    public static CategoryJpaEntity mapperFromDomain(Category category) {
        return CategoryJpaEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
