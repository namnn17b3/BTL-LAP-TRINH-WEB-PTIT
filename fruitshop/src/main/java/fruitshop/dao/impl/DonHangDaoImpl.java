package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fruitshop.dao.DonHangDao;
import fruitshop.model.DonHang;

public class DonHangDaoImpl implements DonHangDao {
	private Connection conn = JDBCConnection.getConnection();
	
	public DonHangDaoImpl() {}
	
	@Override
	public List<DonHang> getAllDanhGiaChoSanPhamById(int idSanPham, int choose) {
		List<DonHang> list = new ArrayList<>();
		try {
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select dg.*, u.ten\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where id_sp = ? and dg.id_user = u.id"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc;"
				);
				ppst.setInt(1, idSanPham);
			}
			else {
				ppst = conn.prepareStatement(
					"select dg.*, u.ten\r\n"
					+ "from danhgia dg, user u\r\n"
					+ "where id_sp = ? and dg.id_user = u.id and dg.so_sao_vote = ?\r\n"
					+ "order by dg.so_sao_vote desc, dg.ngay_binh_luan desc;"
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
		return soLuong;
	}
	
	@Override
	public List<DonHang> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page) {
		List<DonHang> list = new ArrayList<>();
		try {
			PreparedStatement ppst = null;
			if (choose == -1) {
				ppst = conn.prepareStatement(
					"select dg.*, u.ten\r\n"
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
					"select dg.*, u.ten\r\n"
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

	@Override
	public int getSoLuongSanPhamTrongGioHangByIdUser(int idUser) {
		/*HashMap<Integer, DonHang> map = new HashMap<>();
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select a1.*, a2.so_luong_ban\r\n"
				+ "from\r\n"
				+ "(\r\n"
				+ "	select dh.id, dh.id_sp, dh.id_user, sp.ten as ten_sp, sp.tien_tren_don_vi, dh.so_luong, dh.trang_thai, sp.anh, sp.so_luong_nhap\r\n"
				+ "	from donhang dh, sanpham sp\r\n"
				+ "	where dh.id_user = ? and sp.id = dh.id_sp and dh.trang_thai = 'giỏ hàng'\r\n"
				+ ") as a1,\r\n"
				+ "(\r\n"
				+ "	select dh.id_sp, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "    from donhang dh, sanpham sp\r\n"
				+ "    where dh.id_sp = sp.id\r\n"
				+ "    group by sp.id\r\n"
				+ ") as a2\r\n"
				+ "where a1.id_sp = a2.id_sp;"
			);
			ppst.setInt(1, idUser);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DonHang donHang = new DonHang();
				donHang.setId(res.getInt("id"));
				donHang.setIdSanPham(res.getInt("id_sp"));
				donHang.setIdUser(res.getInt("id_user"));
				donHang.setTenSanPham(res.getString("ten_sp"));
				donHang.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				donHang.setSoLuong(res.getInt("so_luong"));
				donHang.setAnh(res.getString("anh"));
				donHang.setSoLuongSanPhamConLai(res.getInt("so_luong_nhap") - res.getInt("so_luong_ban"));
				map.put(donHang.getIdSanPham(), donHang);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;*/
		
		int soLuong = 0;
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong from (\r\n"
				+ "	select a1.*, a2.so_luong_ban\r\n"
				+ "	from\r\n"
				+ "	(\r\n"
				+ "		select dh.id, dh.id_sp, dh.id_user, sp.ten as ten_sp, sp.tien_tren_don_vi, dh.so_luong, dh.trang_thai, sp.anh, dh.ngay_xuat, sp.so_luong_nhap\r\n"
				+ "		from donhang dh, sanpham sp\r\n"
				+ "		where dh.id_user = ? and sp.id = dh.id_sp and dh.trang_thai = 'giỏ hàng'\r\n"
				+ "	) as a1,\r\n"
				+ "	(\r\n"
				+ "		select dh.id_sp, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "		from donhang dh, sanpham sp\r\n"
				+ "		where dh.id_sp = sp.id\r\n"
				+ "		group by sp.id\r\n"
				+ "	) as a2\r\n"
				+ "	where a1.id_sp = a2.id_sp\r\n"
				+ ") as tmp;"
			);
			ppst.setInt(1, idUser);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				soLuong = res.getInt("so_luong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	
	@Override
	public List<DonHang> getSanPhamTrongGioHangByPage(int idUser, int page) {
		List<DonHang> listDonHang = new ArrayList<>();
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select a1.*, a2.so_luong_ban\r\n"
				+ "from\r\n"
				+ "(\r\n"
				+ "	select dh.id, dh.id_sp, dh.id_user, sp.ten as ten_sp, sp.tien_tren_don_vi, dh.so_luong, dh.trang_thai, sp.anh, dh.ngay_xuat, sp.so_luong_nhap\r\n"
				+ "	from donhang dh, sanpham sp\r\n"
				+ "	where dh.id_user = ? and sp.id = dh.id_sp and dh.trang_thai = 'giỏ hàng'\r\n"
				+ ") as a1,\r\n"
				+ "(\r\n"
				+ "	select dh.id_sp, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "    from donhang dh, sanpham sp\r\n"
				+ "    where dh.id_sp = sp.id\r\n"
				+ "    group by sp.id\r\n"
				+ ") as a2\r\n"
				+ "where a1.id_sp = a2.id_sp\r\n"
				+ "order by a1.ngay_xuat desc\r\n"
				+ "limit ?, 5;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, (page - 1) * 5);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DonHang donHang = new DonHang();
				donHang.setId(res.getInt("id"));
				donHang.setIdSanPham(res.getInt("id_sp"));
				donHang.setIdUser(res.getInt("id_user"));
				donHang.setTenSanPham(res.getString("ten_sp"));
				donHang.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				donHang.setSoLuong(res.getInt("so_luong"));
				donHang.setAnh(res.getString("anh"));
				donHang.setNgayXuat(new Date(res.getTimestamp("ngay_xuat").getTime()));
				donHang.setSoLuongSanPhamConLai(res.getInt("so_luong_nhap") - res.getInt("so_luong_ban"));
				listDonHang.add(donHang);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listDonHang;
	}
	
	@Override
	public DonHang getDonHangByIdUserAndIdSanPham(int idUser, int idSanPham) {
		DonHang donHang = null;
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select *\r\n"
				+ "from donhang dh\r\n"
				+ "where dh.id_user = ? and id_sp = ?;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, idSanPham);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				donHang = new DonHang();
				donHang.setId(res.getInt("id"));
				donHang.setIdSanPham(res.getInt("id_sp"));
				donHang.setIdUser(res.getInt("id_user"));
				donHang.setSoLuong(res.getInt("so_luong"));
				donHang.setTrangThai(res.getString("trang_thai"));
				donHang.setNgayXuat(new Date(res.getTimestamp("ngay_xuat").getTime()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return donHang;
	}
	
	@Override
	public int getTongTienByIdUser(int idUser) {
		int tongTien = 0;
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select sum(dh.so_luong * sp.tien_tren_don_vi) as thanh_tien\r\n"
				+ "from donhang dh, sanpham sp\r\n"
				+ "where dh.id_user = ? and dh.id_sp = sp.id\r\n"
				+ "group by dh.id_user;\r\n"
			);
			ppst.setInt(1, idUser);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				tongTien = res.getInt("thanh_tien");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tongTien;
	}
	
	@Override
	public void xoaDonHangByIdUserAndIdSanPham(int idUser, int idSanPham) {
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"delete from donhang dh\r\n"
				+ "where dh.id_user = ? and dh.id_sp = ?;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, idSanPham);
			ppst.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void themDonHang(DonHang donHang) {
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"insert into donhang(id_sp, id_user, ten_nguoi_nhan, so_luong, dia_chi_nguoi_nhan, so_dien_thoai_nguoi_nhan, trang_thai, ghi_chu, ngay_xuat, noi_dung_binh_luan, ngay_binh_luan, so_sao_vote, thanh_toan)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
			);
			ppst.setInt(1, donHang.getIdSanPham());
			ppst.setInt(2, donHang.getIdUser());
			if (donHang.getTenNguoiNhan() == null) {
				ppst.setNull(3, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(3,  donHang.getTenNguoiNhan());
			}
			ppst.setInt(4, donHang.getSoLuong());
			if (donHang.getDiaChiNguoiNhan() == null) {
				ppst.setNull(5, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(5, donHang.getDiaChiNguoiNhan());
			}
			if (donHang.getSoDienThoaiNguoiNhan() == null) {
				ppst.setNull(6, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(6, donHang.getSoDienThoaiNguoiNhan());
			}
			ppst.setString(7, donHang.getTrangThai());
			if (donHang.getGhiChu() == null) {
				ppst.setNull(8, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(8, donHang.getGhiChu());
			}
			ppst.setTimestamp(9, new java.sql.Timestamp(donHang.getNgayXuat().getTime()));
			if (donHang.getNoiDungBinhLuan() == null) {
				ppst.setNull(10, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(10, donHang.getNoiDungBinhLuan());
			}
			if (donHang.getNgayBinhLuan() == null) {
				ppst.setNull(11, java.sql.Types.TIMESTAMP);
			}
			else {
				ppst.setTimestamp(11, new java.sql.Timestamp(donHang.getNgayBinhLuan().getTime()));
			}
			if (donHang.getSoSaoVote() == -1) {
				ppst.setNull(12, java.sql.Types.INTEGER);
			}
			else {
				ppst.setInt(12, donHang.getSoSaoVote());
			}
			if (donHang.getThanhToan() == null) {
				ppst.setNull(13, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(13, donHang.getThanhToan());
			}
			ppst.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void capNhatDonHang(DonHang donHang) {
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"update donhang\r\n"
				+ "set id_sp = ?, id_user = ?, ten_nguoi_nhan = ?, so_luong = ?,\r\n"
				+ "dia_chi_nguoi_nhan = ?, so_dien_thoai_nguoi_nhan = ?, trang_thai = ?, ghi_chu = ?, ngay_xuat = ?, noi_dung_binh_luan = ?,\r\n"
				+ "ngay_binh_luan = ?, so_sao_vote = ?, thanh_toan = ?\r\n"
				+ "where id = ?;"
			);
			ppst.setInt(1, donHang.getIdSanPham());
			ppst.setInt(2, donHang.getIdUser());
			if (donHang.getTenNguoiNhan() == null) {
				ppst.setNull(3, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(3,  donHang.getTenNguoiNhan());
			}
			ppst.setInt(4, donHang.getSoLuong());
			if (donHang.getDiaChiNguoiNhan() == null) {
				ppst.setNull(5, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(5, donHang.getDiaChiNguoiNhan());
			}
			if (donHang.getSoDienThoaiNguoiNhan() == null) {
				ppst.setNull(6, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(6, donHang.getSoDienThoaiNguoiNhan());
			}
			ppst.setString(7, donHang.getTrangThai());
			if (donHang.getGhiChu() == null) {
				ppst.setNull(8, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(8, donHang.getGhiChu());
			}
			ppst.setTimestamp(9, new java.sql.Timestamp(donHang.getNgayXuat().getTime()));
			if (donHang.getNoiDungBinhLuan() == null) {
				ppst.setNull(10, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(10, donHang.getNoiDungBinhLuan());
			}
			if (donHang.getNgayBinhLuan() == null) {
				ppst.setNull(11, java.sql.Types.TIMESTAMP);
			}
			else {
				ppst.setTimestamp(11, new java.sql.Timestamp(donHang.getNgayBinhLuan().getTime()));
			}
			if (donHang.getSoSaoVote() == -1) {
				ppst.setNull(12, java.sql.Types.INTEGER);
			}
			else {
				ppst.setInt(12, donHang.getSoSaoVote());
			}
			if (donHang.getThanhToan() == null) {
				ppst.setNull(13, java.sql.Types.VARCHAR);
			}
			else {
				ppst.setString(13, donHang.getThanhToan());
			}
			ppst.setInt(14, donHang.getId());
			ppst.execute();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
