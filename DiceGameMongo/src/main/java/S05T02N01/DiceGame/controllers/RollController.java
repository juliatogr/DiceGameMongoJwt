package S05T02N01.DiceGame.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.domain.Roll;
import S05T02N01.DiceGame.model.dto.RollDTO;
import S05T02N01.DiceGame.model.services.IGameService;
import S05T02N01.DiceGame.model.services.IPlayerService;
import S05T02N01.DiceGame.model.services.IRollService;


@Controller
public class RollController {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IPlayerService playerService;
	
	@Autowired
	private IRollService rollService;
	
	@PostMapping("/players/{playerId}/games/{gameId}/roll")
	public String roll(@PathVariable("playerId") int pk_PlayerID, 
			@PathVariable("gameId") int pk_gameID, Roll lastRoll) {
		
		Game game = gameService.findByID(pk_gameID);
		Player player = playerService.findByID(pk_PlayerID);
		
		lastRoll.rollDices();
		lastRoll.setGame(game);
		
		
		List<Game> games = gameService.listAllPlayer(pk_PlayerID);
		List<RollDTO> rolls = rollService.listAllGame(pk_gameID);
		
		int numRolls = rolls.size();
		int numGames = games.size();
		
		double currentGameSuccess = game.getSuccessPerc();
		double currentPlayerSuccess = player.getAvgSuccessPerc();
		
		double newGameSuccess = (currentGameSuccess * numRolls + (lastRoll.isWin()?1:0))/(numRolls+1);
		game.setSuccessPerc(Math.floor(newGameSuccess* 100)/100);
		gameService.saveOne(game);
		
		double newPlayerSuccess = (currentPlayerSuccess * (numGames-1) + newGameSuccess)/numGames;
		player.setAvgSuccessPerc(Math.floor(newPlayerSuccess* 100)/100);
		
		playerService.saveOne(player);
		Roll.idIncrement++;
		lastRoll.setRollId(Roll.idIncrement);
		rollService.saveOne(lastRoll);
		
		return "redirect:/players/"+pk_PlayerID+"/games/"+pk_gameID+"/rolls";
	}
	
	@GetMapping("/players/{playerId}/games/{gameId}/rolls")
	public String listRolls(@PathVariable("playerId") int pk_PlayerID, 
			@PathVariable("gameId") int pk_gameID, Model model) {
		
		Game game = gameService.findByID(pk_gameID);
		List<RollDTO> rolls = rollService.listAllGame(pk_gameID);
		
		model.addAttribute("rolls", rolls);
		
		if (!rolls.isEmpty() && rolls.get(rolls.size()-1).isWin()) {
			return "/game/won_rolls_list";
		}
		return "/game/rolls_list";
	}
	
	@GetMapping("/players/{playerId}/games/{gameId}/deleterolls")
	public String deleteRolls(@PathVariable("playerId") int pk_PlayerID,
			@PathVariable("gameId") int pk_gameID) {
		
		rollService.deleteAllGame(pk_gameID);

		return "/game/rolls_list";
	}
	

}
