package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
				+ "dia_chi_nguoi_nhan, so_dien_thoai_nguoi_nhan,\r\n"
				+ "ngay_xuat, ngay_gui, ngay_nhan, tong_tien, thanh_toan)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?);"
			);
			ppst.setInt(1, danhSachDonHang.getIdUser());
			ppst.setString(2, danhSachDonHang.getTenNguoiNhan());
			ppst.setString(3, danhSachDonHang.getDiaChiNguoiNhan());
			ppst.setString(4, danhSachDonHang.getSoDienThoaiNguoiNhan());
			ppst.setTimestamp(5, new java.sql.Timestamp(danhSachDonHang.getNgayXuat().getTime()));
			ppst.setNull(6, java.sql.Types.TIMESTAMP);
			ppst.setNull(7, java.sql.Types.TIMESTAMP);
			ppst.setInt(8, danhSachDonHang.getTongTien());
			ppst.setString(9, danhSachDonHang.getThanhToan());
			
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

}
