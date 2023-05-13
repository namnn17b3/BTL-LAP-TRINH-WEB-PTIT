package fruitshop.iservice;

import fruitshop.model.User;

public interface IUserService {
	public void addUser(User user);
	public boolean tonTaiUser(String email);
	public int getNextUserId();
}
