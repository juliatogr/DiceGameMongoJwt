package S05T02N01.dicegame.security.payload.request;


import javax.validation.constraints.*;

import lombok.Data;
 
@Data
public class SignupRequest {

    private String username;
 
    @Email
    private String email;
    
    private String role = "ROLE_USER";
    
    private String password;
  
    public String getUsername() {
        return username;
    }
 
    public void setUsername(String username) {
        this.username = username;
    }
 
}
