package S05T02N01.dicegame.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import S05T02N01.dicegame.model.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
