package S05T02N01.dicegame.model.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Document(collection = "users")
@EqualsAndHashCode(callSuper=false)
public class AnonymousUser extends User{
  
  public AnonymousUser() {
    super("ANONYMOUS");
    this.setRole("ROLE_ANONYMOUS");
  }

}
