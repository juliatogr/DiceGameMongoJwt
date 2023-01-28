package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import S05T02N01.dicegame.model.domain.AnonymousUser;
import S05T02N01.dicegame.model.domain.Game;
import S05T02N01.dicegame.model.domain.RegisteredUser;
import S05T02N01.dicegame.model.domain.User;
import S05T02N01.dicegame.model.dto.GameDTO;
import S05T02N01.dicegame.model.dto.UserDTO;
import S05T02N01.dicegame.model.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private User currentUser = null;
	private AnonymousUser lastAnonymousUser = null;

	public List<UserDTO> listAll() {

		return userRepository.findAll().stream().map(u -> convertUserToDTO(u)).toList();
	}

	public List<GameDTO> listAllGames(String id) {
		User u = userRepository.findById(id).get();
		System.out.println(u);
		return u.getGames().stream().map(g -> convertGameToDTO(g)).toList();
	}

	public User saveOne(User player) {

		return userRepository.save(player);
	}

	public User findById(String id) {
		Optional<User> u = userRepository.findById(id);

		return u.isPresent() ? u.get() : null;
	}

	public GameDTO convertGameToDTO(Game g) {
		return new GameDTO(g.getD1(), g.getD2());
	}

	public UserDTO convertUserToDTO(User u) {
		UserDTO user =  new UserDTO(u.getUsername(), computeSuccessPerc(u), this.listAllGames(u.getId()));
		
		return user;
	}

	public double computeSuccessPerc(User u) {
		double successPerc = 0.0;

		List<GameDTO> games = this.listAllGames(u.getId());

		if (games.size() > 0) {

			for (GameDTO g : games) {
				successPerc += g.isWin() ? 1 : 0;
			}
			successPerc /= games.size();
		}

		return successPerc;
	}

	public boolean existsByUsername(String username) {

		boolean[] exists = { false };
		userRepository.findAll().forEach(u -> {
			exists[0] = !u.getUsername().equals("ANONYMOUS") && u.getUsername().equals(username);

		});
		return exists[0];
	}

	public Optional<RegisteredUser> findByUsername(String username) {


		return userRepository.findByUsername(username);
	}

	public boolean existsByEmail(String email) {

		boolean[] exists = { false };
		userRepository.findAll().forEach(u -> {

			if (!u.getUsername().equals("ANONYMOUS")) {
				RegisteredUser regUser = (RegisteredUser) u;
				exists[0] = regUser.getEmail().equals(email);
			}

		});
		return exists[0];
	}

	public void setlastAnonymousUser(AnonymousUser u) {
		this.lastAnonymousUser = u;
		this.currentUser = u;
	}
	
	public User getCurrentUser() {
		
		List<User> users = userRepository.findAll();
		
		boolean found = false;
		int i = users.size()-1;
		while (i >= 0 && !found) {
			if (users.get(i).getUsername().equals("ANONYMOUS")) {
				this.currentUser = users.get(i);
				found = true;
			}
			i--;
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			
			System.out.println("is logged in");
			Optional<RegisteredUser> _user = this.findByUsername(authentication.getName());
			
			this.currentUser = _user.isPresent()? _user.get() : null;
		}
		System.out.println("currentuser: " + currentUser.getId());
		return this.currentUser;
	}
}
