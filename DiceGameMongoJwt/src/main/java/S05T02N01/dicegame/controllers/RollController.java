package S05T02N01.dicegame.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.Roll;
import S05T02N01.dicegame.model.dto.RollDTO;
import S05T02N01.dicegame.model.services.IGameService;
import S05T02N01.dicegame.model.services.IUserService;
import S05T02N01.dicegame.model.services.IRollService;
import S05T02N01.dicegame.security.models.User;


//@Controller
@RestController
public class RollController {

	@Autowired
	private IGameService gameService;
	
	@Autowired
	private IUserService playerService;
	
	@Autowired
	private IRollService rollService;
	
//	@PostMapping("/players/{id}/games/{gameId}/roll")
//	public String roll(@PathVariable("id") int pk_PlayerID, 
//			@PathVariable("gameId") int pk_gameID, Roll lastRoll) {
//		
//		Game game = gameService.findByID(pk_gameID);
//		Player player = playerService.findByID(pk_PlayerID);
//		
//		lastRoll.rollDices();
//		lastRoll.setGame(game);
//		
//		
//		List<Game> games = gameService.listAllPlayer(pk_PlayerID);
//		List<RollDTO> rolls = rollService.listAllGame(pk_gameID);
//		
//		int numRolls = rolls.size();
//		int numGames = games.size();
//		
//		double currentGameSuccess = game.getSuccessPerc();
//		double currentPlayerSuccess = player.getAvgSuccessPerc();
//		
//		double newGameSuccess = (currentGameSuccess * numRolls + (lastRoll.isWin()?1:0))/(numRolls+1);
//		game.setSuccessPerc(Math.floor(newGameSuccess* 100)/100);
//		gameService.saveOne(game);
//		
//		double newPlayerSuccess = (currentPlayerSuccess * (numGames-1) + newGameSuccess)/numGames;
//		player.setAvgSuccessPerc(Math.floor(newPlayerSuccess* 100)/100);
//		
//		playerService.saveOne(player);
//		Roll.idIncrement++;
//		lastRoll.setRollId(Roll.idIncrement);
//		rollService.saveOne(lastRoll);
//		
//		return "redirect:/players/"+pk_PlayerID+"/games/"+pk_gameID+"/rolls";
//	}
//	
//	@GetMapping("/players/{id}/games/{gameId}/rolls")
//	public String listRolls(@PathVariable("id") int pk_PlayerID, 
//			@PathVariable("gameId") int pk_gameID, Model model) {
//		
//		Game game = gameService.findByID(pk_gameID);
//		List<RollDTO> rolls = rollService.listAllGame(pk_gameID);
//		
//		model.addAttribute("rolls", rolls);
//		
//		if (!rolls.isEmpty() && rolls.get(rolls.size()-1).isWin()) {
//			return "/game/won_rolls_list";
//		}
//		return "/game/rolls_list";
//	}
//	
//	@GetMapping("/players/{id}/games/{gameId}/deleterolls")
//	public String deleteRolls(@PathVariable("id") int pk_PlayerID,
//			@PathVariable("gameId") int pk_gameID) {
//		
//		rollService.deleteAllGame(pk_gameID);
//
//		return "/game/rolls_list";
//	}
	
	@PostMapping("/players/{id}/games/{gameId}/roll")
	public ResponseEntity<Roll> roll(@PathVariable("id") String pk_PlayerID, 
			@PathVariable("gameId") String gameId) {
		
		
		try {
			
			
			Game game = gameService.findByID(gameId);
			Optional<User> p = playerService.findById(pk_PlayerID);
			
			User player = p.isPresent()? p.get() : null;
			
			Roll lastRoll = new Roll();
			
			
			
//			List<Game> games = gameService.listAllPlayer(pk_PlayerID);
//			List<RollDTO> rolls = rollService.listAllGame(gameId);
//			
//			int numRolls = rolls.size();
//			int numGames = games.size();
//			
//			double currentGameSuccess = game.getSuccessPerc();
//			double currentPlayerSuccess = player.getAvgSuccessPerc();
//			
//			double newGameSuccess = (currentGameSuccess * numRolls + (lastRoll.isWin()?1:0))/(numRolls+1);
//			game.setSuccessPerc(Math.floor(newGameSuccess* 100)/100);
//			gameService.saveOne(game);
//			
//			double newPlayerSuccess = (currentPlayerSuccess * (numGames-1) + newGameSuccess)/numGames;
//			player.setAvgSuccessPerc(Math.floor(newPlayerSuccess* 100)/100);
//			
//			playerService.saveOne(player);
			Roll _roll = rollService.saveOne(lastRoll);
			return new ResponseEntity<>(_roll, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/players/{id}/games/{gameId}/rolls")
	public ResponseEntity<List<RollDTO>> listRolls(@PathVariable("id") String pk_PlayerID, 
			@PathVariable("gameId") String pk_gameID) {
		try {
			Game game = gameService.findByID(pk_gameID);
			List<RollDTO> rolls = new ArrayList<RollDTO>();
			rollService.listAllGame(pk_gameID).forEach(rolls::add);
		
			return new ResponseEntity<>(rolls, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/players/{id}/games/{gameId}/deleterolls")
	public ResponseEntity<HttpStatus> deleteRolls(@PathVariable("id") String pk_PlayerID,
			@PathVariable("gameId") String pk_gameID) {

		try {
			rollService.deleteAllGame(pk_gameID);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
