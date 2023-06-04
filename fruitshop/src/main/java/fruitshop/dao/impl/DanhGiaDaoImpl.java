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
				DanhGia DanhGia = new DanhGia();
				DanhGia.setTenUser(res.getString("ten"));
				if (choose == -1) {
					DanhGia.setSoSaoVote(res.getInt("so_sao_vote"));
				}
				else {
					DanhGia.setSoSaoVote(choose);
				}
				DanhGia.setNoiDungBinhLuan(res.getString("noi_dung_binh_luan"));
				String anhUser = res.getString("anh");
				if (anhUser == null) {
					anhUser = "./img/fb-no-img.png";
				}
				DanhGia.setAnhUser(anhUser);
				DanhGia.setNgayBinhLuan(res.getTimestamp("ngay_binh_luan"));
				list.add(DanhGia);
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
}
