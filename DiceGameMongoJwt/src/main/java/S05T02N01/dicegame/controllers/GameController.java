package S05T02N01.dicegame.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.Roll;
import S05T02N01.dicegame.model.services.IGameService;
import S05T02N01.dicegame.model.services.IUserService;
import S05T02N01.dicegame.security.models.User;


//@Controller
@RestController
public class GameController {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IUserService playerService;

//	@GetMapping("/games")
//	public String listGames(Model model) {
//		List<Game> llistatGames = gameService.listAll();
//		model.addAttribute("Titol", "Game's list");
//		model.addAttribute("games", llistatGames);
//		return "/game/game_list";
//	}
//	
//	@GetMapping("/players/{id}/games")
//	public String listPlayerGames(@PathVariable("id") int pk_PlayerID, Model model) {
//		List<Game> llistatGames = gameService.listAllPlayer(pk_PlayerID);
//		model.addAttribute("Titol", "Game's list");
//		model.addAttribute("playerGames", llistatGames);
//		return "/game/player_game_list";
//	}
//	
//	@PostMapping("/players/{id}/games/save")
//	public String saveGame(@PathVariable("id") int pk_PlayerID, Game game, Model model) {
//		
//		Player p = playerService.findByID(pk_PlayerID);
//		game.setPlayer(p);
//		Game.idIncrement++;
//		game.setGameId(Game.idIncrement);
//		gameService.saveOne(game);
//		model.addAttribute("gameId", game.getGameId());
//
//		return "/game/rolls_list";
//	}
//	

	@GetMapping("/games")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Game>> listGames(Model model) {
		
		try {
			List<Game> games = new ArrayList<Game>();
		
			gameService.listAll().forEach(games::add);
		
			return new ResponseEntity<>(games, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Game>> listPlayerGames(@PathVariable("id") String id, Model model) {
		
		try {
			List<Game> games = new ArrayList<Game>();
		
			gameService.listAllPlayer(id).forEach(games::add);
		
			return new ResponseEntity<>(games, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/players/{id}/games/save")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Game> saveGame(@PathVariable("id") String id) {
		
		try {
			Optional<User> p = playerService.findById(id);
			User player = p.isPresent()?p.get():null;
			Game _game = gameService.saveOne(new Game(player));
			return new ResponseEntity<>(_game, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
