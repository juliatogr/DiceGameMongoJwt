package S05T02N01.dicegame.security.login;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import S05T02N01.dicegame.model.domain.User;
import S05T02N01.dicegame.security.services.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 여부 체크 인터셉터
 */
@Component
@Slf4j
public class LoginCheck implements HandlerInterceptor {
    public static final String COOKIE_NAME = "login_token";

    @Autowired
    private JWTService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws ModelAndViewDefiningException {

        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(LoginCheck.COOKIE_NAME)).findFirst().map(Cookie::getValue)
                .orElse("not valid");

        log.info("token : {}", token);

        try {
            Map<String, Object> info = jwtService.verify(token);

            // View session.id request verify
            User user = User.builder().id((String) info.get("id")).username((String) info.get("name")).build();

            // view 에서 login.id 로 접근가능
            request.setAttribute("login", user);
        } catch (ExpiredJwtException ex) {
            log.error("Expired Jwt");

            ModelAndView mav = new ModelAndView("login");
            mav.addObject("return_url", request.getRequestURI());

            throw new ModelAndViewDefiningException(mav);
        } catch (JwtException ex) {
            log.error("Jwt Exception");

            ModelAndView mav = new ModelAndView("login");

            throw new ModelAndViewDefiningException(mav);
        }

        return true;
    }
}
