package br.com.ecommerce.domain.product;

import br.com.ecommerce.domain.category.CategoryNotFound;
import br.com.ecommerce.domain.category.CategoryService;
import br.com.ecommerce.domain.exception.AccumulatorValidator;
import br.com.ecommerce.domain.exception.ValidatorUtils;
import br.com.ecommerce.domain.product.command.ProductSaveCommand;

import java.util.List;
import java.util.Objects;

public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        Objects.requireNonNull(productRepository);
        Objects.requireNonNull(categoryService);
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<Product> findAllByIdIn(List<String> ids) {
        ValidatorUtils.listNotNullOrEmpty("ids", ids, new AccumulatorValidator())
                .throwsIfContainsException();
        return productRepository.findAllByIdIn(ids);
    }

    public int totalPrice(List<Product> products) {
        ValidatorUtils.listNotNullOrEmpty("Products", products, new AccumulatorValidator())
                .throwsIfContainsException();
        return products.stream()
                .map(Product::getPriceCents)
                .reduce(0, Integer::sum);
    }

    public Product save(ProductSaveCommand command) {
        command.validate(new AccumulatorValidator())
                .throwsIfContainsException();
        var categoryExists = categoryService.existsById(command.category());
        if (!categoryExists) {
            throw new CategoryNotFound("Category %s should be exists before save product".formatted(command.category()));
        }
        return productRepository.save(
                Product.newProduct(command.name(), command.priceCents(), command.category())
        );
    }
}
