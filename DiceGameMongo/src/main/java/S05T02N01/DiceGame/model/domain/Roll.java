package S05T02N01.DiceGame.model.domain;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.Data;

@Data
public class Roll {
	
	private int rollId;
	

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

	@Override
	public String toString() {
		return "Roll [rollId=" + rollId + ", d1=" + d1 + ", d2=" + d2 + ", game=" + game + "]";
	}
	
}
