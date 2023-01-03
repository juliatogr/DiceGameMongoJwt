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

	@GetMapping("/games")
	public String listGames(Model model) {
		List<Game> llistatGames = gameService.listAll();
		model.addAttribute("Titol", "Game's list");
		model.addAttribute("games", llistatGames);
		return "/game/game_list";
	}
	
	@GetMapping("/players/{playerId}/games")
	public String listPlayerGames(@PathVariable("playerId") int pk_PlayerID, Model model) {
		List<Game> llistatGames = gameService.listAllPlayer(pk_PlayerID);
		model.addAttribute("Titol", "Game's list");
		model.addAttribute("playerGames", llistatGames);
		return "/game/player_game_list";
	}
	
	@PostMapping("/players/{playerId}/games/save")
	public String saveGame(@PathVariable("playerId") int pk_PlayerID, Game game, Model model) {
		
		Player p = playerService.findByID(pk_PlayerID);
		game.setPlayer(p);
		gameService.saveOne(game);
		model.addAttribute("gameId", game.getGameId());

		return "/game/rolls_list";
	}
	

	

}
