package user.application.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTORequest {
    @NotBlank(message = "El nombre no puede estar vacío.")
    private String name;
    @NotBlank(message = "El apellido no puede estar vacío.")
    private String lastName;
    @NotBlank(message = "El documento de identidad no puede estar vacío.")
    @Pattern(regexp = "\\d+", message = "El documento de identidad debe ser numérico.")
    private String cc;
    @NotNull(message = "La fecha de nacimiento no puede estar vacía.")
    @Past(message = "La fecha de nacimiento debe ser en el pasado.")
    private LocalDate birthDate;
    @NotBlank(message = "El correo no puede estar vacío.")
    @Email(message = "Debe proporcionar un correo electrónico válido.")
    private String email;
    @NotBlank(message = "La contraseña no puede estar vacía.")
    private String password;
    @NotBlank(message = "El teléfono no puede estar vacío.")
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = "El teléfono debe contener un máximo de 13 caracteres y puede contener el símbolo +.")
    private String phone;
    @NotNull(message = "El ID del rol no puede estar vacío.")
    private Long idRole;
}
