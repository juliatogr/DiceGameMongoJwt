package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.Optional;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.Roll;
import S05T02N01.dicegame.model.dto.RollDTO;

public interface IGameService {
	public List<Game> listAll();
	public Game saveOne(Game game);
}
