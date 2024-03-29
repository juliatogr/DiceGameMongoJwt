package S05T02N01.dicegame.security.payload.request;

import javax.validation.constraints.NotBlank;

/*
 * Class created to validate login requests fields
 */
public class LoginRequest {
	
	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
