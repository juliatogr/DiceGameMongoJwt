package S05T02N01.DiceGame.model.domain;

import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "game")
@Data
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "game_id", unique = true, nullable = false)
	int id;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private ArrayList<Player> players;
	
	@Column(name="avg_success_perc")
	private double avgSuccessPerc;
	
	private Player wonPlayer = null;
	
	public void computeAvgSuccessPerc() {
		
		avgSuccessPerc = 0;
		players.stream().forEach(
			p -> {
				p.computeSuccessPerc(); 
				avgSuccessPerc += p.getSuccessPerc();
			}
		);
	}
	

}
