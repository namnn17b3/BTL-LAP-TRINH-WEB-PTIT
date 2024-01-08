package fruitshop.dao;

import java.util.List;

import fruitshop.model.User;

public interface UserDao {
	public void addUser(User user);
	public boolean tonTaiUser(String email);
	public boolean tonTaiUser(String email, String password);
	public User getUserByEmail(String email);
	public User getUserById(int id);
	public void upDateUserByEmail(User user);
	public void upDateUserById(User user);
	public int getSoLuongUserOnline();
	public int getSoLuongUser();
	public List<User> getListTop5KhachHangTheoTongChiTieu();
	public List<User> getListTop5KhachHangTheoSoLuongMua();
	public List<User> getListUserWithPagination(String queryText, int page, int itemInPage);
	public int getSoLuongUserWithPagination(String queryText);
	public void deleteUserById(int id);
	public void deleteUserByEmail(String email);
}
