package user.infraestructure.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user.application.dto.request.UserDTORequest;
import user.application.dto.response.UserDTOResponse;
import user.application.handler.UserHandler;
@RestController
@RequestMapping("/createEmployes")
@AllArgsConstructor
@Tag(name = "User Management", description = "Endpoints para la gestión de usuarios")
public class UserController {
    private final UserHandler userHandler;
    @PostMapping
    @Operation(summary = "Crear un nuevo empleado", description = "Crea un nuevo empleado en el sistema con los detalles proporcionados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTOResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida, error de validación",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<UserDTOResponse> createUser(@RequestBody UserDTORequest userDTORequest) {
        UserDTOResponse createdUser = userHandler.createUser(userDTORequest);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
