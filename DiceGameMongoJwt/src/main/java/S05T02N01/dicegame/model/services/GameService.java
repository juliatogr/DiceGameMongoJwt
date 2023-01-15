package S05T02N01.dicegame.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.repository.GameRepository;


@Service
public class GameService implements IGameService {

	@Autowired
	private GameRepository gameRepository;
	
	
	@Override
	@ModelAttribute("games")
	public List<Game> listAll() {
		
		return gameRepository.findAll();
	}
	
	@Override
	@ModelAttribute("playerGames")
	public List<Game> listAllPlayer(String id) {
		
		return gameRepository.findAllByPlayerId(id);
	}

	@Override
	public Game saveOne(Game game) {
		
		return gameRepository.save(game);
	}

	@Override
	public Game findByID(int id) {
		return gameRepository.findById(id);
	}

}
