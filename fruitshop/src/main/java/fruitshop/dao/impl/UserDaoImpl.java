package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fruitshop.dao.UserDao;
import fruitshop.model.User;

public class UserDaoImpl implements UserDao {
	private Connection conn = JDBCConnection.getConnection();
	
	public UserDaoImpl() {}

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
			if (user.getAnh().equals("./img_user/fb-no-img.png")) {
				ppst.setNull(4, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(4, user.getAnh());
			}
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
	public long getNextUserId() {
		long id = 0;
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
	
	@Override
	public boolean tonTaiUser(String email, String password) {
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select * from user where email = ? and mat_khau = ?;"
			);
			ppst.setString(1, email);
			ppst.setString(2, password);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select * from user where email = ?;"
			);
			ppst.setString(1, email);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				user = new User();
				user.setId(res.getInt("id"));
				user.setTen(res.getString("ten"));
				user.setEmail(res.getString("email"));
				user.setMatKhau(res.getString("mat_khau"));
				user.setAnh(res.getString("anh"));
				if (user.getAnh() == null) {
					user.setAnh("./img_user/fb-no-img.png");
				}
				user.setVaiTro(res.getString("vai_tro"));
				user.setTrangThai(res.getInt("trang_thai"));
				return user;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void upDateUserByEmail(User user) {
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"update user\r\n"
				+ "set ten = ?, mat_khau = ?, anh = ?, trang_thai = ?\r\n"
				+ "where email = ?;"
			);
			ppst.setString(1, user.getTen());
			ppst.setString(2, user.getMatKhau());
			if (user.getAnh().equals("./img_user/fb-no-img.png") == false) {
				ppst.setString(3, user.getAnh());
			}
			else {
				ppst.setNull(3, java.sql.Types.VARCHAR);
			}
			ppst.setInt(4, user.getTrangThai());
			ppst.setString(5, user.getEmail());
			ppst.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
