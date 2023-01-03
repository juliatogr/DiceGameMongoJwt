package S05T02N01.DiceGame.model.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.DiceGame.model.domain.Player;

@Repository
public interface PlayerRepository extends MongoRepository<Player, Integer> {
	public Player findByName(String name);

	public Player findById(int id);

	public void deleteById(int id);
}