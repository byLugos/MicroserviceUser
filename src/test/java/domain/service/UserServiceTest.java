package domain.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import user.application.dto.request.UserDTORequest;
import user.domain.exceptions.InvalidDocumentException;
import user.domain.exceptions.InvalidExistantUserException;
import user.domain.exceptions.InvalidRoleException;
import user.domain.model.Role;
import user.domain.model.User;
import user.domain.ports.spi.UserOut;
import user.domain.service.UserService;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserOut userOut;
    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateUser_Success() {
        //Datos de prueba
        Long idRole = 1L;
        Role role = new Role(idRole, "ROLE_CLIENTE");
        when(userOut.findRoleById(idRole)).thenReturn(Optional.of(role));
        when(userOut.findUserByEmail("test1@example.com")).thenReturn(Optional.empty());
        when(userOut.existsBycc("12345")).thenReturn(false);
        when(userOut.saveUser(any(User.class))).thenAnswer(i -> i.getArgument(0));

        // Crear el DTO de solicitud de usuario (Request)
        UserDTORequest createUserRequest = new UserDTORequest(
                "John", "Doe", "12345", LocalDate.of(1990, 1, 1),
                "test1@example.com", "password", "5551234", idRole
        );

        // Mapeo manual de UserDTORequest a User
        User user = new User(
                null,
                createUserRequest.getName(),
                createUserRequest.getLastName(),
                createUserRequest.getCc(),
                createUserRequest.getBirthDate(),
                createUserRequest.getEmail(),
                createUserRequest.getPassword(),
                createUserRequest.getPhone(),
                role
        );

        // Ejecutar el método
        User createdUser = userService.createUser(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getCc(),
                user.getBirthDate(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                role.getId()
        );

        // Verificaciones
        assertNotNull(createdUser);
        assertEquals("John", createdUser.getName());
        assertEquals("Doe", createdUser.getLastName());
        assertEquals("ROLE_CLIENTE", createdUser.getRole().getName());
        verify(userOut, times(1)).saveUser(any(User.class));
    }

    @Test
    void testCreateUser_EmailAlreadyExists() {
        //Datos de prueba
        when(userOut.findUserByEmail("test1@example.com")).thenReturn(Optional.of(new User()));

        // Verificar excepción
        assertThrows(InvalidExistantUserException.class, () -> {
            userService.createUser(
                    null,
                    "John",
                    "Doe",
                    "12345",
                    LocalDate.of(1990, 1, 1),
                    "test1@example.com",
                    "password",
                    "5551234",
                    1L
            );
        });
    }
    @Test
    void testCreateUser_DocumentAlreadyExists() {
        //Datos de prueba
        when(userOut.findUserByEmail("test1@example.com")).thenReturn(Optional.empty());
        when(userOut.existsBycc("12345")).thenReturn(true);

        // Verificar excepción
        assertThrows(InvalidDocumentException.class, () -> {
            userService.createUser(
                    null,
                    "John",
                    "Doe",
                    "12345",
                    LocalDate.of(1990, 1, 1),
                    "test1@example.com",
                    "password",
                    "5551234",
                    1L
            );
        });
    }

    @Test
    void testCreateUser_InvalidRole() {
        //Datos de prueba
        when(userOut.findUserByEmail("test1@example.com")).thenReturn(Optional.empty());
        when(userOut.existsBycc("12345")).thenReturn(false);
        when(userOut.findRoleById(1L)).thenReturn(Optional.empty());

        // Verificar excepción
        assertThrows(InvalidRoleException.class, () -> {
            userService.createUser(
                    null,
                    "John",
                    "Doe",
                    "12345",
                    LocalDate.of(1990, 1, 1),
                    "test1@example.com",
                    "password",
                    "5551234",
                    1L
            );
        });
    }
}
