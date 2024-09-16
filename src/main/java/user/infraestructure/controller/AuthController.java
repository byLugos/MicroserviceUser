package user.infraestructure.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user.infraestructure.auth.serviceauth.AuthService;
import user.infraestructure.controller.dtojwt.JwtResponse;
import user.infraestructure.controller.dtojwt.LoginRequest;
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Tag(name = "Authentication", description = "Endpoints para la autenticación de usuarios")
public class AuthController {

    private final AuthService authInfrastructureService;

    @PostMapping("/login")
    @Operation(summary = "Autenticar usuario", description = "Autentica un usuario basado en las credenciales proporcionadas y devuelve un token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Autenticación exitosa, devuelve el token JWT"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    })
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authInfrastructureService.authenticateUser(loginRequest);
    }
}