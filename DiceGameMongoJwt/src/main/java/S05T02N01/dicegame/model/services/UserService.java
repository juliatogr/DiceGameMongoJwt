package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.User;
import S05T02N01.dicegame.model.dto.GameDTO;
import S05T02N01.dicegame.model.dto.UserDTO;
import S05T02N01.dicegame.model.repository.UserRepository;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	
	public List<UserDTO> listAll() {
		
		return userRepository.findAll().stream().map(u->convertUserToDTO(u)).toList();
	}
	
	public List<GameDTO> listAllGames(String id) {
		
		return userRepository.findById(id).get().getGames().stream().map(g->convertGameToDTO(g)).toList();
	}

	public User saveOne(User player) {
		userRepository.save(player);
		return player;
	}
	
	public Optional<User> findById(String id) {
		return userRepository.findById(id);
	}
	
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public GameDTO convertGameToDTO(Game g) {
		return new GameDTO(g.getD1(), g.getD2());
	}
	
	public UserDTO convertUserToDTO(User u) {
		return new UserDTO(u.getUsername(), computeSuccessPerc(u), this.listAllGames(u.getId()));
	}

	public double computeSuccessPerc(User u) {
		double successPerc = 0.0;
		
		List<GameDTO> games = this.listAllGames(u.getId());
		
		if (games.size() > 0) {
			
			for (GameDTO g : games) {
				successPerc += g.isWin() ? 1:0;
			}
			successPerc /= games.size();
		}
		
		return successPerc;
	}
	
}
