package S05T02N01.DiceGame.model.domain;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "players")
@Data
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id", unique = true, nullable = true)
	private int playerId;
	
	@Column(name = "name", unique = true)
	private String name;

    @Column(name="avg_success_perc")
	private double avgSuccessPerc = 0.0;
    
	@OneToMany(mappedBy = "player", 
			cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@JsonIgnore
	private Set<Game> games = new HashSet<>();


	
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", name=" + name + ", avgSuccessPerc=" + avgSuccessPerc + "]";
	}
		

}
