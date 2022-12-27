package S05T02N01.DiceGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.dto.GameDTO;
import S05T02N01.DiceGame.model.repository.GameRepository;
import S05T02N01.DiceGame.model.services.IGameService;


@Controller
@RequestMapping("/game")
public class GameController {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	GameRepository gameRepository;

	@GetMapping("/")
	public String listAll(Model model) {
		List<GameDTO> llistatGames = gameService.listAll();
		model.addAttribute("Titol", "Llista de Games");
		model.addAttribute("games", llistatGames);
		return "/game/list";
	}
	
	@GetMapping("/add")
	public String create(Model model) {
		Game game = new Game();
		model.addAttribute("titol", "Formulari: Nova game");
		model.addAttribute("game", game);
		return "/game/create";
	}
	
	@PostMapping("/save")
	public String save(Game game) {
		System.out.println(game);
		gameService.saveOne(game);
		System.out.println(game);
		return "redirect:/game/";
	}
	
	@GetMapping("/update/{pk_GameID}")
	public String edit(@PathVariable("pk_GameID") int pk_GameID, Model model) {
		Game game = gameService.findByID(pk_GameID);
		model.addAttribute("game", game);
		System.out.println(game);
		return "/game/edit";
	}
	
	@GetMapping("/delete/{pk_GameID}")
	public String remove(@PathVariable int pk_GameID) {
		gameService.deleteOne(pk_GameID);
		return "redirect:/game/";
	}
}
