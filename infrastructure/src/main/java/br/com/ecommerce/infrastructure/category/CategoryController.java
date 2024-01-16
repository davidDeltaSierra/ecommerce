package br.com.ecommerce.infrastructure.category;

import br.com.ecommerce.domain.category.CategoryService;
import br.com.ecommerce.infrastructure.category.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/categories")
class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("{id}")
    CategoryResponse findById(@PathVariable String id) {
        return categoryService.findById(id)
                .map(CategoryResponse::mapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category %s not found".formatted(id)));
    }
}
