package S05T02N01.DiceGame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping({"/index", "/home", "/"})
	public String index() {
		return "home";
	}

}
