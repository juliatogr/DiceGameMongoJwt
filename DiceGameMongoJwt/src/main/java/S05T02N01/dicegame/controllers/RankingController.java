package S05T02N01.dicegame.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import S05T02N01.dicegame.model.services.IUserService;
import S05T02N01.dicegame.security.models.User;

import org.springframework.ui.Model;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
//@Controller
public class RankingController {
	
	@Autowired
	private IUserService playerService;
	
	
//	@GetMapping("/ranking")
//	public String getRanking(Model model) {
//		double avgSuccessPerc = 0;
//		List<Player> players = playerService.listAll();
//		for (Player p : players ) {
//			avgSuccessPerc += p.getAvgSuccessPerc();
//		}
//		if (players.size() > 0) {
//			avgSuccessPerc /= players.size();
//		}
//		
//		avgSuccessPerc = Math.floor(avgSuccessPerc * 100)/100;
//		model.addAttribute("players", players);
//		model.addAttribute("totalAvgSuccessPerc", avgSuccessPerc);
//		return "/ranking/avg_success_perc";
//	}
//	
//	@GetMapping("/ranking/loser")
//	public String getLoser(Model model) {
//		Player loser = new Player();
//		loser.setAvgSuccessPerc(100);
//		List<Player> players = playerService.listAll();
//		for (Player p : players) {;
//			if (p.getAvgSuccessPerc() <= loser.getAvgSuccessPerc()) {
//				loser = p;
//			}
//		}
//		model.addAttribute("loser", loser);
//		return "/ranking/loser";
//	}
//	
//	@GetMapping("/ranking/winner")
//	public String getWinner(Model model) {
//		Player winner = new Player();
//		winner.setAvgSuccessPerc(0);
//		List<Player> players = playerService.listAll();
//		for (Player p : players) {
//			if (p.getAvgSuccessPerc() >= winner.getAvgSuccessPerc()) {
//				winner = p;
//			}
//		}
//		model.addAttribute("winner", winner);
//		return "/ranking/winner";
//	}
	
	@GetMapping("/ranking")
	public ResponseEntity<Double> getRanking() {
		try {
			double avgSuccessPerc = 0;
			List<User> players = playerService.listAll();
			for (User p : players ) {
				avgSuccessPerc += p.getAvgSuccessPerc();
			}
			if (players.size() > 0) {
				avgSuccessPerc /= players.size();
			}
			
			avgSuccessPerc = Math.floor(avgSuccessPerc * 100)/100;
			
//			List<Player> players = new ArrayList<Player>();
//			
//			playerService.listAll().forEach(players::add);
			return new ResponseEntity<>(avgSuccessPerc, HttpStatus.OK);
		}  catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/ranking/loser")
	public ResponseEntity<User> getLoser() {
		User loser = new User();
		loser.setAvgSuccessPerc(100);
		List<User> players = playerService.listAll();
		for (User p : players) {;
			if (p.getAvgSuccessPerc() <= loser.getAvgSuccessPerc()) {
				loser = p;
			}
		}
		
		return new ResponseEntity<>(loser, HttpStatus.OK);
	}
	
	@GetMapping("/ranking/winner")
	public ResponseEntity<User> getWinner(Model model) {
		User winner = new User();
		winner.setAvgSuccessPerc(0);
		List<User> players = playerService.listAll();
		for (User p : players) {
			if (p.getAvgSuccessPerc() >= winner.getAvgSuccessPerc()) {
				winner = p;
			}
		}

		return new ResponseEntity<>(winner, HttpStatus.OK);
	}
	
}
