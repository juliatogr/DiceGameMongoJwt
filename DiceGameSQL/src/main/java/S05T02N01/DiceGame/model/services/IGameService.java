package S05T02N01.DiceGame.model.services;

import java.util.List;

import S05T02N01.DiceGame.model.domain.Game;

public interface IGameService {
	public List<Game> listAll();
	public List<Game> listAllPlayer(Integer playerId);
	public void saveOne(Game game);
	public Game findByID(int id);
}
