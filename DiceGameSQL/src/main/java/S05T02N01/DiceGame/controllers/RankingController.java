package S05T02N01.DiceGame.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.domain.Roll;
import S05T02N01.DiceGame.model.dto.RollDTO;
import S05T02N01.DiceGame.model.services.IGameService;
import S05T02N01.DiceGame.model.services.IPlayerService;
import S05T02N01.DiceGame.model.services.IRollService;

import org.springframework.ui.Model;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class RankingController {
	
	@Autowired
	private IPlayerService playerService;
	
	@Autowired
	private IGameService gameService;
	
	
	@GetMapping("/ranking")
	public String getRanking(Model model) {
		double avgSuccessPerc = 0;
		List<Player> players = playerService.listAll();
		for (Player p : players ) {
			avgSuccessPerc += p.getAvgSuccessPerc();
		}
		if (players.size() > 0) {
			avgSuccessPerc /= players.size();
		}
		
		avgSuccessPerc = Math.floor(avgSuccessPerc * 100)/100;
		model.addAttribute("players", players);
		model.addAttribute("totalAvgSuccessPerc", avgSuccessPerc);
		return "/ranking/avg_success_perc";
	}
	
	@GetMapping("/ranking/loser")
	public String getLoser(Model model) {
		Player loser = new Player();
		loser.setAvgSuccessPerc(100);
		List<Player> players = playerService.listAll();
		for (Player p : players) {;
			if (p.getAvgSuccessPerc() <= loser.getAvgSuccessPerc()) {
				loser = p;
			}
		}
		model.addAttribute("loser", loser);
		return "/ranking/loser";
	}
	
	@GetMapping("/ranking/winner")
	public String getWinner(Model model) {
		Player winner = new Player();
		winner.setAvgSuccessPerc(0);
		List<Player> players = playerService.listAll();
		for (Player p : players) {
			if (p.getAvgSuccessPerc() >= winner.getAvgSuccessPerc()) {
				winner = p;
			}
		}
		model.addAttribute("winner", winner);
		return "/ranking/winner";
	}
	
}
