package S05T02N01.DiceGame.model.domain;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Data
@Entity
public class User {
	@Id
	@GeneratedValue()
	@Column(name="userId")
	private Integer id;
	

}
