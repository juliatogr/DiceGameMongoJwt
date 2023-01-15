package S05T02N01.dicegame.model.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;

import S05T02N01.dicegame.security.models.User;
import lombok.Data;

@Data
public class Game {
	public static int idIncrement = 0;
	@Id private int gameId;

	private Set<Roll> rolls = new HashSet<>();
    private User player;
	private double successPerc = 0.0;
	
	public Game() {
		
	}
	
	public Game(User p) {
		this.player = p;
	}
}