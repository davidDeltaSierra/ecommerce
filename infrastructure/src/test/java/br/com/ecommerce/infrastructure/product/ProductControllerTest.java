package br.com.ecommerce.infrastructure.product;

import br.com.ecommerce.infrastructure.common.BaseIntegrationTest;
import br.com.ecommerce.infrastructure.product.dto.ProductSaveRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ProductControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("/h2/category.sql")
    void givenRegisteredCategoryId_whenCallSaveEndpoint_thenReturnSuccess() throws Exception {
        var category = "5d3e145c-1912-4575-9604-22604720472b";
        var body = ProductSaveRequest.builder()
                .name("TV Samsung 40º")
                .priceCents(400000)
                .category(category)
                .build();
        mockMvc.perform(
                        post("/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(body))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty())
                .andExpect(jsonPath("$.priceCents").isNotEmpty())
                .andExpect(jsonPath("$.category").isNotEmpty());
    }

    @Test
    @Sql("/h2/category.sql")
    void givenNoRegisteredCategoryId_whenCallSaveEndpoint_thenReturnNotFound() throws Exception {
        var category = UUID.randomUUID().toString();
        var body = ProductSaveRequest.builder()
                .name("TV Samsung 40º")
                .priceCents(400000)
                .category(category)
                .build();
        mockMvc.perform(
                        post("/v1/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(body))
                )
                .andExpect(status().isNotFound());
    }
}
