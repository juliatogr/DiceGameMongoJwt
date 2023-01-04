package S05T02N01.DiceGame.model.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class Game {
	public static int idIncrement = 0;
	@Id private int gameId;

	private Set<Roll> rolls = new HashSet<>();
    private Player player;
	private double successPerc = 0.0;
	
}