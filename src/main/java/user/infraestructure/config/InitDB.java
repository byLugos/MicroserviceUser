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
import user.infraestructure.utils.Constants;

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
            RoleEntity adminRole = roleJpaRepo.findByName(Constants.ROLE_ADMIN)
                    .orElseGet(() -> {
                        RoleEntity newRole = new RoleEntity();
                        newRole.setName(Constants.ROLE_ADMIN);
                        return roleJpaRepo.save(newRole);
                    });

            if (userJpaRepo.findByEmail(Constants.ADMIN_EMAIL).isEmpty()) {
                String encodedPassword = passwordEncoder.encode(Constants.ADMIN_PASSWORD);
                userJpaRepo.save(new UserEntity(
                        null,
                        Constants.ADMIN_FIRST_NAME,
                        Constants.ADMIN_LAST_NAME,
                        Constants.ADMIN_CC,
                        LocalDate.of(Constants.ADMIN_BIRTH_YEAR, Constants.ADMIN_BIRTH_MONTH, Constants.ADMIN_BIRTH_DAY),
                        Constants.ADMIN_EMAIL,
                        encodedPassword,
                        Constants.ADMIN_PHONE,
                        adminRole
                ));

                System.out.printf((Constants.ADMIN_CREATION_SUCCESS) + "%n", Constants.ADMIN_EMAIL, Constants.ADMIN_PASSWORD);
            } else {
                System.out.println(Constants.ADMIN_ALREADY_EXISTS);
            }
        };
    }
}