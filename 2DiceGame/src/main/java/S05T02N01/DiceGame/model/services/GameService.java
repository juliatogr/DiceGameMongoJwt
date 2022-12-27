package S05T02N01.DiceGame.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.dto.GameDTO;
import S05T02N01.DiceGame.model.repository.GameRepository;


public class GameService implements IGameService {
	@Autowired
	private GameRepository gameRepository;
	
	
	@Override
	@ModelAttribute("Games")
	public List<GameDTO> listAll() {
		
		return gameRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public GameDTO convertToDTO(Game game) {
		GameDTO gameDTO = new GameDTO();
		gameDTO.setId(game.getId());
		return gameDTO;
		
	}

	@Override
	public void saveOne(Game Game) {
		gameRepository.save(Game);
	}

	@Override
	public Game findByID(int id) {
		return gameRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteOne(int id) {
		gameRepository.deleteById(id);

	}
}
