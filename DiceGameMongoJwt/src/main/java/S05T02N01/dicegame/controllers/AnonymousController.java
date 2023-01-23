package S05T02N01.dicegame.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.User;
import S05T02N01.dicegame.model.dto.GameDTO;
import S05T02N01.dicegame.model.dto.UserDTO;
import S05T02N01.dicegame.model.services.UserService;


@RestController
public class AnonymousController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/players/anonymous/{id}/games")
	public ResponseEntity<List<GameDTO>> listPlayerGames(@PathVariable("id") String id) {
		
		try {
						
			return new ResponseEntity<>( userService.listAllGames(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/players/anonymous/{id}/games")
	public ResponseEntity<GameDTO> saveGame(@PathVariable("id") String id) {
		
		try {
			//Get player
			User player = userService.findById(id);
			
			//init game
			Game game = new Game();
			
			// Update games on user
			List<Game> games = player.getGames();
			games.add(game);
			player.setGames(games);
			userService.saveOne(player);
			return new ResponseEntity<>(userService.convertGameToDTO(game), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/players/anonymous/{id}/games")
	public ResponseEntity<UserDTO> deleteGames(@PathVariable("id") String id) {
		
		try {
			//Get player
			User player = userService.findById(id);

			player.setGames(new ArrayList<Game>());
			userService.saveOne(player);
			return new ResponseEntity<>(userService.convertUserToDTO(player), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	

}
