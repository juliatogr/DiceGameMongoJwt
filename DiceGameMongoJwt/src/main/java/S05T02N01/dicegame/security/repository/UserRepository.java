package S05T02N01.dicegame.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import S05T02N01.dicegame.security.models.User;

public interface UserRepository extends MongoRepository<User, String> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  Optional<User>  findById(int id);

}