package S05T02N01.DiceGame.model.domain;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;


@Data
public class Player {

	private int playerId;
	

	private String name;


	private double avgSuccessPerc = 0.0;

	private Set<Game> games = new HashSet<>();


	
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", name=" + name + ", avgSuccessPerc=" + avgSuccessPerc + "]";
	}
		

}
