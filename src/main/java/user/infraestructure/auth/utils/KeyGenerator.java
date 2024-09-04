package user.infraestructure.auth.utils;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
public class KeyGenerator {
    public static void main(String[] args) {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64EncodedKey = Encoders.BASE64.encode(key.getEncoded());
        System.out.println("Clave HS512 segura: " + base64EncodedKey);
    }
}
