package S05T02N01.dicegame.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
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
			User currentUser = userService.getCurrentUser();
			if (currentUser != null && playerData.getId() == currentUser.getId()) {
				RegisteredUser _player = playerData;
				_player.setUsername(player.getUsername());
				return new ResponseEntity<>((RegisteredUser) userService.saveOne(_player), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("players/ranking")
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
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
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
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
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
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
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
	public ResponseEntity<List<GameDTO>> listPlayerGames(@PathVariable("id") String id) {
		
		try {
			User player = userService.findById(id);
			
			if (player != null) {

				User currentUser = userService.getCurrentUser();
				System.out.println(currentUser.getId());
				if(currentUser.getId().equals(id)) {
					return new ResponseEntity<>(userService.listAllGames(id), HttpStatus.OK);
					
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
	public ResponseEntity<GameDTO> saveGame(@PathVariable("id") String id) {

		try {
			User player = userService.findById(id);
			
			if (player != null) {

				User currentUser = userService.getCurrentUser();
				if(currentUser.getId().equals(id)) {
					Game game = new Game();

					// Update games on user
					List<Game> games = player.getGames();
					games.add(game);
					player.setGames(games);
					userService.saveOne(player);
					return new ResponseEntity<>(userService.convertGameToDTO(game), HttpStatus.CREATED);
					
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/players/{id}/games")
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
	public ResponseEntity<UserDTO> deleteGames(@PathVariable("id") String id) {
		
		try {
			User player = userService.findById(id);
			
			if (player != null) {

				User currentUser = userService.getCurrentUser();
				if(currentUser.getId().equals(id)) {
					player.setGames(new ArrayList<Game>());
					userService.saveOne(player);
					return new ResponseEntity<>(userService.convertUserToDTO(player), HttpStatus.OK);
					
				} else {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

			
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/currentuser")
	@PreAuthorize("hasRole('USER') or hasRole('ANONYMOUS')")
	public ResponseEntity<UserDTO> getCurrentUser() {

		try {
			
			UserDTO u = userService.convertUserToDTO(userService.getCurrentUser());
			System.out.println(userService.getCurrentUser().getId());
			return new ResponseEntity<>(u, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
