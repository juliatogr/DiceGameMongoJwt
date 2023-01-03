package S05T02N01.DiceGame.model.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data

public class Game {
	private int gameId;

	private Set<Roll> rolls = new HashSet<>();
    private Player player;
    
	private double successPerc = 0.0;
    
	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", player=" + player + ", successPerc=" + successPerc
				+ "]";
	}
		

	
}