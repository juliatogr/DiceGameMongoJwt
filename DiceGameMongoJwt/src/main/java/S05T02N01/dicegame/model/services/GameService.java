package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.Roll;
import S05T02N01.dicegame.model.dto.RollDTO;
import S05T02N01.dicegame.model.repository.GameRepository;
import S05T02N01.dicegame.security.repository.UserRepository;


@Service
public class GameService implements IGameService {

	@Autowired
	private GameRepository gameRepository;
		
	@Override
	//@ModelAttribute("games")
	public List<Game> listAll() {
		
		return gameRepository.findAll();
	}
	


	@Override
	public Game saveOne(Game game) {
		
		return gameRepository.save(game);
	}

}
