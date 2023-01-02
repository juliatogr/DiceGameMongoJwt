package S05T02N01.DiceGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Roll;
import S05T02N01.DiceGame.model.dto.RollDTO;
import S05T02N01.DiceGame.model.services.IGameService;
import S05T02N01.DiceGame.model.services.IRollService;


@Controller
public class RollController {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IRollService rollService;
	
	@PostMapping("/players/{playerId}/games/{gameId}/roll")
	public String roll(@PathVariable("playerId") int pk_PlayerID, 
			@PathVariable("gameId") int pk_gameID, Roll lastRoll) {
		
		Game game = gameService.findByID(pk_gameID);

		lastRoll.rollDices();
		lastRoll.setGame(game);
		rollService.saveOne(lastRoll);
		
		return "redirect:/players/"+pk_PlayerID+"/games/"+pk_gameID+"/rolls";
	}
	
	@GetMapping("/players/{playerId}/games/{gameId}/rolls")
	public String listRolls(@PathVariable("playerId") int pk_PlayerID, 
			@PathVariable("gameId") int pk_gameID, Model model) {
		
		List<RollDTO> rolls = rollService.listAllGame(pk_gameID);
		
		model.addAttribute("rolls", rolls);
		
		if (!rolls.isEmpty() && rolls.get(rolls.size()-1).isWin()) {
			return "/game/won_rolls_list";
		}
		return "/game/rolls_list";
	}
	

}
