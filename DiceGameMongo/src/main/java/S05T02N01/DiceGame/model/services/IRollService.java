package S05T02N01.DiceGame.model.services;

import java.util.List;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Roll;
import S05T02N01.DiceGame.model.dto.RollDTO;

public interface IRollService {
	public List<RollDTO> listAll();
	public List<RollDTO> listAllGame(Integer gameId);
	public void saveOne(Roll roll);
	public Roll findByID(int id);
}
