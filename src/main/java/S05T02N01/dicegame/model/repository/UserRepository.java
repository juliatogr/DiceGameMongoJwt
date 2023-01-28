package S05T02N01.dicegame.model.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import S05T02N01.dicegame.model.domain.RegisteredUser;
import S05T02N01.dicegame.model.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<RegisteredUser> findByUsername(String username);
}
