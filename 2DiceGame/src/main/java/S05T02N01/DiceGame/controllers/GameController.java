package S05T02N01.DiceGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.services.IGameService;
import S05T02N01.DiceGame.model.services.IPlayerService;


@Controller
public class GameController {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IPlayerService playerService;

	
	@GetMapping("/players/{playerId}/games")
	public String listGames(@PathVariable("playerId") int pk_PlayerID, Model model) {
		List<Game> llistatGames = gameService.listAll();
		model.addAttribute("Titol", "Game's list");
		model.addAttribute("games", llistatGames);
		return "/game/game_list";
	}
	
	@GetMapping("/players/{playerId}/games/add")
	public String createGame(@PathVariable("playerId") int pk_PlayerID, Model model) {
		
		Game game = new Game();
		game.setPlayer(playerService.findByID(pk_PlayerID));
		
		model.addAttribute("titol", "New Game");
		model.addAttribute("game", game);
		return "/game/play";
	}
	
	@PostMapping("/players/{playerId}/games/save")
	public String saveGame(@PathVariable("playerId") int pk_PlayerID, Game game) {
		System.out.println(game);
		gameService.listAll();
		return "redirect:/players";
	}
	
	@GetMapping("/players/{playerId}/games/{gameId}/roll")
	public String roll(@PathVariable("playerId") int pk_PlayerID, 
			@PathVariable("gameId") int pk_gameID, Model model) {
		
		Game game = gameService.findByID(pk_gameID);
		model.addAttribute("game", game);
		System.out.println(game);
		return "redirect:/players/{playerId}/games";
	}
	

}
