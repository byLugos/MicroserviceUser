package user.application.mapper;
import org.springframework.stereotype.Component;
import user.application.dto.request.UserDTORequest;
import user.application.dto.response.UserDTOResponse;
import user.domain.model.Role;
import user.domain.model.User;
@Component
public class UserMapper {
    public User toDomain(UserDTORequest createUserRequest, Role role) {
        if (createUserRequest == null || role == null) {
            return null;
        }
        User user = new User();
        user.setName(createUserRequest.getName());
        user.setLastName(createUserRequest.getLastName());
        user.setCc(createUserRequest.getCc());
        user.setBirthDate(createUserRequest.getBirthDate());
        user.setEmail(createUserRequest.getEmail());
        user.setPassword(createUserRequest.getPassword());
        user.setPhone(createUserRequest.getPhone());
        user.setRole(role);
        return user;
    }
    public UserDTOResponse toDto(User user) {
        if (user == null) {
            return null;
        }
        UserDTOResponse userDTOResponse = new UserDTOResponse();
        userDTOResponse.setId(user.getId());
        userDTOResponse.setName(user.getName());
        userDTOResponse.setLastName(user.getLastName());
        userDTOResponse.setCc(user.getCc());
        userDTOResponse.setBirthDate(user.getBirthDate());
        userDTOResponse.setEmail(user.getEmail());
        userDTOResponse.setPhone(user.getPhone());
        userDTOResponse.setRole(user.getRole() != null ? user.getRole().getName() : null);
        return userDTOResponse;
    }
}
