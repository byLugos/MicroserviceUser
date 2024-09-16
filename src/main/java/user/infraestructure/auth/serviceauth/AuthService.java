package user.infraestructure.auth.serviceauth;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import user.infraestructure.auth.JwtTokenProvider;
import user.infraestructure.controller.dtojwt.JwtResponse;
import user.infraestructure.controller.dtojwt.LoginRequest;
import user.infraestructure.utils.Constants;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public ResponseEntity<JwtResponse> authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new JwtResponse(jwt));
        } catch (Exception ex) {
            System.err.println(Constants.AUTHENTICATION_ERROR + ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new JwtResponse(Constants.INVALID_CREDENTIALS));
        }
    }
}