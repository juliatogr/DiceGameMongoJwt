package S05T02N01.DiceGame.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import S05T02N01.DiceGame.model.domain.Game;
import S05T02N01.DiceGame.model.domain.Player;
import S05T02N01.DiceGame.model.repository.PlayerRepository;


@Service
public class PlayerService implements IPlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	
	@Override
	@ModelAttribute("players")
	public List<Player> listAll() {
		
		return playerRepository.findAll().stream().collect(Collectors.toList());
	}

	@Override
	public void saveOne(Player player) {
		playerRepository.save(player);
	}
	
	@Override
	public Player findByID(int id) {
		return playerRepository.findById(id);
	}
	
	@Override
	public Player findByName(String name) {
		return playerRepository.findByName(name);
	}

	@Override
	public void deleteOne(int id) {
		playerRepository.deleteById(id);

	}
}
