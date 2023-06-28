package fruitshop.dao;

import fruitshop.model.User;

public interface UserDao {
	public void addUser(User user);
	public boolean tonTaiUser(String email);
	public boolean tonTaiUser(String email, String password);
	public User getUserByEmail(String email);
	public void upDateUserByEmail(User user);
	public int getSoLuongUserOnline();
	public int getSoLuongUser();
}
