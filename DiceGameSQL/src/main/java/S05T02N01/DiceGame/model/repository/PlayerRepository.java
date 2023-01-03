package S05T02N01.DiceGame.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.DiceGame.model.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
	public Player findByName(String name);
}