package S05T02N01.dicegame.model.dto;

import lombok.Data;

@Data
public class GameDTO {
	
	private int d1;
	private int d2;
	private int result;
	private boolean win;
	
	public GameDTO(int d1, int d2) {
		this.d1 = d1;
		this.d2 = d2;
		this.result = d1 + d2;
		this.win = this.result == 7;
	}	
}