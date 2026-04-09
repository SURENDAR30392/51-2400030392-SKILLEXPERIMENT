package com.example.jwt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthDemoApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loginShouldReturnTokenForValidCredentials() throws Exception {
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "admin",
                                  "password": "admin123"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isString())
                .andExpect(jsonPath("$.role").value("ADMIN"));
    }

    @Test
    void adminEndpointShouldRejectRequestWithoutToken() throws Exception {
        mockMvc.perform(post("/admin/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "employeeName": "John Doe",
                                  "department": "IT"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void employeeTokenShouldNotAccessAdminEndpoint() throws Exception {
        String employeeToken = loginAndGetToken("employee", "employee123");

        mockMvc.perform(post("/admin/add")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + employeeToken)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "employeeName": "John Doe",
                                  "department": "IT"
                                }
                                """))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminTokenShouldNotAccessEmployeeEndpoint() throws Exception {
        String adminToken = loginAndGetToken("admin", "admin123");

        mockMvc.perform(get("/employee/profile")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
                .andExpect(status().isForbidden());
    }

    @Test
    void employeeTokenShouldAccessEmployeeProfile() throws Exception {
        String employeeToken = loginAndGetToken("employee", "employee123");

        mockMvc.perform(get("/employee/profile")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + employeeToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("employee"))
                .andExpect(jsonPath("$.role").value("EMPLOYEE"));
    }

    @Test
    void invalidTokenShouldBeRejected() throws Exception {
        mockMvc.perform(get("/employee/profile")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer invalid.token.value"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void adminTokenShouldDeleteEmployee() throws Exception {
        String adminToken = loginAndGetToken("admin", "admin123");

        mockMvc.perform(delete("/admin/delete/99")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + adminToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Employee record deleted for id 99"));
    }

    private String loginAndGetToken(String username, String password) throws Exception {
        MvcResult result = mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "%s",
                                  "password": "%s"
                                }
                                """.formatted(username, password)))
                .andExpect(status().isOk())
                .andReturn();

        JsonNode jsonNode = objectMapper.readTree(result.getResponse().getContentAsString());
        return jsonNode.get("token").asText();
    }
}

