package user.infraestructure.controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import user.application.dto.request.UserDTORequest;
import user.application.dto.response.UserDTOResponse;
import user.application.handler.UserHandler;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class UserControllerTest {
    @Mock
    private UserHandler userHandler;
    @InjectMocks
    private UserController userController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createUser_Success_ReturnsCreatedResponse() {
        UserDTORequest userDTORequest = new UserDTORequest("John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "password123", "+1234567890", 1L);
        UserDTOResponse userDTOResponse = new UserDTOResponse(1L, "John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "+1234567890", "ROLE_ADMIN");
        when(userHandler.createUser(any(UserDTORequest.class))).thenReturn(userDTOResponse);
        ResponseEntity<UserDTOResponse> response = userController.createUser(userDTORequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDTOResponse, response.getBody());
        verify(userHandler, times(1)).createUser(any(UserDTORequest.class));
    }

    @Test
    void createUser_InvalidRequest_ReturnsBadRequest() {
        UserDTORequest userDTORequest = new UserDTORequest();
        when(userHandler.createUser(any(UserDTORequest.class))).thenThrow(new IllegalArgumentException("Solicitud inválida"));
        try {
            userController.createUser(userDTORequest);
        } catch (Exception ex) {
            assertEquals(IllegalArgumentException.class, ex.getClass());
            assertEquals("Solicitud inválida", ex.getMessage());
        }
        verify(userHandler, times(1)).createUser(any(UserDTORequest.class));
    }
}
