package user.infraestructure.config;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import org.springframework.context.annotation.Configuration;
/**
 * Configuración principal para OpenAPI para documentar el microservicio de usuarios.
 * Esta clase define la información principal que se mostrará en Swagger UI.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microservicio de Gestión de Usuarios",
                version = "1.0",
                description = "Este microservicio proporciona endpoints para la gestión de usuarios, incluyendo la creación de usuarios con autenticación.",
                contact = @Contact(name = "Soporte", email = "support@ejemplo.com"),
                license = @License(name = "MIT License", url = "https://opensource.org/licenses/MIT")
        ),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "Servidor local",
                        variables = {
                                @ServerVariable(name = "port", defaultValue = "8001", description = "Puerto del servidor")
                        }
                )
        }
)
public class OpenApiConfig {
}
