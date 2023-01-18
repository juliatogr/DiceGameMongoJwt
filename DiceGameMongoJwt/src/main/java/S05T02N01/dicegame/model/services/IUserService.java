package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.Optional;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.security.models.User;


public interface IUserService {
	public List<User> listAll();
	public User saveOne(User player);
	public Optional<User> findById(String id);
	public Optional<User> findByUsername(String name);
}
