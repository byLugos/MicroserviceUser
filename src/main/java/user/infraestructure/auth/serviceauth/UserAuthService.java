package user.infraestructure.auth.serviceauth;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import user.domain.model.User;
import user.domain.ports.spi.UserOut;
import java.util.Collections;

@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserOut userOut;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userOut.findUserByEmail(email)
                .orElseThrow(() -> {
                    System.err.println("Usuario no encontrado con email: " + email);
                    return new UsernameNotFoundException("Usuario no encontrado con email: " + email);
                });

        if (user.getPassword() == null) {
            System.err.println("Contraseña para el usuario con email " + email + " es null!");
        } else {
            System.out.println("Contraseña cifrada obtenida de la base de datos para " + email + ": " + user.getPassword());
        }

        if (user.getRole() == null) {
            System.err.println("Rol para el usuario con email " + email + " es null!");
        } else {
            System.out.println("Rol obtenido para el usuario con email " + email + ": " + user.getRole().getName());
        }

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }

}
