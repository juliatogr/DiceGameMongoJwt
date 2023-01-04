package S05T02N01.DiceGame.model.domain;


import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "players")
public class Player {

    public static int idIncrement = 0;
    
	@Id private long playerId;
	private String name;
	private double avgSuccessPerc = 0.0;

	private List<Game> games = new ArrayList<>();

}
