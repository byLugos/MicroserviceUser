package user.infraestructure.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import user.domain.ports.api.UserIn;
import user.domain.ports.spi.UserOut;
import user.domain.service.UserService;
@Configuration
@RequiredArgsConstructor
public class AppConfig {
    @Bean
    public UserIn userService(UserOut userOut) {
        return new UserService(userOut);
    }
}
