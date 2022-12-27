package S05T02N01.DiceGame.model.dto;

import java.util.Arrays;
import java.util.Optional;

import lombok.Data;

@Data
public class GameDTO {

	private int id;
	
	
	public GameDTO() {
	}
	

	
	@Override
	public String toString() {
		return "Game [id=" + id + "]";
	}
	
	
}

