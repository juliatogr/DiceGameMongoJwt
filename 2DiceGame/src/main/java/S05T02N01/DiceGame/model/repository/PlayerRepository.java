package S05T02N01.DiceGame.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Player;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer> {
}