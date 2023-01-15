package S05T02N01.dicegame.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.dicegame.model.domain.Roll;

@Repository
public interface RollRepository extends MongoRepository<Roll, Integer> {
	public List<Roll> findAllByGameGameId(Integer gameId);
	public void deleteAllByGameGameId(int gameId);
	public Roll findById(int id);
	public void deleteById(int rollId);
}