package S05T02N01.dicegame.security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.security.models.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  @Query(value= "{id: ?0}", fields="{'games': 1}")
  List<Game> findGamesById(String id);
  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
