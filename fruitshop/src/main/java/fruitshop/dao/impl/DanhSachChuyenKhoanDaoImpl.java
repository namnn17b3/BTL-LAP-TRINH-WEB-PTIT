package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.DanhSachChuyenKhoanDao;
import fruitshop.model.DanhSachChuyenKhoan;
import fruitshop.model.DanhSachDonHang;

public class DanhSachChuyenKhoanDaoImpl implements DanhSachChuyenKhoanDao {

	public DanhSachChuyenKhoanDaoImpl() {};
	
	private static HikariDataSource poolConnection = PoolConnection.getPoolConnection();
	
	@Override
	public void themDanhSachChuyenKhoan(DanhSachChuyenKhoan danhSachChuyenKhoan) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"insert into danhsachchuyenkhoan(id_dsdh, ngay_chuyen_khoan, so_tien_chuyen_khoan,\r\n"
				+ "so_tai_khoan_nguoi_chuyen, ten_ngan_hang_nguoi_chuyen)\r\n"
				+ "values(?, ?, ?, ?, ?);"
			);
			ppst.setInt(1, danhSachChuyenKhoan.getIdDanhSachDonHang());
			ppst.setTimestamp(2, new java.sql.Timestamp(danhSachChuyenKhoan.getNgayChuyenKhoan().getTime()));
			ppst.setInt(3, danhSachChuyenKhoan.getSoTienChuyenKhoan());
			ppst.setString(4, danhSachChuyenKhoan.getSoTaiKhoanNguoiChuyen());
			ppst.setString(5, danhSachChuyenKhoan.getTenNganHangNguoiChuyen());
			
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
	public List<DanhSachChuyenKhoan> getListDanhSachChuyenKhoanByListDanhSachDonHang(List<DanhSachDonHang> listDanhSachDonHang) {
		List<DanhSachChuyenKhoan> listDanhSachChuyenKhoan = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			for (DanhSachDonHang danhSachDonHang : listDanhSachDonHang) {				
				PreparedStatement ppst = conn.prepareStatement(
					"select * from danhsachchuyenkhoan where id_dsdh = ?;"
				);
				ppst.setInt(1, danhSachDonHang.getId());
				ResultSet res = ppst.executeQuery();
				DanhSachChuyenKhoan danhSachChuyenKhoan = new DanhSachChuyenKhoan();
				if (res.next()) {
					danhSachChuyenKhoan.setNgayChuyenKhoan(new Date(res.getTimestamp("ngay_chuyen_khoan").getTime()));
					danhSachChuyenKhoan.setSoTienChuyenKhoan(res.getInt("so_tien_chuyen_khoan"));
					danhSachChuyenKhoan.setSoTaiKhoanNguoiChuyen(res.getString("so_tai_khoan_nguoi_chuyen"));
					danhSachChuyenKhoan.setTenNganHangNguoiChuyen(res.getString("ten_ngan_hang_nguoi_chuyen"));
				}
				listDanhSachChuyenKhoan.add(danhSachChuyenKhoan);
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
		return listDanhSachChuyenKhoan;
	}
	
	@Override
	public DanhSachChuyenKhoan getDanhSachChuyenKhoanByIdDanhSachDonHang(int idDanhSachDonHang) {
		DanhSachChuyenKhoan danhSachChuyenKhoan = null;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select * from danhsachchuyenkhoan where id_dsdh = ?;"
			);
			ppst.setInt(1, idDanhSachDonHang);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				danhSachChuyenKhoan = new DanhSachChuyenKhoan();
				danhSachChuyenKhoan.setNgayChuyenKhoan(new Date(res.getTimestamp("ngay_chuyen_khoan").getTime()));
				danhSachChuyenKhoan.setSoTienChuyenKhoan(res.getInt("so_tien_chuyen_khoan"));
				danhSachChuyenKhoan.setSoTaiKhoanNguoiChuyen(res.getString("so_tai_khoan_nguoi_chuyen"));
				danhSachChuyenKhoan.setTenNganHangNguoiChuyen(res.getString("ten_ngan_hang_nguoi_chuyen"));
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
		return danhSachChuyenKhoan;
	}
	
//	@Override
//	public void xoaDanhSachChuyenKhoanByIdDanhSachDonHang(int idDanhSachDonHang) {
//		Connection conn = null;
//		try {
//			conn = poolConnection.getConnection();
//			PreparedStatement ppst = conn.prepareStatement(
//				"delete from danhsachchuyenkhoan where id_dsdh = ?;"
//			);
//			ppst.setInt(1, idDanhSachDonHang);
//			ppst.execute();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		finally {
//			try {
//				conn.close();
//			} catch (Exception e2) {
//				e2.printStackTrace();
//			}
//		}
//	}
}
