package user.application.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import user.application.utils.Constants;

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
    @NotBlank(message = Constants.NAME_NOT_BLANK)
    private String name;
    @NotBlank(message = Constants.LASTNAME_NOT_BLANK)
    private String lastName;
    @NotBlank(message = Constants.DOCUMENT_NOT_BLANK)
    @Pattern(regexp = "\\d+", message = Constants.DOCUMENT_NUMERIC)
    private String cc;
    @NotNull(message = Constants.BIRTHDATE_NOT_NULL)
    @Past(message = Constants.BIRTHDATE_PAST)
    private LocalDate birthDate;
    @NotBlank(message = Constants.EMAIL_NOT_BLANK)
    @Email(message = Constants.EMAIL_VALID)
    private String email;
    @NotBlank(message = Constants.PASSWORD_NOT_BLANK)
    private String password;
    @NotBlank(message = Constants.PHONE_NOT_BLANK)
    @Pattern(regexp = "^\\+?\\d{1,13}$", message = Constants.PHONE_PATTERN)
    private String phone;
    @NotNull(message = Constants.IDROLE_NOT_NULL)
    private Long idRole;

}
