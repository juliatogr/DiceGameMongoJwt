package S05T02N01.DiceGame.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="rolls")
public class Roll {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roll_id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "dice1", unique = true)
	private int d1;
	@Column(name = "dice2", unique = true)
	private int d2;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;
	
	public void rollDices() {
		d1 = (int) (Math.random())*5+1;
		d2 = (int) (Math.random())*5+1;
	}
	
	public boolean isWin() {
		boolean isWin = false;
		if (d1 + d2 == 7) {
			isWin = true;
		}
		return isWin;
	}
}
