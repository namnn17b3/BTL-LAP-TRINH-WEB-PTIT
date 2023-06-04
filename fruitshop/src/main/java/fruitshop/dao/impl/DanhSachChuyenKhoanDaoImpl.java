package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.DanhSachChuyenKhoanDao;
import fruitshop.model.DanhSachChuyenKhoan;

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
}
