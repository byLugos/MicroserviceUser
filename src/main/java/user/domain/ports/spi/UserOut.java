package user.domain.ports.spi;
import user.domain.model.Role;
import user.domain.model.User;

import java.util.List;
import java.util.Optional;
public interface UserOut {
    boolean existsById(Long id);
    User saveUser(User user);
    Optional<User> findUserById(Long id);
    List<User> findAllUsers();
    Optional<Role> findRoleById(Long id);
    Optional<User> findUserByEmail(String username);
    boolean existsBycc(String cc);
}
