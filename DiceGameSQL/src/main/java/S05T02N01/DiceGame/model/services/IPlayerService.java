package S05T02N01.DiceGame.model.services;

import java.util.List;

import S05T02N01.DiceGame.model.domain.Player;

public interface IPlayerService {
	public List<Player> listAll();
	public void saveOne(Player player);
	public Player findByID(int id);
	public Player findByName(String name);
	public void deleteOne(int id);
}
