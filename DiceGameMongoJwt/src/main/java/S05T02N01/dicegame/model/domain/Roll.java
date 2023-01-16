package S05T02N01.dicegame.model.domain;

import java.util.Random;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Document(collection = "rolls")
public class Roll {
	
	@Id private String id;
	private int d1;
	private int d2;
    private Game game;
	
	public boolean isWin() {
		return d1 + d2 == 7;
	}
	
	public Roll() {
		Random random = new Random();
		d1 = (int) (random.nextInt(6)+1);
		d2 = (int) (random.nextInt(6)+1);
	}
	
	public Roll(Game game) {
		
		Random random = new Random();
		d1 = (int) (random.nextInt(6)+1);
		d2 = (int) (random.nextInt(6)+1);
		this.game = game;
	}
	
}
