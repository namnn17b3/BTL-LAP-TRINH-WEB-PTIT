package fruitshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.zaxxer.hikari.HikariDataSource;

import fruitshop.dao.DonHangDao;
import fruitshop.model.DonHang;

public class DonHangDaoImpl implements DonHangDao {
	
	public DonHangDaoImpl() {}
	
	private static HikariDataSource poolConnection = PoolConnection.getPoolConnection();
	
	/*@Override
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
		
		/*int soLuong = 0;
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
	}*/
	
	@Override
	public void themDonHang(DonHang donHang) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"insert into donhang(\r\n"
				+ "id_sp,\r\n"
				+ "id_dsdh, so_luong)\r\n"
				+ "values(?, ?, ?);"
			);
			ppst.setInt(1, donHang.getIdSanPham());
			ppst.setInt(2,  donHang.getIdDanhSachDonHang());
			ppst.setInt(3, donHang.getSoLuong());
			ppst.execute();
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
	public void capNhatDonHang(DonHang donHang) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"update donhang\r\n"
				+ "set id_sp = ?, id_dsdh = ?, so_luong = ?\r\n"
				+ "where id = ?;"
			);
			ppst.setInt(1, donHang.getIdSanPham());
			ppst.setInt(2, donHang.getIdDanhSachDonHang());
			ppst.setInt(3, donHang.getSoLuong());
			ppst.setInt(4, donHang.getId());
			ppst.execute();
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
	public void xoaDonHangByIdDanhSachDonHang(int idDanhSachDonHang) {
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"delete from donhang where id_dsdh = ?;"
			);
			ppst.setInt(1, idDanhSachDonHang);
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
	public int getSoLuongDonHangByIdDanhSachDonHang(int idDanhSachDonHang) {
		int soLuong = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong from donhang where id_dsdh = ?;"
			);
			ppst.setInt(1, idDanhSachDonHang);
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
	public List<DonHang> getListDonHangByIdDanhSachDonHang(int idDanhSachDonHang, int page) {
		List<DonHang> listDonHang = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select dh.id_sp, sp.ten, sp.anh, sp.tien_tren_don_vi as don_gia, sp.don_vi, dh.so_luong\r\n"
				+ "from donhang dh, sanpham sp\r\n"
				+ "where dh.id_sp = sp.id and dh.id_dsdh = ?\r\n"
				+ "limit ?, 5;"
			);
			ppst.setInt(1, idDanhSachDonHang);
			ppst.setInt(2, (page - 1) * 5);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DonHang donHang = new DonHang();
				donHang.setIdDanhSachDonHang(idDanhSachDonHang);
				donHang.setIdSanPham(res.getInt("id_sp"));
				donHang.setTenSanPham(res.getString("ten"));
				donHang.setAnh(res.getString("anh"));
				donHang.setDonGia(res.getInt("don_gia"));
				donHang.setSoLuong(res.getInt("so_luong"));
				donHang.setDonVi(res.getString("don_vi"));
				listDonHang.add(donHang);
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
		return listDonHang;
	}
	
	@Override
	public List<DonHang> getAllDonHangByIdDanhSachDonHang(int idDanhSachDonHang) {
		List<DonHang> listDonHang = new ArrayList<>();
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select dh.id_sp, sp.ten, sp.anh, sp.tien_tren_don_vi as don_gia, sp.don_vi, dh.so_luong\r\n"
				+ "from donhang dh, sanpham sp\r\n"
				+ "where dh.id_sp = sp.id and dh.id_dsdh = ?;"
			);
			ppst.setInt(1, idDanhSachDonHang);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				DonHang donHang = new DonHang();
				donHang.setIdDanhSachDonHang(idDanhSachDonHang);
				donHang.setIdSanPham(res.getInt("id_sp"));
				donHang.setTenSanPham(res.getString("ten"));
				donHang.setAnh(res.getString("anh"));
				donHang.setDonGia(res.getInt("don_gia"));
				donHang.setSoLuong(res.getInt("so_luong"));
				donHang.setDonVi(res.getString("don_vi"));
				listDonHang.add(donHang);
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
		return listDonHang;
	}
	
	@Override
	public int tonTaiDanhGiaDonHang(int idUser, int idSanPham, int idDanSachDonHang) {
		int ok = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong\r\n"
				+ "from danhsachdonhang dsdh, danhgia dg\r\n"
				+ "where dg.id_user = ? and dg.id_sp = ? and dsdh.id = ?;"
			);
			ppst.setInt(1, idUser);
			ppst.setInt(2, idSanPham);
			ppst.setInt(3, idDanSachDonHang);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				ok = res.getInt("so_luong");
			}
			if (ok > 0) {
				ok = 1;
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
		return ok;
	}
	
	@Override
	public DonHang getDonHangByIdSanPhamIdDanhDonHang(int idSanPham, int idDanhSachDonHang) {
		DonHang donHang = null;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select dh.id_sp, sp.ten, sp.anh, sp.tien_tren_don_vi as don_gia, sp.don_vi, dh.so_luong\r\n"
				+ "from donhang dh, sanpham sp\r\n"
				+ "where dh.id_sp = sp.id and dh.id_sp = ? and dh.id_dsdh = ?;"
			);
			ppst.setInt(1, idSanPham);
			ppst.setInt(2, idDanhSachDonHang);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				donHang = new DonHang();
				donHang.setIdDanhSachDonHang(idDanhSachDonHang);
				donHang.setIdSanPham(res.getInt("id_sp"));
				donHang.setTenSanPham(res.getString("ten"));
				donHang.setAnh(res.getString("anh"));
				donHang.setDonGia(res.getInt("don_gia"));
				donHang.setSoLuong(res.getInt("so_luong"));
				donHang.setDonVi(res.getString("don_vi"));
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
		return donHang;
	}
	
	@Override
	public int getSoLuongSanPhamDaBan() {
		int soLuongSanPhamDaBan = 0;
		Connection conn = null;
		try {
			conn = poolConnection.getConnection();
			PreparedStatement ppst = conn.prepareStatement(
				"select sum(dh.so_luong) as so_luong_san_pham_da_ban\r\n"
				+ "from donhang dh, danhsachdonhang dsdh\r\n"
				+ "where dh.id_dsdh = dsdh.id and dsdh.ngay_xuat is not null;"
			);
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				soLuongSanPhamDaBan = res.getInt("so_luong_san_pham_da_ban");
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
		return soLuongSanPhamDaBan;
	}
}
