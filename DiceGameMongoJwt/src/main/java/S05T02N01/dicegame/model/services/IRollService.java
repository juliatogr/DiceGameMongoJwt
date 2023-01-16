package S05T02N01.dicegame.model.services;

import java.util.List;

import S05T02N01.dicegame.model.domain.Roll;
import S05T02N01.dicegame.model.dto.RollDTO;

public interface IRollService {
	public List<RollDTO> listAll();
	public List<RollDTO> listAllGame(String gameId);
	public void deleteAllGame(String gameId);
	public Roll saveOne(Roll roll);
	public Roll findByID(String id);
}
