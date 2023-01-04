package S05T02N01.DiceGame.security.config;

import S05T02N01.DiceGame.security.domain.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collections;

@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt jwt) {
        User user = new User();
        user.setId(jwt.getSubject());
        return new UsernamePasswordAuthenticationToken(user, jwt, Collections.EMPTY_LIST);
    }
}
