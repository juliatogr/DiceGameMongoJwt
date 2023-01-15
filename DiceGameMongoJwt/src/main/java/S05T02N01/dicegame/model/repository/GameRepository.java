package S05T02N01.dicegame.model.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.dicegame.model.domain.Game;

@Repository
public interface GameRepository extends MongoRepository<Game, Integer> {
	public List<Game> findAllByPlayerId(String id);
	public Game findById(int id);
}