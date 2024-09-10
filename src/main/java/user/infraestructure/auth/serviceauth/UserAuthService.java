package user.infraestructure.auth.serviceauth;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import user.domain.model.User;
import user.domain.ports.spi.UserOut;
import user.infraestructure.utils.Constants;

import java.util.Collections;
@Service
@AllArgsConstructor
public class UserAuthService implements UserDetailsService {
    private final UserOut userOut;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userOut.findUserByEmail(email)
                .orElseThrow(() -> {
                    System.err.println(Constants.USER_NOT_FOUND + email);
                    return new UsernameNotFoundException(Constants.USER_NOT_FOUND + email);
                });
        if (user.getPassword() == null) {
            System.err.printf((Constants.PASSWORD_NULL) + "%n", email);
        } else {
            System.out.printf((Constants.PASSWORD_RETRIEVED) + "%n", email, user.getPassword());
        }
        if (user.getRole() == null) {
            System.err.printf((Constants.ROLE_NULL) + "%n", email);
        } else {
            System.out.printf((Constants.ROLE_RETRIEVED) + "%n", email, user.getRole().getName());
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole().getName()))
        );
    }
}