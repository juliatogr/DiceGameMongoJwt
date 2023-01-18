package S05T02N01.dicegame.security.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "roles")
@Data
public class Role {
  @Id
  private String id;

  private ERole name;
}
