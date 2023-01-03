package S05T02N01.DiceGame.model.dto;

import S05T02N01.DiceGame.model.domain.Game;
import lombok.Data;

@Data
public class RollDTO {
	private int rollId;
	private int d1;
	private int d2;
	private int result;
    private Game game;
	
	public boolean isWin() {
		return result == 7;
	}
}
