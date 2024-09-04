package infraestructure.jpa.repository.user;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import user.domain.model.Role;
import user.domain.model.User;
import user.infraestructure.jpa.entity.RoleEntity;
import user.infraestructure.jpa.entity.UserEntity;
import user.infraestructure.jpa.mapper.UserJpaMapper;
import user.infraestructure.jpa.repository.role.RoleJpaRepo;
import user.infraestructure.jpa.repository.user.UserJpaOut;
import user.infraestructure.jpa.repository.user.UserJpaRepo;

import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class UserJpaOutTest {
    @Mock
    private UserJpaRepo userJpaRepo;
    @Mock
    private RoleJpaRepo roleJpaRepo;
    @Mock
    private UserJpaMapper userJpaMapper;
    @InjectMocks
    private UserJpaOut userJpaOut;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSaveUser_Success() {
        User user = new User(1L, "John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "encryptedPassword", "+1234567890", new Role(1L, "ROLE_ADMIN"));
        UserEntity userEntity = new UserEntity(1L, "John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "encryptedPassword", "+1234567890", new RoleEntity(1L, "ROLE_ADMIN", null));

        when(userJpaMapper.toEntity(any(User.class))).thenReturn(userEntity);
        when(userJpaRepo.save(any(UserEntity.class))).thenReturn(userEntity);
        when(userJpaMapper.toDomain(any(UserEntity.class))).thenReturn(user);

        User savedUser = userJpaOut.saveUser(user);

        assertNotNull(savedUser);
        assertEquals(user.getEmail(), savedUser.getEmail());
        verify(userJpaRepo, times(1)).save(any(UserEntity.class));
        verify(userJpaMapper, times(1)).toEntity(any(User.class));
        verify(userJpaMapper, times(1)).toDomain(any(UserEntity.class));
    }

    @Test
    void testFindUserById_UserExists() {
        User user = new User(1L, "John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "encryptedPassword", "+1234567890", new Role(1L, "ROLE_ADMIN"));
        UserEntity userEntity = new UserEntity(1L, "John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "encryptedPassword", "+1234567890", new RoleEntity(1L, "ROLE_ADMIN", null));

        when(userJpaRepo.findById(1L)).thenReturn(Optional.of(userEntity));
        when(userJpaMapper.toDomain(any(UserEntity.class))).thenReturn(user);

        Optional<User> result = userJpaOut.findUserById(1L);

        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().getEmail());
        verify(userJpaRepo, times(1)).findById(1L);
        verify(userJpaMapper, times(1)).toDomain(any(UserEntity.class));
    }

    @Test
    void testFindUserByEmail_UserExists() {
        User user = new User(1L, "John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "encryptedPassword", "+1234567890", new Role(1L, "ROLE_ADMIN"));
        UserEntity userEntity = new UserEntity(1L, "John", "Doe", "123456", LocalDate.of(1990, 5, 15), "john.doe@example.com", "encryptedPassword", "+1234567890", new RoleEntity(1L, "ROLE_ADMIN", null));

        when(userJpaRepo.findByEmail("john.doe@example.com")).thenReturn(Optional.of(userEntity));
        when(userJpaMapper.toDomain(any(UserEntity.class))).thenReturn(user);

        Optional<User> result = userJpaOut.findUserByEmail("john.doe@example.com");

        assertTrue(result.isPresent());
        assertEquals(user.getEmail(), result.get().getEmail());
        verify(userJpaRepo, times(1)).findByEmail("john.doe@example.com");
        verify(userJpaMapper, times(1)).toDomain(any(UserEntity.class));
    }

    @Test
    void testExistsBycc_True() {
        when(userJpaRepo.existsBycc("123456")).thenReturn(true);

        boolean exists = userJpaOut.existsBycc("123456");

        assertTrue(exists);
        verify(userJpaRepo, times(1)).existsBycc("123456");
    }

    @Test
    void testFindRoleById_RoleExists() {
        Role role = new Role(1L, "ROLE_ADMIN");
        RoleEntity roleEntity = new RoleEntity(1L, "ROLE_ADMIN", null);

        when(roleJpaRepo.findById(1L)).thenReturn(Optional.of(roleEntity));
        when(userJpaMapper.toDomainRole(any(RoleEntity.class))).thenReturn(role);

        Optional<Role> result = userJpaOut.findRoleById(1L);

        assertTrue(result.isPresent());
        assertEquals(role.getName(), result.get().getName());
        verify(roleJpaRepo, times(1)).findById(1L);
        verify(userJpaMapper, times(1)).toDomainRole(any(RoleEntity.class));
    }
}