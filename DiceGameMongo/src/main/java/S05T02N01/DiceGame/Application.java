package S05T02N01.DiceGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import S05T02N01.DiceGame.model.repository.GameRepository;
import S05T02N01.DiceGame.model.repository.PlayerRepository;
import S05T02N01.DiceGame.model.repository.RollRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses
	    = {
	        GameRepository.class, PlayerRepository.class, RollRepository.class
	    })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
