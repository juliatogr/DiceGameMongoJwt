package S05T02N01.DiceGame.model.domain;


import java.util.ArrayList;
import java.util.Arrays;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="players")
@Data
public class Player {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "player_id", unique = true, nullable = false)
	int id;
	
	@Column(name = "name", unique = true, nullable = true)
	String name;
	
    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private ArrayList<Roll> rolls;

	@Column(name="success_perc")
	private double successPerc;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;
	
	public void clearRolls() {
		rolls.clear();
	}
	
	public void computeSuccessPerc() {
		successPerc = 0;
		
		rolls.stream().forEach(r -> successPerc +=  r.isWin()? 1:0);
		successPerc /= rolls.size();
	}
}
