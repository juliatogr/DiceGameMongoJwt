package S05T02N01.DiceGame.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import S05T02N01.DiceGame.security.domain.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}