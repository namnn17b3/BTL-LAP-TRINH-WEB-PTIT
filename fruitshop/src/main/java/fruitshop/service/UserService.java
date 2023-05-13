package fruitshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fruitshop.dao.Dao;
import fruitshop.iservice.IUserService;
import fruitshop.model.User;

public class UserService implements IUserService {

	private Connection conn = Dao.getConnection();
	
	@Override
	public void addUser(User user) {
		PreparedStatement ppst;
		try {
			ppst = conn.prepareStatement(
				"insert into user(ten, email, mat_khau, anh, vai_tro) values(?, ?, ?, ?, ?)"
			);
			ppst.setString(1, user.getTen());
			ppst.setString(2, user.getEmail());
			ppst.setString(3, user.getMatKhau());
			ppst.setString(4, user.getAnh());
			ppst.setString(5, user.getVaiTro());
			ppst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean tonTaiUser(String email) {
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong from user where email = ?;"
			);
			ppst.setString(1, email);
			ResultSet res = ppst.executeQuery();
			if (res.next() && res.getInt("so_luong") == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@Override
	public int getNextUserId() {
		int id = 0;
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong from user;"
			);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				id = res.getInt("so_luong") + 1;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
