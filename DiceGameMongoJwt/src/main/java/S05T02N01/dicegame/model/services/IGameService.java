package S05T02N01.dicegame.model.services;

import java.util.List;

import S05T02N01.dicegame.model.domain.Game;

public interface IGameService {
	public List<Game> listAll();
	public List<Game> listAllPlayer(String playerId);
	public Game saveOne(Game game);
	public Game findByID(String id);
}
