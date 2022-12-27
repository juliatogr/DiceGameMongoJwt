package S05T02N01.DiceGame.model.services;

import java.util.List;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.dto.GameDTO;

public interface IGameService {
	public List<GameDTO> listAll();
	public void saveOne(Game sucursal);
	public Game findByID(int id);
	public void deleteOne(int id);
}
