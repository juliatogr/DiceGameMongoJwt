package S05T02N01.dicegame.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserDTO {
  private String username;
  private double successPerc = 0.0;
  private List<GameDTO> games;

  public UserDTO(String username, double successPerc, List<GameDTO> games) {
	  this.username = username;
	  this.successPerc = successPerc;
	  this.games = games;
  }
}
