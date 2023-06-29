package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.SanPhamTrongGioHangDao;
import fruitshop.model.SanPhamTrongGioHang;

public class SanPhamTrongGioHangDaoImpl implements SanPhamTrongGioHangDao {
	
	public SanPhamTrongGioHangDaoImpl() {}
	
	private static HikariDataSource poolConnection = PoolConnection.getPoolConnection();

	@Override
	public int getSoLuongSanPhamTrongGioHangByIdUser(int idUser) {
		Connection conn = null;
		int soLuong = 0;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong from sanphamtronggiohang where id_user = ?;"
			);
			ppst.setInt(1, idUser);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				soLuong = res.getInt("so_luong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return soLuong;
	}
	
	@Override
	public List<SanPhamTrongGioHang> getSanPhamTrongGioHangByPage(int idUser, int page) {
		Connection conn = null;
		List<SanPhamTrongGioHang> listSanPhamTrongGioHang = new ArrayList<>();
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select a1.*, a2.so_luong_ban\r\n"
				+ "from\r\n"
				+ "(\r\n"
				+ "	select sptgh.*, sp.ten as ten_sp, sp.tien_tren_don_vi, sp.anh, sp.so_luong_nhap, sp.don_vi\r\n"
				+ "	from sanphamtronggiohang sptgh, sanpham sp\r\n"
				+ "	where sptgh.id_user = ? and sptgh.id_sp = sp.id\r\n"
				+ ") as a1,\r\n"
				+ "(\r\n"
				+ "	select dh.id_sp, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "	from donhang dh, danhsachdonhang dsdh\r\n"
				+ " where dh.id_dsdh = dsdh.id and dsdh.ngay_nhan is not null\r\n"
				+ "	group by dh.id_sp\r\n"
				+ ") as a2\r\n"
				+ "where a1.id_sp = a2.id_sp\r\n"
				+ "order by ngay_them desc\r\n"
				+ "limit ?, 5;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, (page - 1) * 5);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				SanPhamTrongGioHang sanPhamTrongGioHang = new SanPhamTrongGioHang();
				sanPhamTrongGioHang.setId(res.getInt("id"));
				sanPhamTrongGioHang.setIdSanPham(res.getInt("id_sp"));
				sanPhamTrongGioHang.setIdUser(res.getInt("id_user"));
				sanPhamTrongGioHang.setTenSanPham(res.getString("ten_sp"));
				sanPhamTrongGioHang.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPhamTrongGioHang.setSoLuong(res.getInt("so_luong"));
				sanPhamTrongGioHang.setAnh(res.getString("anh"));
				sanPhamTrongGioHang.setNgayThem(new Date(res.getTimestamp("ngay_them").getTime()));
				sanPhamTrongGioHang.setSoLuongSanPhamConLai(res.getInt("so_luong_nhap") - res.getInt("so_luong_ban"));
				sanPhamTrongGioHang.setDonVi(res.getString("don_vi"));
				listSanPhamTrongGioHang.add(sanPhamTrongGioHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listSanPhamTrongGioHang;
	}
	
	@Override
	public List<SanPhamTrongGioHang> getAllSanPhamTrongGioHang(int idUser) {
		List<SanPhamTrongGioHang> listSanPhamTrongGioHang = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select a1.*, a2.so_luong_ban\r\n"
				+ "from\r\n"
				+ "(\r\n"
				+ "	select sptgh.*, sp.ten as ten_sp, sp.tien_tren_don_vi, sp.anh, sp.so_luong_nhap, sp.don_vi\r\n"
				+ "	from sanphamtronggiohang sptgh, sanpham sp\r\n"
				+ "	where sptgh.id_user = ? and sptgh.id_sp = sp.id\r\n"
				+ ") as a1,\r\n"
				+ "(\r\n"
				+ "	select dh.id_sp, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "	from donhang dh, danhsachdonhang dsdh\r\n"
				+ " where dh.id_dsdh = dsdh.id and dsdh.ngay_nhan is not null\r\n"
				+ "	group by dh.id_sp\r\n"
				+ ") as a2\r\n"
				+ "where a1.id_sp = a2.id_sp\r\n"
				+ "order by ngay_them desc;"
			);
			ppst.setInt(1, idUser);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				SanPhamTrongGioHang sanPhamTrongGioHang = new SanPhamTrongGioHang();
				sanPhamTrongGioHang.setId(res.getInt("id"));
				sanPhamTrongGioHang.setIdSanPham(res.getInt("id_sp"));
				sanPhamTrongGioHang.setIdUser(res.getInt("id_user"));
				sanPhamTrongGioHang.setTenSanPham(res.getString("ten_sp"));
				sanPhamTrongGioHang.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPhamTrongGioHang.setSoLuong(res.getInt("so_luong"));
				sanPhamTrongGioHang.setAnh(res.getString("anh"));
				sanPhamTrongGioHang.setNgayThem(new Date(res.getTimestamp("ngay_them").getTime()));
				sanPhamTrongGioHang.setSoLuongSanPhamConLai(res.getInt("so_luong_nhap") - res.getInt("so_luong_ban"));
				sanPhamTrongGioHang.setDonVi(res.getString("don_vi"));
				listSanPhamTrongGioHang.add(sanPhamTrongGioHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listSanPhamTrongGioHang;
	}
	
	@Override
	public SanPhamTrongGioHang getSanPhamTrongGioHangByIdUserAndIdSanPham(int idUser, int idSanPham) {
		Connection conn = null;
		SanPhamTrongGioHang sanPhamTrongGioHang = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
					"select a1.*, a2.so_luong_ban\r\n"
					+ "from\r\n"
					+ "(\r\n"
					+ "	select sptgh.*, sp.ten as ten_sp, sp.tien_tren_don_vi, sp.anh, sp.so_luong_nhap, sp.don_vi\r\n"
					+ "	from sanphamtronggiohang sptgh, sanpham sp\r\n"
					+ "	where sptgh.id_user = ? and sptgh.id_sp = sp.id\r\n"
					+ ") as a1,\r\n"
					+ "(\r\n"
					+ "	select dh.id_sp, sum(dh.so_luong) as so_luong_ban\r\n"
					+ "	from donhang dh, danhsachdonhang dsdh\r\n"
					+ " where dh.id_dsdh = dsdh.id and dh.id_sp = ? and dsdh.ngay_nhan is not null\r\n"
					+ "	group by dh.id_sp\r\n"
					+ ") as a2\r\n"
					+ "where a1.id_sp = a2.id_sp\r\n"
					+ "order by ngay_them desc;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, idSanPham);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				sanPhamTrongGioHang = new SanPhamTrongGioHang();
				sanPhamTrongGioHang.setId(res.getInt("id"));
				sanPhamTrongGioHang.setIdSanPham(res.getInt("id_sp"));
				sanPhamTrongGioHang.setIdUser(res.getInt("id_user"));
				sanPhamTrongGioHang.setTenSanPham(res.getString("ten_sp"));
				sanPhamTrongGioHang.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPhamTrongGioHang.setSoLuong(res.getInt("so_luong"));
				sanPhamTrongGioHang.setAnh(res.getString("anh"));
				sanPhamTrongGioHang.setNgayThem(new Date(res.getTimestamp("ngay_them").getTime()));
				sanPhamTrongGioHang.setSoLuongSanPhamConLai(res.getInt("so_luong_nhap") - res.getInt("so_luong_ban"));
				sanPhamTrongGioHang.setDonVi(res.getString("don_vi"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sanPhamTrongGioHang;
	}
	
	@Override
	public int getTongTienByIdUser(int idUser) {
		Connection conn = null;
		int tongTien = 0;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select sum(sptgh.so_luong * sp.tien_tren_don_vi) as thanh_tien\r\n"
				+ "from sanphamtronggiohang sptgh, sanpham sp\r\n"
				+ "where sptgh.id_user = ? and sptgh.id_sp = sp.id\r\n"
				+ "group by sptgh.id_user;"
			);
			ppst.setInt(1, idUser);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				tongTien = res.getInt("thanh_tien");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tongTien;
	}
	
	@Override
	public void capNhatSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"update sanphamtronggiohang\r\n"
				+ "set so_luong = ?, ngay_them = ?\r\n"
				+ "where id_user = ? and id_sp = ?;"
			);
			ppst.setInt(1, sanPhamTrongGioHang.getSoLuong());
			ppst.setTimestamp(2, new java.sql.Timestamp(sanPhamTrongGioHang.getNgayThem().getTime()));
			ppst.setInt(3, sanPhamTrongGioHang.getIdUser());
			ppst.setInt(4, sanPhamTrongGioHang.getIdSanPham());
			ppst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void xoaSanPhamTrongGioHangByIdUserAndIdSanPham(int idUser, int idSanPham) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"delete from sanphamtronggiohang dh\r\n"
				+ "where dh.id_user = ? and dh.id_sp = ?;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, idSanPham);
			ppst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void xoaSanPhamTrongGioHangByIdUser(int idUser) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"delete from sanphamtronggiohang dh\r\n"
				+ "where dh.id_user = ?;"
			);
			ppst.setInt(1, idUser);
			ppst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void themSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"insert into sanphamtronggiohang(id_user, id_sp, so_luong, ngay_them) values(?, ?, ?, ?);"
			);
			System.out.println("line 302 san pham trong gio hang dao impl " + sanPhamTrongGioHang.getIdUser());
			ppst.setInt(1, sanPhamTrongGioHang.getIdUser());
			ppst.setInt(2, sanPhamTrongGioHang.getIdSanPham());
			ppst.setInt(3, sanPhamTrongGioHang.getSoLuong());
			ppst.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			ppst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
