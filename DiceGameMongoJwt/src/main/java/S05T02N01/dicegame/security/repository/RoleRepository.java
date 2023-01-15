package S05T02N01.dicegame.security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import S05T02N01.dicegame.security.models.ERole;
import S05T02N01.dicegame.security.models.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
  Optional<Role> findByName(ERole name);
}
