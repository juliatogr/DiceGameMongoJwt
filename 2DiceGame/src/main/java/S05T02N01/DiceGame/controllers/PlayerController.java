package S05T02N01.DiceGame.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.repository.PlayerRepository;
import S05T02N01.DiceGame.model.services.IPlayerService;


@Controller
public class PlayerController {

	@Autowired
	private IPlayerService playerService;

	@GetMapping({"/","/players"})
	public String listAll(Model model) {
		List<Player> llistatPlayers = playerService.listAll();
		model.addAttribute("Titol", "Llista de Players");
		model.addAttribute("players", llistatPlayers);
		return "/player/player_list";
	}
	
	@GetMapping("/players/add")
	public String create(Model model) {
		Player player = new Player();
		model.addAttribute("titol", "New Player");
		model.addAttribute("player", player);
		return "/player/create";
	}
	
	@PostMapping("/players/save")
	public String save(Player player) {
		System.out.println(player);
		playerService.saveOne(player);
		System.out.println(player);
		return "redirect:/players";
	}
	
	@GetMapping("/players/update/{playerId}")
	public String edit(@PathVariable("playerId") int playerId, Model model) {
		Player player = playerService.findByID(playerId);
		model.addAttribute("player", player);
		System.out.println(player);
		return "/player/edit";
	}
	
	@GetMapping("/players/delete/{playerId}")
	public String remove(@PathVariable int playerId) {
		playerService.deleteOne(playerId);
		return "redirect:/players";
	}
	
	@GetMapping("/players/{playerId}/games")
	public String listGames(@PathVariable("playerId") int pk_PlayerID, Model model) {
		Player currentPlayer = playerService.findByID(pk_PlayerID);
		model.addAttribute("player", currentPlayer);
		model.addAttribute("player_id", pk_PlayerID);
		return "/game/game_list";
	}
	
	@GetMapping("/players/{playerId}/games/add")
	public String createGame(@PathVariable("playerId") int pk_PlayerID, Model model) {
	
		Player currentPlayer = playerService.findByID(pk_PlayerID);
		model.addAttribute("player", currentPlayer);
		
		Game game = new Game();
		currentPlayer.getGames().add(game);
		
		model.addAttribute("titol", "New Game");
		model.addAttribute("game", game);
		return "/game/create_game";
	}
	
	@PostMapping("/players/{playerId}/games/save")
	public String saveGame(@PathVariable("playerId") int pk_PlayerID, Game game) {
		System.out.println(game);
		playerService.findByID(pk_PlayerID).getGames()
		return "redirect:/players";
	}
	
	@GetMapping("/players/{playerId}/roll")
	public String roll(@PathVariable("playerId") int pk_PlayerID, Model model) {
		Player player = playerService.findByID(pk_PlayerID);
		model.addAttribute("player", player);
		System.out.println(player);
		return "redirect:/players/{playerId}/games";
	}
	

}
