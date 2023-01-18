package S05T02N01.dicegame.model.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import S05T02N01.dicegame.security.models.User;
import lombok.Data;

@Data
public class Game {

	private List<Roll> rolls = new ArrayList<>();
	
	private double successPerc = 0.0;
	
	@JsonIgnore
	private boolean isFinished = false;
}