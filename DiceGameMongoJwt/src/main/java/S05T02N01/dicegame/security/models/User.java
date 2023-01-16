package S05T02N01.dicegame.security.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import S05T02N01.dicegame.model.domain.Game;
import lombok.Data;

@Document(collection = "users")
@Data
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;

  @DBRef
  private Set<Role> roles = new HashSet<>();
  
  @DBRef
  private Set<Game> games = new HashSet<>();
  
  public double avgSuccessPerc = 0.0;

  public User() {
  }

  public User(String username) {
	    this.username = username;
}
  
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
  }

}
