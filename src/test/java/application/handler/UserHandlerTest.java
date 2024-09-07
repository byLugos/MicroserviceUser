package application.handler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import user.application.dto.request.UserDTORequest;
import user.application.dto.response.UserDTOResponse;
import user.application.handler.UserHandler;
import user.application.mapper.UserMapper;
import user.domain.exceptions.InvalidRoleException;
import user.domain.model.Role;
import user.domain.model.User;
import user.domain.ports.api.UserIn;
import user.domain.ports.spi.UserOut;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class UserHandlerTest {
    @Mock
    private UserIn userService;
    @Mock
    private UserOut userRepository;
    @Mock
    private BCryptPasswordEncoder passwordEncoder;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserHandler userHandler;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testCreateUser_InvalidRole() {
        UserDTORequest request = new UserDTORequest("John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe1@example.com", "password123", "+1234567890", 1L);

        when(userRepository.findRoleById(request.getIdRole())).thenReturn(Optional.empty());

        Exception exception = assertThrows(InvalidRoleException.class, () -> userHandler.createUser(request));
        assertEquals("El rol solicitado no existe", exception.getMessage());
        verify(userRepository, times(1)).findRoleById(request.getIdRole());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userMapper, never()).toDomain(any(UserDTORequest.class), any(Role.class));
        verify(userService, never()).createUser(anyLong(), anyString(), anyString(), anyString(), any(LocalDate.class), anyString(), anyString(), anyString(), anyLong());
        verify(userMapper, never()).toDto(any(User.class));
    }
}

