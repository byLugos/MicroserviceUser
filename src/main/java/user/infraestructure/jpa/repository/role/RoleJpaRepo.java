package user.infraestructure.jpa.repository.role;
import user.infraestructure.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface RoleJpaRepo extends JpaRepository<RoleEntity,Long> {
    Optional<RoleEntity> findByName(String nombre);
}
