package S05T02N01.DiceGame.model.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.domain.Roll;
import S05T02N01.DiceGame.model.dto.RollDTO;

@Repository
public interface RollRepository extends MongoRepository<Roll, Integer> {
	public List<Roll> findAllByGameGameId(Integer gameId);
	public void deleteAllByGameGameId(int gameId);
	public Roll findById(int id);
	public void deleteById(int rollId);
}