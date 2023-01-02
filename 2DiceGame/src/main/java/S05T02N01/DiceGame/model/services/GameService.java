package S05T02N01.DiceGame.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.dto.RollDTO;
import S05T02N01.DiceGame.model.repository.GameRepository;


@Service
public class GameService implements IGameService {

	@Autowired
	private GameRepository gameRepository;
	
	
	@Override
	@ModelAttribute("games")
	public List<Game> listAll() {
		
		return gameRepository.findAll().stream().collect(Collectors.toList());
	}
	
	@Override
	@ModelAttribute("playerGames")
	public List<Game> listAllPlayer(Integer playerId) {
		
		return gameRepository.findAllByPlayerPlayerId(playerId);
	}

	@Override
	public void saveOne(Game game) {
		gameRepository.save(game);
	}

	@Override
	public Game findByID(int id) {
		return gameRepository.findById(id).orElse(null);
	}

}
