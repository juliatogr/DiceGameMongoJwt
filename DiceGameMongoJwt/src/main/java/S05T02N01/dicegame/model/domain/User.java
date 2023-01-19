package S05T02N01.dicegame.model.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
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

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
  
  private Date regDate;

  private String role = "ROLE_USER";

  private List<Game> games = new ArrayList<>();
  
  public double avgSuccessPerc = 0.0;

  public User() {
	  this.regDate = new Date();
  }

  public User(String username) {
	    this.username = username;
	    this.regDate = new Date();
}
  
  public User(String username, String email, String password) {
    this.username = username;
    this.email = email;
    this.password = password;
    this.regDate = new Date();
  }

}
