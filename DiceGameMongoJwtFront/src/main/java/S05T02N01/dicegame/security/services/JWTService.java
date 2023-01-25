package S05T02N01.dicegame.security.services;


import java.security.Key;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {
    // application.properties secret ?
    @Value("${site.jwt.secret}")
    private String secret;

    /**
     * body 
     * 
     * @param body
     * @param expired 
     * @return
     */
    public String token(Map<String, Object> body, Optional<LocalDateTime> expired) {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);

        Key key = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

        JwtBuilder builder = Jwts.builder().setClaims(body)
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusDays(1)))
                .signWith(SignatureAlgorithm.HS512, key);

        // expir
        expired.ifPresent(exp -> {
            builder.setExpiration(Timestamp.valueOf(exp));
        });

        return builder.compact();
    }

    /**
     * : LocalDateTime.now().plusMinutes(30) :
     * LocalDateTime.now().plusHours(1)
     * 
     * @param body
     * @return
     */
    public String token(Map<String, Object> body) {
        return token(body, Optional.of(LocalDateTime.now().plusDays(1)));
    }

    /**
     * 
     */
    public Map<String, Object> verify(String token) {
        Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token)
                .getBody();

        return new HashMap<>(claims);
    }
}