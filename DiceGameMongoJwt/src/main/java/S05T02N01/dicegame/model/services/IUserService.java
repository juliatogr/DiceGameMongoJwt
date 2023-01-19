package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.Optional;

import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.User;
import S05T02N01.dicegame.model.dto.GameDTO;
import S05T02N01.dicegame.model.dto.UserDTO;


public interface IUserService {
	public List<UserDTO> listAll();
	public List<GameDTO> listAllGames(String id);
	public User saveOne(User player);
	public Optional<User> findById(String id);
	public Optional<User> findByUsername(String name);
	public GameDTO convertGameToDTO(Game g);
	public UserDTO convertUserToDTO(User u);
}
