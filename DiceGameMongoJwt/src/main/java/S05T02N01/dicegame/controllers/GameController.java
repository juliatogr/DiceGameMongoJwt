package S05T02N01.dicegame.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.RegisteredUser;
import S05T02N01.dicegame.model.domain.User;
import S05T02N01.dicegame.model.dto.GameDTO;
import S05T02N01.dicegame.model.dto.UserDTO;
import S05T02N01.dicegame.model.services.UserService;

@RestController
public class GameController {

	@Autowired
	private UserService userService;

	@GetMapping("/players")
	public ResponseEntity<List<UserDTO>> listAll() {

		try {
			List<UserDTO> players = new ArrayList<UserDTO>();

			userService.listAll().forEach(players::add);
			return new ResponseEntity<>(players, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/players/edit")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<RegisteredUser> updatePlayer(@RequestBody RegisteredUser player) {
		RegisteredUser playerData = (RegisteredUser) userService.findById(player.getId());

		if (playerData != null) {
			RegisteredUser _player = playerData;
			_player.setUsername(player.getUsername());
			return new ResponseEntity<>((RegisteredUser) userService.saveOne(_player), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("players/ranking")
	public ResponseEntity<Double> getRanking() {
		try {
			double avgSuccessPerc = 0;
			List<UserDTO> players = userService.listAll();
			for (UserDTO p : players) {
				avgSuccessPerc += p.getSuccessPerc();
			}
			if (players.size() > 0) {
				avgSuccessPerc /= players.size();
			}

			avgSuccessPerc = Math.floor(avgSuccessPerc * 100) / 100;
			return new ResponseEntity<>(avgSuccessPerc, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("players/ranking/loser")
	public ResponseEntity<UserDTO> getLoser() {
		List<UserDTO> players = userService.listAll();

		UserDTO loser = players.get(0);
		for (UserDTO p : players) {
			;
			if (p.getSuccessPerc() < loser.getSuccessPerc()) {
				loser = p;
			}
		}

		return new ResponseEntity<>(loser, HttpStatus.OK);
	}

	@GetMapping("players/ranking/winner")
	public ResponseEntity<UserDTO> getWinner(Model model) {

		List<UserDTO> players = userService.listAll();
		UserDTO winner = players.get(0);
		for (UserDTO p : players) {
			if (p.getSuccessPerc() > winner.getSuccessPerc()) {
				winner = p;
			}
		}

		return new ResponseEntity<>(winner, HttpStatus.OK);
	}

	@GetMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<GameDTO>> listPlayerGames(@PathVariable("id") String id) {

		try {

			return new ResponseEntity<>(userService.listAllGames(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<GameDTO> saveGame(@PathVariable("id") String id) {

		try {
			// Get player
			User player = userService.findById(id);

			// init game
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

	@DeleteMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<UserDTO> deleteGames(@PathVariable("id") String id) {

		try {
			// Get player
			User player = userService.findById(id);

			player.setGames(new ArrayList<Game>());
			userService.saveOne(player);
			return new ResponseEntity<>(userService.convertUserToDTO(player), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
