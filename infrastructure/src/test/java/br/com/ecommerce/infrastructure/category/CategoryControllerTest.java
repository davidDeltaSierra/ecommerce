package br.com.ecommerce.infrastructure.category;

import br.com.ecommerce.infrastructure.common.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CategoryControllerTest extends BaseIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql("/h2/category.sql")
    void givenRegisteredId_whenCallFindByIdEndpoint_thenReturnSuccess() throws Exception {
        var id = "5d3e145c-1912-4575-9604-22604720472b";
        mockMvc.perform(get("/v1/categories/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").isNotEmpty());
    }

    @Test
    void givenNotRegisteredId_whenCallFindByIdEndpoint_thenReturnNotFound() throws Exception {
        var randomId = UUID.randomUUID().toString();
        mockMvc.perform(get("/v1/categories/{id}", randomId))
                .andExpect(status().isNotFound());
    }
}
