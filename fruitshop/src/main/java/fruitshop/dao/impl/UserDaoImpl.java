package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.UserDao;
import fruitshop.model.User;

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
			ppst.setString(3, BCrypt.hashpw(user.getMatKhau(), BCrypt.gensalt(12)));
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
				"select mat_khau from user where email = binary ?;"
			);
			ppst.setString(1, email);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				String passwordDataBase = res.getString("mat_khau");
				return BCrypt.checkpw(password, passwordDataBase);
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
	public User getUserById(int id) {
		User user = null;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select * from user where id = ?;"
			);
			ppst.setInt(1, id);
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
				+ "set email = ?, ten = ?, mat_khau = ?, anh = ?, trang_thai = ?\r\n"
				+ "where email = binary ?;"
			);
			ppst.setString(1, user.getEmail());
			ppst.setString(2, user.getTen());
			ppst.setString(3, BCrypt.hashpw(user.getMatKhau(), BCrypt.gensalt(12)));
			if (user.getAnh().equals("./img_user/fb-no-img.png") == false) {
				ppst.setString(4, user.getAnh());
			}
			else {
				ppst.setNull(4, java.sql.Types.VARCHAR);
			}
			ppst.setInt(5, user.getTrangThai());
			ppst.setString(6, user.getEmail());
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
	public void upDateUserById(User user) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			if (!user.getMatKhau().equals("")) {
				PreparedStatement ppst = conn.prepareStatement(
					"update user\r\n"
					+ "set email = ?, ten = ?, mat_khau = ?, anh = ?, trang_thai = ?\r\n"
					+ "where id = ?;"
				);
				ppst.setString(1, user.getEmail());
				ppst.setString(2, user.getTen());
				ppst.setString(3, BCrypt.hashpw(user.getMatKhau(), BCrypt.gensalt(12)));
				if (user.getAnh().equals("./img_user/fb-no-img.png") == false) {
					ppst.setString(4, user.getAnh());
				}
				else {
					ppst.setNull(4, java.sql.Types.VARCHAR);
				}
				ppst.setInt(5, user.getTrangThai());
				ppst.setInt(6, user.getId());
				ppst.executeUpdate();
			}
			else {
				PreparedStatement ppst = conn.prepareStatement(
					"update user\r\n"
					+ "set email = ?, ten = ?, anh = ?, trang_thai = ?\r\n"
					+ "where id = ?;"
				);
				ppst.setString(1, user.getEmail());
				ppst.setString(2, user.getTen());
				if (user.getAnh().equals("./img_user/fb-no-img.png") == false) {
					ppst.setString(3, user.getAnh());
				}
				else {
					ppst.setNull(3, java.sql.Types.VARCHAR);
				}
				ppst.setInt(4, user.getTrangThai());
				ppst.setInt(5, user.getId());
				ppst.executeUpdate();
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
	
	@Override
	public List<User> getListTop5KhachHangTheoTongChiTieu() {
		List<User> listUser = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select u.id, u.anh, u.ten, u.email, sum(dsdh.tong_tien) as tong_chi_tieu\r\n"
				+ "from user u, danhsachdonhang dsdh\r\n"
				+ "where u.id = dsdh.id_user and dsdh.ngay_nhan is not null\r\n"
				+ "group by u.id\r\n"
				+ "order by tong_chi_tieu desc\r\n"
				+ "limit 5;"
			);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setId(res.getInt("id"));
				user.setAnh(res.getString("anh"));
				user.setTen(res.getString("ten"));
				if (user.getAnh() == null) {
					user.setAnh("../img_user/fb-no-img.png");
				}
				else {
					user.setAnh("." + user.getAnh());
				}
				user.setEmail(res.getString("email"));
				user.setTongChiTieu(res.getLong("tong_chi_tieu"));
				listUser.add(user);
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
		return listUser;
	}
	
	@Override
	public List<User> getListTop5KhachHangTheoSoLuongMua() {
		List<User> listUser = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select u.id, u.anh, u.ten, u.email, sum(dh.so_luong) as so_luong_da_mua\r\n"
				+ "from user u, danhsachdonhang dsdh, donhang dh\r\n"
				+ "where u.id = dsdh.id_user and dh.id_dsdh = dsdh.id and dsdh.ngay_nhan is not null\r\n"
				+ "group by u.id\r\n"
				+ "order by so_luong_da_mua desc\r\n"
				+ "limit 5;"
			);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setId(res.getInt("id"));
				user.setAnh(res.getString("anh"));
				user.setTen(res.getString("ten"));
				if (user.getAnh() == null) {
					user.setAnh("../img_user/fb-no-img.png");
				}
				else {
					user.setAnh("." + user.getAnh());
				}
				user.setEmail(res.getString("email"));
				user.setSoLuongDaMua(res.getInt("so_luong_da_mua"));
				listUser.add(user);
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
		return listUser;
	}
	
	@Override
	public List<User> getListUserWithPagination(String queryText, int page, int itemInPage) {
		List<User> listUser = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppstm = null;
			
			
			if (queryText != null && !queryText.equals("")) {
				String sql = "select *\r\n"
				+ "from `user` u\r\n"
				+ "where vai_tro != 'a'\r\n"
				+ "and (\r\n"
				+ "	match(u.ten) against(? in natural language mode)\r\n"
				+ "	or match(u.email) against(? in natural language mode)\r\n"
				+ ")\r\n"
				+ "order by id desc\r\n"
				+ "limit ?, ?;";
				ppstm = conn.prepareStatement(sql);
				ppstm.setString(1, queryText);
				ppstm.setString(2, queryText);
				ppstm.setInt(3, (page - 1) * itemInPage);
				ppstm.setInt(4, itemInPage);
			}
			else {
				String sql = "select * from `user` where vai_tro != 'a' order by id desc limit ?, ?;";
				ppstm = conn.prepareStatement(sql);
				ppstm.setInt(1, (page - 1) * itemInPage);
				ppstm.setInt(2, itemInPage);
			}
			
			ResultSet res = ppstm.executeQuery();
			while (res.next()) {
				User user = new User();
				user.setId(res.getInt("id"));
				user.setAnh(res.getString("anh"));
				user.setTen(res.getString("ten"));
				if (user.getAnh() == null) {
					user.setAnh("../img_user/fb-no-img.png");
				}
				else {
					user.setAnh("." + user.getAnh());
				}
				user.setEmail(res.getString("email"));
				listUser.add(user);
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
		return listUser;
	}
	
	@Override
	public int getSoLuongUserWithPagination(String queryText) {
		int soLuongUser = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppstm = null;
			
			if (queryText != null && !queryText.equals("")) {
				String sql = "select count(*) as so_luong_user\r\n"
				+ "from `user` u\r\n"
				+ "where vai_tro != 'a'\r\n"
				+ "and (\r\n"
				+ "	match(u.ten) against(? in natural language mode)\r\n"
				+ "	or match(u.email) against(? in natural language mode)\r\n"
				+ ");\r\n";
				ppstm = conn.prepareStatement(sql);
				ppstm.setString(1, queryText);
				ppstm.setString(2, queryText);
			}
			else {
				String sql = "select count(*) as so_luong_user from `user` where vai_tro != 'a';";
				ppstm = conn.prepareStatement(sql);
			}
			
			ResultSet res = ppstm.executeQuery();
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
	
	@Override
	public void deleteUserById(int id) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppstm = conn.prepareStatement(
				"delete from `user` where id = ?"
			);
			ppstm.setInt(1, id);
			ppstm.executeUpdate();
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
	public void deleteUserByEmail(String email) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppstm = conn.prepareStatement(
				"delete from `user` where email = ?"
			);
			ppstm.setString(1, email);
			ppstm.executeUpdate();
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
}
