package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.DanhSachDonHangDao;
import fruitshop.model.DanhSachDonHang;

public class DanhSachDonHangDaoImpl implements DanhSachDonHangDao {
	
	public DanhSachDonHangDaoImpl() {};

	private static HikariDataSource poolConnection = PoolConnection.getPoolConnection();
	
	@Override
	public void themDanhSachDonHang(DanhSachDonHang danhSachDonHang) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"insert into danhsachdonhang(id_user, ten_nguoi_nhan,\r\n"
				+ "dia_chi_nguoi_nhan, so_dien_thoai_nguoi_nhan, so_luong_san_pham,\r\n"
				+ "ngay_xuat, ngay_gui, ngay_nhan, tong_tien, thanh_toan)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
			);
			ppst.setInt(1, danhSachDonHang.getIdUser());
			ppst.setString(2, danhSachDonHang.getTenNguoiNhan());
			ppst.setString(3, danhSachDonHang.getDiaChiNguoiNhan());
			ppst.setString(4, danhSachDonHang.getSoDienThoaiNguoiNhan());
			ppst.setInt(5, danhSachDonHang.getSoLuongSanPham());
			ppst.setTimestamp(6, new java.sql.Timestamp(danhSachDonHang.getNgayXuat().getTime()));
			ppst.setNull(7, java.sql.Types.TIMESTAMP);
			ppst.setNull(8, java.sql.Types.TIMESTAMP);
			ppst.setInt(9, danhSachDonHang.getTongTien());
			ppst.setString(10, danhSachDonHang.getThanhToan());
			
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
	public int getIdLastDanhSachDonHang() {
		int id = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select id from danhsachdonhang order by id desc limit 1;"
			);
			
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				id = res.getInt("id");
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
		return id;
	}

	@Override
	public int getSoLuongDanhSachDonHangByIdUser(int idUser, int choose) {
		int soLuong = 0;
		Connection conn = null;
		PreparedStatement ppst = null;
		try {
			conn = poolConnection.getConnection();
			if (choose == 0) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong from danhsachdonhang\r\n"
					+ "where id_user = ?;"
				);
			}
			else if (choose == 1) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong from danhsachdonhang\r\n"
					+ "where id_user = ? and ngay_gui is null and ngay_nhan is null and huy = 0\r\n"
				);
			}
			else if (choose == 2) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong from danhsachdonhang\r\n"
					+ "where id_user = ? and ngay_gui is not null and ngay_nhan is null and huy = 0\r\n"
				);
			}
			else if (choose == 3) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong from danhsachdonhang\r\n"
					+ "where id_user = ? and ngay_gui is not null and ngay_nhan is not null and huy = 0\r\n"
				);
			}
			else {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong from danhsachdonhang\r\n"
					+ "where id_user = ? and huy = 1\r\n"
				);
			}
			
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
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return soLuong;
	}
	
	@Override
	public List<DanhSachDonHang> getAllDanhSachDonHangByIdUser(int idUser) {
		Connection conn = null;
		List<DanhSachDonHang> listDanhSachDonHang = new ArrayList<>();
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select *\r\n"
				+ "from danhsachdonhang\r\n"
				+ "where id_user = ?"
				+ "order by ngay_xuat desc;"
			);
			ppst.setInt(1, idUser);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DanhSachDonHang danhSachDonHang = new DanhSachDonHang();
				danhSachDonHang.setId(res.getInt("id"));
				danhSachDonHang.setTenNguoiNhan(res.getString("ten_nguoi_nhan"));
				danhSachDonHang.setIdUser(res.getInt("id_user"));
				danhSachDonHang.setDiaChiNguoiNhan(res.getString("dia_chi_nguoi_nhan"));
				danhSachDonHang.setSoDienThoaiNguoiNhan(res.getString("so_dien_thoai_nguoi_nhan"));
				danhSachDonHang.setSoLuongSanPham(res.getInt("so_luong_san_pham"));
				danhSachDonHang.setNgayXuat(new Date(res.getTimestamp("ngay_xuat").getTime()));
				Date ngayGui = res.getTimestamp("ngay_gui");
				if (res.wasNull() == false) {
					danhSachDonHang.setNgayGui(new Date(ngayGui.getTime()));
				}
				Date ngayNhan = res.getTimestamp("ngay_nhan");
				if (res.wasNull() == false) {
					danhSachDonHang.setNgayNhan(new Date(ngayNhan.getTime()));
				}
				danhSachDonHang.setTongTien(res.getInt("tong_tien"));
				danhSachDonHang.setThanhToan(res.getString("thanh_toan"));
				danhSachDonHang.setHuy(res.getInt("huy"));
				listDanhSachDonHang.add(danhSachDonHang);
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
		return listDanhSachDonHang;
	}
	
	@Override
	public List<DanhSachDonHang> getListDanhSachDonHangByIdUser(int idUser, int choose, int page) {
		Connection conn = null;
		PreparedStatement ppst = null;
		List<DanhSachDonHang> listDanhSachDonHang = new ArrayList<>();
		try {
			conn = poolConnection.getConnection();
			if (choose == 0) {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from danhsachdonhang\r\n"
					+ "where id_user = ?\r\n"
					+ "order by ngay_xuat desc\r\n"
					+ "limit ?, 5;"
				);
			}
			else if (choose == 1) {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from danhsachdonhang\r\n"
					+ "where id_user = ? and ngay_gui is null and ngay_nhan is null and huy = 0\r\n"
					+ "order by ngay_xuat desc\r\n"
					+ "limit ?, 5;"
				);
			}
			else if (choose == 2) {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from danhsachdonhang\r\n"
					+ "where id_user = ? and ngay_gui is not null and ngay_nhan is null and huy = 0\r\n"
					+ "order by ngay_xuat desc\r\n"
					+ "limit ?, 5;"
				);
			}
			else if (choose == 3) {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from danhsachdonhang\r\n"
					+ "where id_user = ? and ngay_gui is not null and ngay_nhan is not null and huy = 0\r\n"
					+ "order by ngay_xuat desc\r\n"
					+ "limit ?, 5;"
				);
			}
			else {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from danhsachdonhang\r\n"
					+ "where id_user = ? and huy = 1\r\n"
					+ "order by ngay_xuat desc\r\n"
					+ "limit ?, 5;"
				);
			}
			ppst.setInt(1, idUser);
			ppst.setInt(2, (page - 1) * 5);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DanhSachDonHang danhSachDonHang = new DanhSachDonHang();
				danhSachDonHang.setId(res.getInt("id"));
				danhSachDonHang.setTenNguoiNhan(res.getString("ten_nguoi_nhan"));
				danhSachDonHang.setIdUser(res.getInt("id_user"));
				danhSachDonHang.setDiaChiNguoiNhan(res.getString("dia_chi_nguoi_nhan"));
				danhSachDonHang.setSoDienThoaiNguoiNhan(res.getString("so_dien_thoai_nguoi_nhan"));
				danhSachDonHang.setSoLuongSanPham(res.getInt("so_luong_san_pham"));
				danhSachDonHang.setNgayXuat(new Date(res.getTimestamp("ngay_xuat").getTime()));
				Date ngayGui = res.getTimestamp("ngay_gui");
				if (res.wasNull() == false) {
					danhSachDonHang.setNgayGui(new Date(ngayGui.getTime()));
				}
				Date ngayNhan = res.getTimestamp("ngay_nhan");
				if (res.wasNull() == false) {
					danhSachDonHang.setNgayNhan(new Date(ngayNhan.getTime()));
				}
				danhSachDonHang.setTongTien(res.getInt("tong_tien"));
				danhSachDonHang.setThanhToan(res.getString("thanh_toan"));
				danhSachDonHang.setHuy(res.getInt("huy"));
				listDanhSachDonHang.add(danhSachDonHang);
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
		return listDanhSachDonHang;
	}
	
	@Override
	public DanhSachDonHang getDanhSachDonHangById(int idDanhSachDonHang) {
		DanhSachDonHang danhSachDonHang = null;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();;
			PreparedStatement ppst = conn.prepareStatement(
				"select * from danhsachdonhang where id = ?;"
			);
			ppst.setInt(1, idDanhSachDonHang);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				danhSachDonHang = new DanhSachDonHang();
				danhSachDonHang.setId(res.getInt("id"));
				danhSachDonHang.setTenNguoiNhan(res.getString("ten_nguoi_nhan"));
				danhSachDonHang.setIdUser(res.getInt("id_user"));
				danhSachDonHang.setDiaChiNguoiNhan(res.getString("dia_chi_nguoi_nhan"));
				danhSachDonHang.setSoDienThoaiNguoiNhan(res.getString("so_dien_thoai_nguoi_nhan"));
				danhSachDonHang.setSoLuongSanPham(res.getInt("so_luong_san_pham"));
				danhSachDonHang.setNgayXuat(new Date(res.getTimestamp("ngay_xuat").getTime()));
				Date ngayGui = res.getTimestamp("ngay_gui");
				if (res.wasNull() == false) {
					danhSachDonHang.setNgayGui(new Date(ngayGui.getTime()));
				}
				Date ngayNhan = res.getTimestamp("ngay_nhan");
				if (res.wasNull() == false) {
					danhSachDonHang.setNgayNhan(new Date(ngayNhan.getTime()));
				}
				danhSachDonHang.setTongTien(res.getInt("tong_tien"));
				danhSachDonHang.setThanhToan(res.getString("thanh_toan"));
				danhSachDonHang.setHuy(res.getInt("huy"));
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
		
		return danhSachDonHang;
	}
	
	@Override
	public void updateDanhSachDonHang(DanhSachDonHang danhSachDonHang) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"update danhsachdonhang\r\n"
				+ "set huy = ?\r\n"
				+ "where id = ?;"
			);
			ppst.setInt(1, danhSachDonHang.getHuy());
			ppst.setInt(2, danhSachDonHang.getId());
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
