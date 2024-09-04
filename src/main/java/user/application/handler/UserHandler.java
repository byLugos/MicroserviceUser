package user.application.handler;
import user.application.dto.request.UserDTORequest;
import user.application.dto.response.UserDTOResponse;
import user.application.mapper.UserMapper;
import user.domain.model.Role;
import user.domain.model.User;
import user.domain.ports.api.UserIn;
import user.domain.ports.spi.UserOut;
import user.domain.exceptions.InvalidRoleException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
@AllArgsConstructor
public class UserHandler {
    private final UserIn userService;
    private final UserOut userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    public UserDTOResponse createUser(UserDTORequest request) {
        Role role = userRepository.findRoleById(request.getIdRole())
                .orElseThrow(() -> new InvalidRoleException("El rol solicitado no existe"));
        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        User user = userMapper.toDomain(request, role);
        user.setPassword(encryptedPassword);
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
        return userMapper.toDto(createdUser);
    }
}
