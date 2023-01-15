package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;


import S05T02N01.dicegame.security.models.User;
import S05T02N01.dicegame.security.repository.UserRepository;


@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository playerRepository;
	
	
	@Override
	@ModelAttribute("players")
	public List<User> listAll() {
		
		return playerRepository.findAll().stream().collect(Collectors.toList());
	}

	@Override
	public User saveOne(User player) {
		playerRepository.save(player);
		return player;
	}
	
	@Override
	public Optional<User> findById(String id) {
		return playerRepository.findById(id);
	}
	
	@Override
	public Optional<User> findByUsername(String username) {
		return playerRepository.findByUsername(username);
	}

}
