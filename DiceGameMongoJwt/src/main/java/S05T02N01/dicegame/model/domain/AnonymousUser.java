package S05T02N01.dicegame.model.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class AnonymousUser extends User{
  
  public AnonymousUser() {
    super("ANONYMOUS");
    this.setRole("ROLE_ANONYMOUS");
  }

}
