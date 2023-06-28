package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.UserDao;
import fruitshop.model.User;
import fruitshop.utils.AES;

public class UserDaoImpl implements UserDao {
	
	public UserDaoImpl() {}

	private static HikariDataSource poolConnection = PoolConnection.getPoolConnection();
	
	@Override
	public void addUser(User user) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"insert into user(ten, email, mat_khau, anh, vai_tro) values(?, ?, ?, ?, ?)"
			);
			ppst.setString(1, user.getTen());
			ppst.setString(2, user.getEmail());
			ppst.setString(3, AES.encryptAES(user.getMatKhau()));
			if (user.getAnh().equals("./img_user/fb-no-img.png")) {
				ppst.setNull(4, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(4, user.getAnh());
			}
			ppst.setString(5, user.getVaiTro());
			ppst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean tonTaiUser(String email) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong from user where email = binary ?;"
			);
			ppst.setString(1, email);
			ResultSet res = ppst.executeQuery();
			if (res.next() && res.getInt("so_luong") == 0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return true;
	}
	
	@Override
	public boolean tonTaiUser(String email, String password) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select * from user where email = binary ? and mat_khau = binary ?;"
			);
			ppst.setString(1, email);
			ppst.setString(2, AES.encryptAES(password));
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				return true;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return false;
	}
	
	@Override
	public User getUserByEmail(String email) {
		User user = null;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select * from user where email = binary ?;"
			);
			ppst.setString(1, email);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				user = new User();
				user.setId(res.getInt("id"));
				user.setTen(res.getString("ten"));
				user.setEmail(res.getString("email"));
				user.setMatKhau(AES.decryptAES(res.getString("mat_khau")));
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
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public void upDateUserByEmail(User user) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"update user\r\n"
				+ "set ten = ?, mat_khau = ?, anh = ?, trang_thai = ?\r\n"
				+ "where email = ?;"
			);
			ppst.setString(1, user.getTen());
			ppst.setString(2, AES.encryptAES(user.getMatKhau()));
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
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	@Override
	public int getSoLuongUserOnline() {
		int soLuongUserOnline = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong_user_online\r\n"
				+ "from user\r\n"
				+ "where trang_thai = 1;"
			);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				soLuongUserOnline = res.getInt("so_luong_user_online");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return soLuongUserOnline;
	}
	
	@Override
	public int getSoLuongUser() {
		int soLuongUser = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong_user\r\n"
				+ "from user;"
			);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				soLuongUser = res.getInt("so_luong_user");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return soLuongUser;
	}
}
