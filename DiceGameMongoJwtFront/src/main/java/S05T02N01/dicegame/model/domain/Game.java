package S05T02N01.dicegame.model.domain;

import java.util.Random;

import lombok.Data;

@Data
public class Game {

	private int d1;
	private int d2;
	
	public Game() {
		Random random = new Random();
		d1 = (int) (random.nextInt(6)+1);
		d2 = (int) (random.nextInt(6)+1);
	}	
}