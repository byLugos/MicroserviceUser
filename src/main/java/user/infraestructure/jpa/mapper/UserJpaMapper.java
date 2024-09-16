package user.infraestructure.jpa.mapper;

import user.domain.model.Role;
import user.domain.model.User;
import user.infraestructure.jpa.entity.RoleEntity;
import user.infraestructure.jpa.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserJpaMapper {

    default UserEntity toEntity(User user) {
        if (user == null) return null;
        UserEntity entity = new UserEntity();
        entity.setId(user.getId());
        entity.setName(user.getName());
        entity.setLastName(user.getLastName());
        entity.setCc(user.getCc());
        entity.setBirthDate(user.getBirthDate());
        entity.setEmail(user.getEmail());
        entity.setPassword(user.getPassword());
        entity.setPhone(user.getPhone());
        entity.setRole(toEntityRole(user.getRole()));
        return entity;
    }

    default User toDomain(UserEntity userEntity) {
        if (userEntity == null) return null;
        User domain = new User();
        domain.setId(userEntity.getId());
        domain.setName(userEntity.getName());
        domain.setLastName(userEntity.getLastName());
        domain.setCc(userEntity.getCc());
        domain.setBirthDate(userEntity.getBirthDate());
        domain.setEmail(userEntity.getEmail());
        domain.setPassword(userEntity.getPassword());
        domain.setPhone(userEntity.getPhone());
        domain.setRole(toDomainRole(userEntity.getRole()));
        return domain;
    }

    default Role toDomainRole(RoleEntity roleEntity) {
        if (roleEntity == null) return null; // Manejo de nulo
        Role role = new Role();
        role.setId(roleEntity.getId());
        role.setName(roleEntity.getName());
        return role;
    }

    default RoleEntity toEntityRole(Role role) {
        if (role == null) return null; // Manejo de nulo
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(role.getId());
        roleEntity.setName(role.getName());
        return roleEntity;
    }
}
