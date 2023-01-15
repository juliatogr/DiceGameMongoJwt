package S05T02N01.dicegame.model.domain;

import java.util.Random;

import org.springframework.data.annotation.Id;


import lombok.Data;

@Data
public class Roll {
	public static int idIncrement = 0;
	
	@Id private int rollId;
	private int d1;
	private int d2;
    private Game game;
	
	public void rollDices() {
		Random random = new Random();
		d1 = (int) (random.nextInt(6)+1);
		d2 = (int) (random.nextInt(6)+1);
	}
	
	public boolean isWin() {
		return d1 + d2 == 7;
	}
	
	public Roll() {
		
	}
	
	public Roll(Game game) {
		this.game = game;
	}
	
}
