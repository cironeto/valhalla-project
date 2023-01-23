package dev.cironeto.accesscontrolservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.cironeto.accesscontrolservice.dto.BusinessFunctionPermissionRequestBody;
import dev.cironeto.accesscontrolservice.factory.BusinessFunctionFactory;
import dev.cironeto.accesscontrolservice.factory.BusinessFunctionPermissionFactory;
import dev.cironeto.accesscontrolservice.factory.PermissionFactory;
import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import dev.cironeto.accesscontrolservice.model.BusinessFunctionPermission;
import dev.cironeto.accesscontrolservice.model.Permission;
import dev.cironeto.accesscontrolservice.repository.BusinessFunctionPermissionRepository;
import dev.cironeto.accesscontrolservice.repository.BusinessFunctionRepository;
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class BusinessFunctionPermissionControllerIT {

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

    @Autowired
    private BusinessFunctionRepository businessFunctionRepository;
    
    @Autowired
    private BusinessFunctionPermissionRepository businessFunctionPermissionRepository;

    private String username;
    private String password;

    @BeforeEach
    void setUp() {
        username = "ciro.neto16@gmail.com";
        password = "123";
    }

    @Test
    void createBusinessFunctionPermission_shouldReturnTheCreatedID_WhenSuccessful() throws Exception {
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);

        Permission permission = permissionRepository.save(PermissionFactory.createPermissionToBeSaved());
        BusinessFunction businessFunction = businessFunctionRepository.save(BusinessFunctionFactory.createBusinessFunctionToBeSaved());

        BusinessFunctionPermissionRequestBody dto = BusinessFunctionPermissionFactory.createEmptyPostBusinessFunctionPermissionObject();
        dto.setBusinessFunctionId(businessFunction.getId());
        dto.setPermissionId(permission.getId());

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/business-function-permission/create")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isOk());
        result.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void createBusinessFunctionPermission_shouldReturnNotFound_WhenBusinessFunctionDoesNotExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
        Permission permissionSaved = permissionRepository.save(PermissionFactory.createPermissionToBeSaved());
        Long notValidBusinessFunction = 999999L;

        BusinessFunctionPermissionRequestBody dto = BusinessFunctionPermissionFactory.createEmptyPostBusinessFunctionPermissionObject();
        dto.setPermissionId(permissionSaved.getId());
        dto.setBusinessFunctionId(notValidBusinessFunction);

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/business-function-permission/create")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(r -> assertTrue(r.getResolvedException().getMessage().contains("Business Function does not exist")));
    }

    @Test
    void createBusinessFunctionPermission_shouldReturnNotFound_WhenPermissionDoesNotExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
        BusinessFunction businessFunctionSaved = businessFunctionRepository.save(BusinessFunctionFactory.createBusinessFunctionToBeSaved());
        Long notValidPermission = 999999L;

        BusinessFunctionPermissionRequestBody dto = BusinessFunctionPermissionFactory.createEmptyPostBusinessFunctionPermissionObject();
        dto.setBusinessFunctionId(businessFunctionSaved.getId());
        dto.setPermissionId(notValidPermission);

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/business-function-permission/create")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(r -> assertTrue(r.getResolvedException().getMessage().contains("Permission does not exist")));
    }

    @Test
    void createBusinessFunctionPermission_shouldBadRequest_WhenBusinessFunctionPermissionRelationshipAlreadyExists() throws Exception{
        String accessToken = tokenUtil.obtainAccessToken(mockMvc, username, password);
        Permission permission = permissionRepository.save(PermissionFactory.createPermissionToBeSaved());
        BusinessFunction businessFunction = businessFunctionRepository.save(BusinessFunctionFactory.createBusinessFunctionToBeSaved());
        BusinessFunctionPermission entity = new BusinessFunctionPermission();

        entity.setBusinessFunction(businessFunction);
        entity.setPermission(permission);

        BusinessFunctionPermission savedEntity = businessFunctionPermissionRepository.save(entity);

        BusinessFunctionPermissionRequestBody dto = BusinessFunctionPermissionFactory.createEmptyPostBusinessFunctionPermissionObject();
        dto.setPermissionId(permission.getId());
        dto.setBusinessFunctionId(businessFunction.getId());

        String jsonBody = objectMapper.writeValueAsString(dto);

        ResultActions result =
                mockMvc.perform(MockMvcRequestBuilders
                        .post("/business-function-permission/create")
                        .header("Authorization", "Bearer " + accessToken)
                        .content(jsonBody)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        result.andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(r -> assertTrue(r.getResolvedException().getMessage().contains("Business Function/Permission relationship already exists. ID:")));
    }
}
