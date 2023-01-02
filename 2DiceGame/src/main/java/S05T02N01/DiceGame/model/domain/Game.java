package S05T02N01.DiceGame.model.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "games")
@Data

public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="game_id", unique = true, nullable = false)
	private int gameId;

	@OneToMany(mappedBy = "game",
			cascade = CascadeType.ALL,
	        orphanRemoval = true)
	@JsonIgnore
	private Set<Roll> rolls = new HashSet<>();
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    @JsonIgnore
    private Player player;
    
    @Column(name="success_perc")
	private double successPerc = 0.0;
    
	public void clearRolls() {
		rolls.clear();
	}
	
	public void computeSuccessPerc() {
		successPerc = 0;
		
		rolls.stream().forEach(r -> successPerc +=  r.isWin()? 1:0);
		successPerc /= rolls.size();
	}

	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", player=" + player + ", successPerc=" + successPerc
				+ "]";
	}
		

	
}