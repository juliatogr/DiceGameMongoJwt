package S05T02N01.DiceGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.repository.PlayerRepository;
import S05T02N01.DiceGame.model.services.IPlayerService;


@Controller
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private IPlayerService playerService;
	
	@Autowired
	PlayerRepository playerRepository;

	@GetMapping("/")
	public String listAll(Model model) {
		List<Player> llistatPlayers = playerService.listAll();
		model.addAttribute("Titol", "Llista de Players");
		model.addAttribute("players", llistatPlayers);
		return "/player/list";
	}
	
	@GetMapping("/add")
	public String create(Model model) {
		Player player = new Player();
		model.addAttribute("titol", "New Player");
		model.addAttribute("player", player);
		return "/player/create";
	}
	
	@PostMapping("/save")
	public String save(Player player) {
		System.out.println(player);
		playerService.saveOne(player);
		System.out.println(player);
		return "redirect:/player/";
	}
	
	@GetMapping("/update/{playerId}")
	public String edit(@PathVariable("playerId") int pk_PlayerID, Model model) {
		Player player = playerService.findByID(pk_PlayerID);
		model.addAttribute("player", player);
		System.out.println(player);
		return "/player/edit";
	}
	
	@GetMapping("/delete/{playerId}")
	public String remove(@PathVariable int pk_PlayerID) {
		playerService.deleteOne(pk_PlayerID);
		return "redirect:/player/";
	}
}
