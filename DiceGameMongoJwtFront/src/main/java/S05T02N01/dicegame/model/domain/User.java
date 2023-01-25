package S05T02N01.dicegame.model.domain;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Document(collection = "users")
@Data
@Builder
public class User {
  @Id
  private String id;

  @NotBlank
  @Size(max = 20)
  private String username;
  
  @NotBlank
  private String password;
  
  @NotBlank
  @Email
  private String email;

  private List<Game> games = new ArrayList<>();
  


}

