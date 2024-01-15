package br.com.ecommerce.infrastructure.product;

import br.com.ecommerce.domain.product.ProductService;
import br.com.ecommerce.domain.product.command.ProductSaveCommand;
import br.com.ecommerce.infrastructure.product.dto.ProductResponse;
import br.com.ecommerce.infrastructure.product.dto.ProductSaveRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/products")
class ProductController {
    private final ProductService productService;

    @PostMapping
    ProductResponse save(@Valid @RequestBody ProductSaveRequest body) {
        var product = productService.save(new ProductSaveCommand(
                body.name(),
                body.priceCents(),
                body.category()
        ));
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .priceCents(product.getPriceCents())
                .category(product.getCategory())
                .build();
    }
}
