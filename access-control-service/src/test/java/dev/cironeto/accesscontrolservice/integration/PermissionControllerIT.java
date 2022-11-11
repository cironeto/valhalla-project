package dev.cironeto.accesscontrolservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cironeto.accesscontrolservice.dto.PermissionRequestBody;
import dev.cironeto.accesscontrolservice.factory.PermissionFactory;
import dev.cironeto.accesscontrolservice.repository.PermissionRepository;
import dev.cironeto.accesscontrolservice.util.TokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
class PermissionControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PermissionRepository permissionRepository;

    private String username;
    private String password;

    @BeforeEach
    void setUp() {
        username = "ciro.22@gmail.com";
        password = "123";
    }

    @Test
    void createPermission_shouldReturnTheCreatedID_WhenSuccessful() throws Exception {
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        PermissionRequestBody dto = PermissionFactory.createValidPostPermission();

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/permission/create")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.permissionCreatedId").exists());
    }

    @Test
    void createPermission_shouldReturnBadRequestWithExistingID_WhenAlreadyExistsPermission() throws Exception {
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        permissionRepository.save(PermissionFactory.createPermissionToBeSaved());
        PermissionRequestBody dto = PermissionFactory.createNotValidPostPermission();

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/permission/create")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isBadRequest())
                .andExpect(r -> assertTrue(r.getResolvedException().getMessage().contains("Permission already exists")));
    }
}