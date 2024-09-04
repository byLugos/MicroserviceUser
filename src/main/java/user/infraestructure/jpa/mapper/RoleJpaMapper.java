package user.infraestructure.jpa.mapper;
import user.domain.model.Role;
import user.infraestructure.jpa.entity.RoleEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface RoleJpaMapper {
    RoleEntity toEntity(Role role);
    Role toDomain(RoleEntity roleEntity);
}
