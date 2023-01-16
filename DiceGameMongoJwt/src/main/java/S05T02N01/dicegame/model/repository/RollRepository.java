package S05T02N01.dicegame.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.dicegame.model.domain.Roll;

public interface RollRepository extends MongoRepository<Roll, Integer> {
	public List<Roll> findAllByGameId(String gameId);
	public void deleteAllByGameId(String gameId);
	public Roll findById(String id);
	public void deleteById(String string);
}