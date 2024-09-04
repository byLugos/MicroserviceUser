package user.application.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import user.application.dto.request.UserDTORequest;
import user.application.dto.response.UserDTOResponse;
import user.domain.model.Role;
import user.domain.model.User;
@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "createUserRequest.name")
    @Mapping(target = "lastName", source = "createUserRequest.lastName")
    @Mapping(target = "cc", source = "createUserRequest.cc")
    @Mapping(target = "birthDate", source = "createUserRequest.birthDate")
    @Mapping(target = "email", source = "createUserRequest.email")
    @Mapping(target = "password", source = "createUserRequest.password")
    @Mapping(target = "phone", source = "createUserRequest.phone")
    @Mapping(target = "role", source = "role")
    User toDomain(UserDTORequest createUserRequest, Role role);
    @Mapping(target = "role", source = "role.name")
    UserDTOResponse toDto(User user);
}
