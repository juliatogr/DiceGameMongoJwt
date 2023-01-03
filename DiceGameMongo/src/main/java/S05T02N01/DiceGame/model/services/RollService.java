package S05T02N01.DiceGame.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import S05T02N01.DiceGame.model.domain.Roll;
import S05T02N01.DiceGame.model.dto.RollDTO;
import S05T02N01.DiceGame.model.repository.RollRepository;


@Service
public class RollService implements IRollService {

	@Autowired
	private RollRepository rollRepository;
	
	
	@Override
	@ModelAttribute("rolls")
	public List<RollDTO> listAll() {
		
		return rollRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	@Override
	@ModelAttribute("gameRolls")
	public List<RollDTO> listAllGame(Integer gameId) {
		
		return rollRepository.findAllByGameGameId(gameId).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public void saveOne(Roll roll) {
		rollRepository.save((Iterable<Roll>) roll);
	}

	@Override
	public Roll findByID(int id) {
		return rollRepository.findById(id);
	}

	public RollDTO convertToDTO(Roll roll) {
		RollDTO rollDTO = new RollDTO();
		rollDTO.setRollId(roll.getRollId());
		rollDTO.setD1(roll.getD1());
		rollDTO.setD2(roll.getD2());
		rollDTO.setResult(roll.getD1()+roll.getD2());
		rollDTO.setGame(roll.getGame());
		return rollDTO;
		
	}
}
