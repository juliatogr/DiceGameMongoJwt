package S05T02N01.dicegame.security.payload.response;

/*
 * Class to manage String responses
 */
public class MessageResponse {
	private String message;

	public MessageResponse(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
