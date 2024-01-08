package fruitshop.dto;

import fruitshop.model.User;

public class UserAdminUpdateResponseDto extends ErrorResponseDto {
	private User newUser;
	
	public UserAdminUpdateResponseDto() {}
	
	public UserAdminUpdateResponseDto(int status, String message, User newUser) {
		super(status, message);
		this.newUser = newUser;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}
}
