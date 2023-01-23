package S05T02N01.dicegame.model.domain;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class RegisteredUser extends User{

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  @NotBlank
  @Size(max = 120)
  private String password;
  
  private Date regDate;
  
  public RegisteredUser(String username, String email, String password) {
    super(username);
    this.email = email;
    this.password = password;
    this.regDate = new Date();
  }

}
