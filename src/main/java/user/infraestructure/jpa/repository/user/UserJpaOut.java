package user.infraestructure.jpa.repository.user;

import org.hibernate.Hibernate;
import org.springframework.transaction.annotation.Transactional;
import user.domain.model.Role;
import user.domain.model.User;
import user.domain.ports.spi.UserOut;
import user.infraestructure.jpa.entity.RoleEntity;
import user.infraestructure.jpa.entity.UserEntity;
import user.infraestructure.jpa.mapper.UserJpaMapper;
import user.infraestructure.jpa.repository.role.RoleJpaRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserJpaOut implements UserOut {
    private final UserJpaRepo userJpaRepo;
    private final RoleJpaRepo roleJpaRepo;
    private final UserJpaMapper userJpaMapper;
    @Override
    public boolean existsById(Long id) {
        return userJpaRepo.existsById(id);
    }
    @Override
    @Transactional
    public User saveUser(User user) {
        UserEntity userEntity = userJpaMapper.toEntity(user);

        UserEntity savedUserEntity = userJpaRepo.save(userEntity);
        Hibernate.initialize(savedUserEntity.getRole());

        return userJpaMapper.toDomain(savedUserEntity);
    }
    @Override
    public Optional<User> findUserById(Long id) {
        Optional<UserEntity> userEntityOpt = userJpaRepo.findById(id);
        return userEntityOpt.map(userJpaMapper::toDomain);
    }
    @Override
    public List<User> findAllUsers() {
        List<UserEntity> userEntities = userJpaRepo.findAll();
        return userEntities.stream().map(userJpaMapper::toDomain).collect(Collectors.toList());
    }
    @Override
    public Optional<Role> findRoleById(Long id) {
        Optional<RoleEntity> roleEntityOpt = roleJpaRepo.findById(id);
        return roleEntityOpt.map(userJpaMapper::toDomainRole);
    }
    @Override
    public Optional<User> findUserByEmail(String username) {
        Optional<UserEntity> userEntityOpt = userJpaRepo.findByEmail(username);

        User user = userEntityOpt.map(userJpaMapper::toDomain).orElse(null);
        return Optional.ofNullable(user);
    }
    @Override
    public boolean existsBycc(String cc) {
        return userJpaRepo.existsBycc(cc);
    }
}
