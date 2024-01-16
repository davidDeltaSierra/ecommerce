package br.com.ecommerce.infrastructure.order;

import br.com.ecommerce.infrastructure.common.BaseIntegrationTest;
import br.com.ecommerce.infrastructure.order.dto.OrderSaveRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class OrderControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Sql("/h2/order.sql")
    void givenRegisteredId_whenCallFindByIdEndpoint_thenReturnSuccess() throws Exception {
        var id = "8afffe0f-80c3-40d8-a215-9fd973c4bdef";
        mockMvc.perform(get("/v1/orders/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.client").isNotEmpty())
                .andExpect(jsonPath("$.priceCents").isNumber())
                .andExpect(jsonPath("$.orderItems").isNotEmpty());
    }

    @Test
    @Sql({"/h2/category.sql", "/h2/product.sql"})
    void givenRegisteredProductCollectionIds_whenCallSaveEndpoint_thenReturnSuccess() throws Exception {
        var product = "dcdf51b5-98b8-4638-9365-e7c0ce88a6e0";
        var body = OrderSaveRequest.builder()
                .client("David Silva")
                .products(List.of(product))
                .build();
        mockMvc.perform(
                        post("/v1/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(body))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.client").isNotEmpty())
                .andExpect(jsonPath("$.priceCents").isNumber())
                .andExpect(jsonPath("$.orderItems").isNotEmpty());
    }

    @Test
    void givenNotRegisteredProductCollectionIds_whenCallSaveEndpoint_thenReturnNotFound() throws Exception {
        var product = UUID.randomUUID().toString();
        var body = OrderSaveRequest.builder()
                .client("David Silva")
                .products(List.of(product))
                .build();
        mockMvc.perform(
                        post("/v1/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(body))
                )
                .andExpect(status().isNotFound());
    }
}
