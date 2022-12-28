package S05T02N01.DiceGame.model.domain;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "games")
@Data
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="game_id", unique = true, nullable = false)
	private int gameId;

	@OneToMany(mappedBy = "game")
	@JsonIgnore
	private ArrayList<Roll> rolls = new ArrayList<>();
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    @JsonIgnore
    private Player player;
    
    @Column(name="success_perc")
	private double successPerc;
    
	public void clearRolls() {
		rolls.clear();
	}
	
	public void computeSuccessPerc() {
		successPerc = 0;
		
		rolls.stream().forEach(r -> successPerc +=  r.isWin()? 1:0);
		successPerc /= rolls.size();
	}
		

	
}