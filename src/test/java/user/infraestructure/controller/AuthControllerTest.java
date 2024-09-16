package user.infraestructure.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import user.infraestructure.auth.serviceauth.AuthService;
import user.infraestructure.controller.dtojwt.JwtResponse;
import user.infraestructure.controller.dtojwt.LoginRequest;
import user.infraestructure.config.SecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class})
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authInfrastructureService;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void testAuthenticateUser_Success() throws Exception {

        LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");
        JwtResponse jwtResponse = new JwtResponse("test-jwt-token");

        when(authInfrastructureService.authenticateUser(any(LoginRequest.class)))
                .thenReturn(ResponseEntity.ok(jwtResponse));

        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())  // Verifica el código de estado 200 OK
                .andExpect(jsonPath("$.token").value("test-jwt-token"));
    }

    @Test
    void testAuthenticateUser_Failure() throws Exception {

        LoginRequest loginRequest = new LoginRequest("test@example.com", "wrongpassword");

        when(authInfrastructureService.authenticateUser(any(LoginRequest.class)))
                .thenReturn(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponse("Credenciales inválidas")));


        mockMvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.token").value("Credenciales inválidas"));
    }
}
