package user.domain.ports.api;
import user.domain.model.User;

import java.time.LocalDate;

public interface UserIn {
    User createUser(Long id, String name, String lastName, String cc, LocalDate birthDate, String email, String password, String phone, Long idRole);
}
