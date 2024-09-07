package user.domain.service;
import user.domain.exceptions.InvalidDocumentException;
import user.domain.exceptions.InvalidExistantUserException;
import user.domain.exceptions.InvalidRoleException;
import user.domain.model.Role;
import user.domain.model.User;
import user.domain.ports.api.UserIn;
import user.domain.validations.ValidateUser;
import user.domain.ports.spi.UserOut;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
public class UserService implements UserIn {
    private final UserOut userOut;
    public UserService(UserOut userOut) {
        this.userOut = userOut;
    }
    @Override
    public User createUser(Long id, String name, String lastName, String cc, LocalDate birthDate, String email, String password, String phone, Long idRole) {
        ValidateUser.validateName(name, lastName);
        ValidateUser.validateDocument(cc);
        ValidateUser.validateEmail(email);
        ValidateUser.validatePhone(phone);
        ValidateUser.calculateAge(birthDate);
        ValidateUser.validateAge(birthDate);
        if (userOut.findUserByEmail(email).isPresent()) {
            throw new InvalidExistantUserException("El usuario ya existe con el email proporcionado.");
        }
        if (userOut.existsBycc(cc)) {
            throw new InvalidDocumentException("El documento de identidad ya se encuentra registrado");
        }
        Optional<Role> role = userOut.findRoleById(idRole);
        if (role.isEmpty() || (!Objects.equals(role.get().getName(), "ROLE_AUXBODEGA") && !Objects.equals(role.get().getName(), "ROLE_CLIENTE"))) {
            String adviseRole = "El rol solicitado no existe o el rol no es un 'AUX_BODEGA o 'CLIENTE'";
            throw new InvalidRoleException(adviseRole);
        }
        User user = new User(null, name, lastName, cc, birthDate, email, password, phone, role.get());
        return userOut.saveUser(user);
    }

}
