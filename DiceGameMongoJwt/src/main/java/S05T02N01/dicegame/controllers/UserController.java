package S05T02N01.dicegame.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import S05T02N01.dicegame.model.services.IUserService;
import S05T02N01.dicegame.security.models.User;


@RestController
//@Controller
public class UserController {

	@Autowired
	private IUserService playerService;

//	@GetMapping("/players")
//	public String listAll(Model model) {
//		List<Player> llistatPlayers = playerService.listAll();
//		model.addAttribute("Titol", "Llista de Players");
//		model.addAttribute("players", llistatPlayers);
//		return "/player/player_list";
//	}
//	
//	@GetMapping("/players/add")
//	public String create(Model model) {
//		Player player = new Player();
//		model.addAttribute("titol", "New Player");
//		model.addAttribute("player", player);
//		return "/player/create_player";
//	}
//	
//	@PostMapping("/players/save")
//	public String save(Player player) {
//		if (player.getName()=="") {
//			player.setName("ANONIMOUS");
//		} 
//		if (player.getName().equalsIgnoreCase("Anonimous") 
//				|| playerService.findByName(player.getName()) == null) {
//			Player.idIncrement++;
//			player.setPlayerId(Player.idIncrement);
//			playerService.saveOne(player);
//		}
//		
//		return "redirect:/players";
//	}
//	
//	@GetMapping("/players/update/{id}")
//	public String edit(@PathVariable("id") int id, Model model) {
//		Player player = playerService.findByID(id);
//		model.addAttribute("player", player);
//		return "/player/edit";
//	}
//	
//	@GetMapping("/players/delete/{id}")
//	public String remove(@PathVariable int id) {
//		playerService.deleteOne(id);
//		return "redirect:/players";
//	}

	@GetMapping({"/", "/players"})
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<List<User>> listAll() {
		
		try {
			List<User> players = new ArrayList<User>();
		
			playerService.listAll().forEach(players::add);
		
			return new ResponseEntity<>(players, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
		
	@GetMapping("/players/update")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> updatePlayer(@RequestBody User player) {
		Optional<User> p = playerService.findById(player.getId());
		User playerData = p.isPresent() ? p.get(): null;

		if (playerData != null) {
			User _player = playerData;
			_player.setUsername(player.getUsername());
			return new ResponseEntity<>(playerService.saveOne(_player), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}


}
