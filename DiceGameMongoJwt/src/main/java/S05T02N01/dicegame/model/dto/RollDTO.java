package S05T02N01.dicegame.model.dto;

import S05T02N01.dicegame.model.domain.Game;
import lombok.Data;

@Data
public class RollDTO {
	private String rollId;
	private int d1;
	private int d2;
	private int result;
    private Game game;
	
	public boolean isWin() {
		return result == 7;
	}
}
