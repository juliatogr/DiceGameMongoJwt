package S05T02N01.dicegame.model.domain;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "users")
@Data
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  private List<Game> games = new ArrayList<>();

  private String role = "ROLE_USER";
  
  public User() {
	  
  }
  public User(String username) {
    this.username = username;
  }

}
