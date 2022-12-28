package S05T02N01.DiceGame.model.domain;


import java.util.ArrayList;
import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id", unique = true, nullable = false)
	private int player_id;
	
	@Column(name = "name", unique = true, nullable = true)
	private String name;

	@OneToMany(mappedBy = "player")
	@JsonIgnore
	private ArrayList<Game> games = new ArrayList<>();
		

}
