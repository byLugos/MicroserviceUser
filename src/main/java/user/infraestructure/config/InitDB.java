package user.infraestructure.config;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import user.infraestructure.jpa.entity.RoleEntity;
import user.infraestructure.jpa.entity.UserEntity;
import user.infraestructure.jpa.repository.role.RoleJpaRepo;
import user.infraestructure.jpa.repository.user.UserJpaRepo;
import java.time.LocalDate;
@Configuration
@RequiredArgsConstructor
public class InitDB {
    private final UserJpaRepo userJpaRepo;
    private final RoleJpaRepo roleJpaRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    @Bean
    CommandLineRunner initAdminUser() {
        return args -> {
            RoleEntity adminRole = roleJpaRepo.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        RoleEntity newRole = new RoleEntity();
                        newRole.setName("ROLE_ADMIN");
                        return roleJpaRepo.save(newRole);
                    });
            if (userJpaRepo.findByEmail("admin123@ejemplo.com").isEmpty()) {
                String encodedPassword = passwordEncoder.encode("password123");
                userJpaRepo.save(new UserEntity(
                        null,
                        "Admin",
                        "R",
                        "12345",
                        LocalDate.of(1990, 5, 15),
                        "admin123@ejemplo.com",
                        encodedPassword,
                        "5551234567",
                        adminRole
                ));

                System.out.println("Administrador creado con éxito con email: admin123@ejemplo.com y contraseña: password123");
            } else {
                System.out.println("El usuario administrador ya existe. No se creó un nuevo administrador.");
            }
        };
    }
}