package S05T02N01.dicegame.security.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import S05T02N01.dicegame.security.login.LoginCheck;
import S05T02N01.dicegame.security.login.LoginUser;
import S05T02N01.dicegame.security.services.JWTService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SampleController {
    @Autowired
    private JWTService jwtService;

    @GetMapping("/")
    public String rootPage() {
        return "redirect:/main";
    }

    @GetMapping("login")
    public void loginPage() {
    }

    @PostMapping("login")
    public String login(@RequestParam String id, @RequestParam String pwd, HttpServletResponse res) {

        // token
        Map<String, Object> user = new HashMap<>();
        user.put("id", id);
        user.put("name", "user");

        Cookie cookie = new Cookie(LoginCheck.COOKIE_NAME,
                jwtService.token(user, Optional.of(LocalDateTime.now().plusMinutes(30))));

        cookie.setPath("/");
        cookie.setMaxAge(Integer.MAX_VALUE);

        res.addCookie(cookie);

        return "redirect:/main";
    }

    /**
     * jwt
     */
    @GetMapping
    public String logout(HttpServletResponse res) {
        Cookie cookie = new Cookie(LoginCheck.COOKIE_NAME, "");
        cookie.setPath("/");
        cookie.setMaxAge(0);

        res.addCookie(cookie);

        return "redirect:/login";
    }

    @GetMapping("main")
    public void mainPage(Model model, @LoginUser String id) {
        log.info("id : {}", id);
    }
}
