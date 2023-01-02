package S05T02N01.DiceGame.model.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.DiceGame.model.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
	public List<Game> findAllByPlayerPlayerId(Integer playerId);
}