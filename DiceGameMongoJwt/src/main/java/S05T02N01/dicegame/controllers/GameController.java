package S05T02N01.dicegame.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	private IUserService userService;
	
	private Game lastGame;

//	@GetMapping("/players/{id}/games")
//	public String listPlayerGames(@PathVariable("id") int pk_PlayerID, Model model) {
//		List<Game> llistatGames = gameService.listAllPlayer(pk_PlayerID);
//		model.addAttribute("Titol", "Game's list");
//		model.addAttribute("playerGames", llistatGames);
//		return "/game/player_game_list";
//	}
//	
//	@PostMapping("/players/{id}/games")
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
	
	@GetMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<Game>> listPlayerGames(@PathVariable("id") String id) {
		
		try {
			
		
			Optional<User> u = userService.findById(id);
			User player = u.isPresent()? u.get() : null;
			
			List<Game> games = new ArrayList<>();
						
			player.getGames().forEach(games::add);
			System.out.println(games);
			return new ResponseEntity<>(games, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Game> saveGame(@PathVariable("id") String id) {
		
		try {
			//Get player
			Optional<User> p = userService.findById(id);
			User player = p.isPresent()?p.get():null;
			
			// Prepare lastGame

			if (lastGame == null || lastGame.isFinished()) {
				System.out.println("isEmpty or finished");
				lastGame = new Game();
			}
			
			// Roll and check if win
			Roll lastRoll = new Roll();
			
			if (lastRoll.getResult() == 7) {
				lastGame.setFinished(true);
			}
			
			// Update rolls on lastGame
			
			List<Roll> rolls = lastGame.getRolls();
			
			rolls.add(lastRoll);
			lastGame.setRolls(rolls);
			
			// Update games on user

			
			Game _game = gameService.saveOne(lastGame);
			List<Game> games = player.getGames();
			games.add(_game);
			player.setGames(games);
			userService.saveOne(player);
			return new ResponseEntity<>(_game, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
