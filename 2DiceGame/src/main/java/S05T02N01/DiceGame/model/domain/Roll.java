package S05T02N01.DiceGame.model.domain;

import java.util.Objects;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="rolls")
@Data
public class Roll {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roll_id", unique = true, nullable = false)
	private int rollId;
	
	@Column(name = "dice1")
	private int d1;
	@Column(name = "dice2")
	private int d2;
	
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false)
    @JsonIgnore
    private Game game;
	
	public void rollDices() {
		Random random = new Random();
		d1 = (int) (random.nextInt(6)+1);
		d2 = (int) (random.nextInt(6)+1);
	}
	
	public boolean isWin() {
		boolean isWin = false;
		if (d1 + d2 == 7) {
			isWin = true;
		}
		return isWin;
	}

	@Override
	public String toString() {
		return "Roll [rollId=" + rollId + ", d1=" + d1 + ", d2=" + d2 + ", game=" + game + "]";
	}
	
}
