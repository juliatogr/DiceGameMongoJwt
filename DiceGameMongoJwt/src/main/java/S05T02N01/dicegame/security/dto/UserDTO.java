package S05T02N01.dicegame.security.dto;

import java.util.HashSet;
import java.util.Set;

import S05T02N01.dicegame.security.models.Role;
import lombok.Data;

@Data
public class UserDTO {
  private String id;
  private String username;
  private String email;
  private Set<Role> roles = new HashSet<>();
  
  public double avgSuccessPerc = 0.0;

}
