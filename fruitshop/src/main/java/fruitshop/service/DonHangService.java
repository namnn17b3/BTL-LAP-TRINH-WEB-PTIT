package fruitshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fruitshop.dao.Dao;
import fruitshop.iservice.IDonHangService;
import fruitshop.model.DonHang;

public class DonHangService implements IDonHangService {

	private static Connection conn = Dao.getConnection();
	
	@Override
	public List<DonHang> getAllDanhGiaChoSanPhamById(int idSanPham, int choose) {
		List<DonHang> list = new ArrayList<>();
		try {
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from (\r\n"
					+ "	select u.ten, dh.so_sao_vote, dh.noi_dung_binh_luan, dh.ngay_binh_luan, u.anh\r\n"
					+ "	from donhang dh, user u\r\n"
					+ "	where dh.id_sp = ? and dh.id_user = u.id and dh.trang_thai = 'đã vote'\r\n"
					+ "    order by dh.ngay_binh_luan desc\r\n"
					+ ") as tmp;"
				);
				ppst.setInt(1, idSanPham);
			}
			else {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from(\r\n"
					+ "	select u.ten, dh.noi_dung_binh_luan, dh.ngay_binh_luan, u.anh\r\n"
					+ "	from donhang dh, user u\r\n"
					+ "	where dh.id_sp = ? and dh.id_user = u.id and dh.so_sao_vote = ?\r\n"
					+ "    order by dh.ngay_binh_luan desc\r\n"
					+ ") as tmp;"
				);
				ppst.setInt(1, idSanPham);
				ppst.setInt(1, choose);
			}
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DonHang donHang = new DonHang();
				donHang.setTenUser(res.getString("ten"));
				if (choose == -1) {
					donHang.setSoSaoVote(res.getInt("so_sao_vote"));
				}
				else {
					donHang.setSoSaoVote(choose);
				}
				donHang.setNoiDungBinhLuan(res.getString("noi_dung_binh_luan"));
				String anhUser = res.getString("anh");
				if (anhUser == null) {
					anhUser = "./img/fb-no-img.png";
				}
				donHang.setAnhUser(anhUser);
				donHang.setNgayBinhLuan(res.getTimestamp("ngay_binh_luan"));
				list.add(donHang);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int getAllSoLuongDanhGiaChoSanPhamByIdAndStar(int idSanPham, int choose) {
		int soLuong = 0;
		try {
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong\r\n"
					+ "from (\r\n"
					+ "	select u.ten, dh.so_sao_vote, dh.noi_dung_binh_luan, dh.ngay_binh_luan\r\n"
					+ "	from donhang dh, user u\r\n"
					+ "	where dh.id_sp = ? and dh.id_user = u.id and dh.trang_thai = 'đã vote'\r\n"
					+ "    order by dh.ngay_binh_luan desc\r\n"
					+ ") as tmp;"
				);
				ppst.setInt(1, idSanPham);
			}
			else {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong\r\n"
					+ "from (\r\n"
					+ "	select u.ten, dh.noi_dung_binh_luan, dh.ngay_binh_luan\r\n"
					+ "	from donhang dh, user u\r\n"
					+ "	where dh.id_sp = ? and dh.id_user = u.id and dh.so_sao_vote = ?\r\n"
					+ "    order by dh.ngay_binh_luan desc\r\n"
					+ ") as tmp;"
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
		return soLuong;
	}
	
	@Override
	public List<DonHang> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page) {
		List<DonHang> list = new ArrayList<>();
		try {
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from(\r\n"
					+ "	select u.ten, dh.so_sao_vote, dh.noi_dung_binh_luan, dh.ngay_binh_luan, u.anh\r\n"
					+ "	from donhang dh, user u\r\n"
					+ "	where dh.id_sp = ? and dh.id_user = u.id and dh.trang_thai = 'đã vote'\r\n"
					+ "    order by dh.ngay_binh_luan desc\r\n"
					+ ") as tmp\r\n"
					+ "limit ?, 5;"
				);
				ppst.setInt(1, idSanPham);
				ppst.setInt(2, (page - 1) * 5);
			}
			else {
				ppst = conn.prepareStatement(
					"select *\r\n"
					+ "from(\r\n"
					+ "	select u.ten, dh.noi_dung_binh_luan, dh.ngay_binh_luan, u.anh\r\n"
					+ "	from donhang dh, user u\r\n"
					+ "	where dh.id_sp = ? and dh.id_user = u.id and dh.so_sao_vote = ?\r\n"
					+ "    order by dh.ngay_binh_luan desc\r\n"
					+ ") as tmp\r\n"
					+ "limit ?, 5;"
				);
				ppst.setInt(1, idSanPham);
				ppst.setInt(2, choose);
				ppst.setInt(3, (page - 1) * 5);
			}
			ResultSet res = ppst.executeQuery();
			while(res.next()) {
				DonHang donHang = new DonHang();
				donHang.setTenUser(res.getString("ten"));
				if (choose == -1) {
					donHang.setSoSaoVote(res.getInt("so_sao_vote"));
				}
				else {
					donHang.setSoSaoVote(choose);
				}
				donHang.setNoiDungBinhLuan(res.getString("noi_dung_binh_luan"));
				String anhUser = res.getString("anh");
				if (anhUser == null) {
					anhUser = "./img/fb-no-img.png";
				}
				donHang.setAnhUser(anhUser);
				donHang.setNgayBinhLuan(res.getTimestamp("ngay_binh_luan"));
				list.add(donHang);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
