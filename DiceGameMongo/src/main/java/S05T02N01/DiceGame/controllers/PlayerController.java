package S05T02N01.DiceGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.services.IPlayerService;


@Controller
public class PlayerController {

	@Autowired
	private IPlayerService playerService;

	@GetMapping({"/", "/players"})
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
		return "/player/create_player";
	}
	
	@PostMapping("/players/save")
	public String save(Player player) {
		if (player.getName()=="") {
			player.setName("ANONIMOUS");
		} 
		if (player.getName().equalsIgnoreCase("Anonimous") 
				|| playerService.findByName(player.getName()) == null) {
			Player.idIncrement++;
			player.setPlayerId(Player.idIncrement);
			playerService.saveOne(player);
		}
		
		return "redirect:/players";
	}
	
	@GetMapping("/players/update/{playerId}")
	public String edit(@PathVariable("playerId") int playerId, Model model) {
		Player player = playerService.findByID(playerId);
		model.addAttribute("player", player);
		return "/player/edit";
	}
	
	@GetMapping("/players/delete/{playerId}")
	public String remove(@PathVariable int playerId) {
		playerService.deleteOne(playerId);
		return "redirect:/players";
	}

	

}
