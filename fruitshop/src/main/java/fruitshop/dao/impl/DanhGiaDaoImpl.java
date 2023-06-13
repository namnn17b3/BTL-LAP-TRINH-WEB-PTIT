package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.DanhGiaDao;
import fruitshop.model.DanhGia;

public class DanhGiaDaoImpl implements DanhGiaDao {
	
	public DanhGiaDaoImpl() {}
	
	private static HikariDataSource poolConnection = PoolConnection.getPoolConnection();
	
	@Override
	public List<DanhGia> getAllDanhGiaChoSanPhamById(int idSanPham, int choose) {
		List<DanhGia> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select dg.*, u.ten, u.anh\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where id_sp = ? and dg.id_user = u.id"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc;"
				);
				ppst.setInt(1, idSanPham);
			}
			else {
				ppst = conn.prepareStatement(
					"select dg.*, u.ten, u.anh\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where id_sp = ? and dg.id_user = u.id and dg.so_sao_vote = ?\r\n"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc;"
				);
				ppst.setInt(1, idSanPham);
				ppst.setInt(1, choose);
			}
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DanhGia danhGia = new DanhGia();
				danhGia.setId(res.getInt("id"));
				danhGia.setIdUser(res.getInt("id_user"));
				danhGia.setIdSanPham(res.getInt("id_sp"));
				if (choose == -1) {
					danhGia.setSoSaoVote(res.getInt("so_sao_vote"));
				}
				else {
					danhGia.setSoSaoVote(choose);
				}
				danhGia.setNoiDungBinhLuan(res.getString("noi_dung_binh_luan"));
				String anhUser = res.getString("anh");
				if (anhUser == null) {
					anhUser = "./img/fb-no-img.png";
				}
				danhGia.setAnhUser(anhUser);
				danhGia.setNgayBinhLuan(res.getTimestamp("ngay_binh_luan"));
				list.add(danhGia);
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
		return list;
	}
	
	@Override
	public int getAllSoLuongDanhGiaChoSanPhamByIdAndStar(int idSanPham, int choose) {
		int soLuong = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where id_sp = ? and dg.id_user = u.id\r\n"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc;"
				);
				ppst.setInt(1, idSanPham);
			}
			else {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where id_sp = ? and dg.id_user = u.id and dg.so_sao_vote = ?\r\n"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc;"
				);
				ppst.setInt(1, idSanPham);
				ppst.setInt(2, choose);
			}
			ResultSet res = ppst.executeQuery();
			res.next();
			soLuong = res.getInt("so_luong");
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return soLuong;
	}
	
	@Override
	public List<DanhGia> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page) {
		List<DanhGia> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select dg.*, u.ten, u.anh\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where dg.id_sp = ? and dg.id_user = u.id\r\n"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc\r\n"
					+ "limit ?, 5;"
				);
				ppst.setInt(1, idSanPham);
				ppst.setInt(2, (page - 1) * 5);
			}
			else {
				ppst = conn.prepareStatement(
					"select dg.*, u.ten, u.anh\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where dg.id_sp = ? and dg.id_user = u.id and dg.so_sao_vote = ?\r\n"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc\r\n"
					+ "limit ?, 5;"
				);
				ppst.setInt(1, idSanPham);
				ppst.setInt(2, choose);
				ppst.setInt(3, (page - 1) * 5);
			}
			ResultSet res = ppst.executeQuery();
			while(res.next()) {
				DanhGia danhGia = new DanhGia();
				danhGia.setId(res.getInt("id"));
				danhGia.setIdUser(res.getInt("id_user"));
				danhGia.setIdSanPham(res.getInt("id_sp"));
				danhGia.setTenUser(res.getString("ten"));
				if (choose == -1) {
					danhGia.setSoSaoVote(res.getInt("so_sao_vote"));
				}
				else {
					danhGia.setSoSaoVote(choose);
				}
				danhGia.setNoiDungBinhLuan(res.getString("noi_dung_binh_luan"));
				String anhUser = res.getString("anh");
				if (anhUser == null) {
					anhUser = "./img/fb-no-img.png";
				}
				danhGia.setAnhUser(anhUser);
				danhGia.setNgayBinhLuan(res.getTimestamp("ngay_binh_luan"));
				list.add(danhGia);
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
		return list;
	}
	
	@Override
	public DanhGia getDanhGiaByIdUserIdSanPhamIdDanhSachDonHang(int idUser, int idSanPham, int idDanhSachDonHang) {
		Connection conn = null;
		DanhGia danhGia = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select dg.*\r\n"
				+ "from danhsachdonhang dsdh, danhgia dg\r\n"
				+ "where dg.id_user = ? and dg.id_sp = ? and dsdh.id = ?;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, idSanPham);
			ppst.setInt(3, idDanhSachDonHang);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				danhGia = new DanhGia();
				danhGia.setId(res.getInt("id"));
				danhGia.setIdUser(res.getInt("id_user"));
				danhGia.setIdSanPham(res.getInt("id_sp"));
				danhGia.setSoSaoVote(res.getInt("so_sao_vote"));
				danhGia.setNoiDungBinhLuan(res.getString("noi_dung_binh_luan"));
				danhGia.setNgayBinhLuan(res.getTimestamp("ngay_binh_luan"));
			}
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
		return danhGia;
	}
	
	@Override
	public void themDanhGia(DanhGia danhGia) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"insert into danhgia(id_user, id_sp, noi_dung_binh_luan, so_sao_vote, ngay_binh_luan)\r\n"
				+ "values(?, ?, ?, ?, ?);"
			);
			ppst.setInt(1, danhGia.getIdUser());
			ppst.setInt(2, danhGia.getIdSanPham());
			ppst.setString(3, danhGia.getNoiDungBinhLuan().replaceAll("\r\n", "\\\\n"));
			ppst.setInt(4, danhGia.getSoSaoVote());
			ppst.setTimestamp(5, new java.sql.Timestamp(danhGia.getNgayBinhLuan().getTime()));
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
	public void capNhatDanhGia(DanhGia danhGia) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"update danhgia\r\n"
				+ "set so_sao_vote = ?, noi_dung_binh_luan = ?, ngay_binh_luan = ?\r\n"
				+ "where id = ?;"
			);
			ppst.setInt(1, danhGia.getSoSaoVote());
			ppst.setString(2, danhGia.getNoiDungBinhLuan().replaceAll("\r\n", "\\\\n"));
			ppst.setTimestamp(3, new java.sql.Timestamp(danhGia.getNgayBinhLuan().getTime()));
			ppst.setInt(4, danhGia.getId());
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
	public void xoaDanhGia(DanhGia danhGia) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"delete from danhgia where id = ?;"
			);
			ppst.setInt(1, danhGia.getId());
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
}
