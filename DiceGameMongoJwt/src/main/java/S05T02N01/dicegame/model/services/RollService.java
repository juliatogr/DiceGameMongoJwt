package S05T02N01.dicegame.model.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import S05T02N01.dicegame.model.domain.Roll;
import S05T02N01.dicegame.model.dto.RollDTO;
import S05T02N01.dicegame.model.repository.RollRepository;


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
	public List<RollDTO> listAllGame(String gameId) {
		
		return rollRepository.findAllByGameId(gameId).stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public Roll saveOne(Roll roll) {
		return rollRepository.save(roll);
	}
	
	@Override
	public void deleteAllGame(String gameId) {
		this.listAll().stream().forEach(r->rollRepository.deleteById(r.getRollId()));
	}

	@Override
	public Roll findByID(String id) {
		return rollRepository.findById(id);
	}

	public RollDTO convertToDTO(Roll roll) {
		RollDTO rollDTO = new RollDTO();
		rollDTO.setRollId(roll.getId());
		rollDTO.setD1(roll.getD1());
		rollDTO.setD2(roll.getD2());
		rollDTO.setResult(roll.getD1()+roll.getD2());
		return rollDTO;
		
	}
}
