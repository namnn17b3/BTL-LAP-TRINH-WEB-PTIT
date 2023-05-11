package fruitshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fruitshop.dao.Dao;
import fruitshop.iservice.ISanPhamService;
import fruitshop.model.SanPham;

public class SanPhamService implements ISanPhamService {
	private static Connection conn = Dao.getConnection();
	private static String[] tmp = {"Nam Phi", "Chile", "Hàn Quốc", "Úc", "New Zealand", "Mỹ", "Nhiều nước"};
	private static List<String> loaiTraiCayTheoNguonGoc = Arrays.asList(tmp);
	
	public SanPhamService() {}

	@Override
	public List<SanPham> getSanPhamOrderBySoLuongBan(int limit) {
		List<SanPham> list = new ArrayList<>();
		try {
			PreparedStatement ppstm = conn.prepareStatement(
			    "select a1.id, a1.ten, a1.tien_tren_don_vi, a1.anh, a1.don_vi, a1.so_luong_ban, a2.so_sao_vote\r\n"
			    + "from\r\n"
			    + "(\r\n"
			    + "	select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, sum(dh.so_luong) as so_luong_ban\r\n"
			    + "	from sanpham sp, donhang dh\r\n"
			    + "	where sp.id = dh.id_sp\r\n"
			    + "	group by dh.id_sp\r\n"
			    + ") as a1 left join\r\n"
			    + "(\r\n"
			    + "	select sp.id, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
			    + "	from donhang dh, sanpham sp\r\n"
			    + "	where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
			    + "	group by dh.id_sp\r\n"
			    + ") as a2\r\n"
			    + "on a1.id = a2.id\r\n"
			    + "order by so_luong_ban desc\r\n"
			    + "limit "+limit+";"
			);
			ResultSet res = ppstm.executeQuery();
			while (res.next()) {
				SanPham sanPham = new SanPham();
				sanPham.setId(res.getInt("id"));
				sanPham.setTen(res.getString("ten"));
				sanPham.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPham.setAnh(res.getString("anh"));
				sanPham.setSoLuongBan(res.getInt("so_luong_ban"));
				float soSaoVote = res.getFloat("so_sao_vote");
				if (res.wasNull()) {
					sanPham.setSoSaoVote(-1);
				}
				else {
					sanPham.setSoSaoVote(soSaoVote);
				}
				sanPham.setDonVi(res.getString("don_vi"));
				
				list.add(sanPham);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<SanPham> getSanPhamOrderBySoSao(int limit) {
		List<SanPham> list = new ArrayList<>();
		try {
			PreparedStatement ppstm = conn.prepareStatement(
			    "select tmp.*, sum(dh.so_luong) as so_luong_ban\r\n"
			    + "from (\r\n"
			    + "	select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
			    + "	from donhang dh, sanpham sp\r\n"
			    + "	where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
			    + "	group by dh.id_sp\r\n"
			    + "	order by so_sao_vote desc\r\n"
			    + "	limit 4\r\n"
			    + ") as tmp, donhang dh\r\n"
			    + "where tmp.id = dh.id_sp\r\n"
			    + "group by dh.id_sp;"
			);
			ResultSet res = ppstm.executeQuery();
			while (res.next()) {
				SanPham sanPham = new SanPham();
				sanPham.setId(res.getInt("id"));
				sanPham.setTen(res.getString("ten"));
				sanPham.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPham.setAnh(res.getString("anh"));
				sanPham.setSoLuongBan(res.getInt("so_luong_ban"));
				float soSaoVote = res.getFloat("so_sao_vote");
				if (res.wasNull()) {
					sanPham.setSoSaoVote(soSaoVote);
				}
				else {
					sanPham.setSoSaoVote(-1);
				}
				sanPham.setDonVi(res.getString("don_vi"));
				
				list.add(sanPham);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public SanPham getSanPhamById(int id) {
		SanPham sanPham = new SanPham();
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select a1.*, a2.so_sao_vote\r\n"
				+ "from\r\n"
				+ "(\r\n"
				+ "	select sp.*, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "	from sanpham sp, donhang dh\r\n"
				+ "	where sp.id = dh.id_sp and sp.id = ?\r\n"
				+ ") as a1\r\n"
				+ "left join\r\n"
				+ "(\r\n"
				+ "	select sp.*, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
				+ "	from donhang dh, sanpham sp\r\n"
				+ "	where dh.id_sp = sp.id and sp.id = ? and dh.so_sao_vote is not null\r\n"
				+ "	group by dh.id_sp\r\n"
				+ ") as a2\r\n"
				+ "on a1.id = a2.id;"
			);
			ppst.setInt(1, id);
			ppst.setInt(2, id);
			ResultSet res = ppst.executeQuery();
			boolean tonTaiSanPham = false;
			while (res.next()) {
				tonTaiSanPham = true;
				sanPham.setId(id);
				sanPham.setTen(res.getString("ten"));
				sanPham.setDonVi(res.getString("don_vi"));
				sanPham.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPham.setNguonGoc(res.getString("nguon_goc"));
				sanPham.setSoLuongNhap(res.getInt("so_luong_nhap"));
				sanPham.setSoLuongTrenDonVi(res.getString("so_luong_tren_don_vi"));
				sanPham.setTomTat(res.getString("tom_tat"));
				sanPham.setVi(res.getString("vi"));
				sanPham.setDinhDuong(res.getString("dinh_duong"));
				sanPham.setBaoQuan(res.getString("bao_quan"));
				sanPham.setAnh(res.getString("anh"));
				sanPham.setSoLuongBan(res.getInt("so_luong_ban"));
				float soSaoVote = res.getFloat("so_sao_vote");
				if (res.wasNull()) {
					sanPham.setSoSaoVote(-1);
				}
				else {
					sanPham.setSoSaoVote(soSaoVote);
				}
			}
			if (tonTaiSanPham == false) {
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sanPham;
	}

	@Override
	public List<SanPham> getListSanPhamByLoai(String loai, int page) {
		List<SanPham> list = new ArrayList<>();
		boolean tonTaiLoaiSanPham = false;
		PreparedStatement ppst = null;
		try {
			if (loai.equals("Tất cả")) {
				tonTaiLoaiSanPham = true;
				ppst = conn.prepareStatement(
					"select a1.id, a1.ten, a1.tien_tren_don_vi, a1.anh, a1.don_vi, a1.so_luong_ban, a2.so_sao_vote\r\n"
					+ "from\r\n"
					+ "(\r\n"
					+ "	select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, sum(dh.so_luong) as so_luong_ban\r\n"
					+ "	from sanpham sp, donhang dh\r\n"
					+ "	where sp.id = dh.id_sp\r\n"
					+ "	group by dh.id_sp\r\n"
					+ ") as a1 left join\r\n"
					+ "(\r\n"
					+ "	select sp.id, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
					+ "	from donhang dh, sanpham sp\r\n"
					+ "	where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
					+ "	group by dh.id_sp\r\n"
					+ ") as a2\r\n"
					+ "on a1.id = a2.id\r\n"
					+ "order by so_luong_ban desc, so_sao_vote desc\r\n"
					+ "limit ?, 12;"
				);
				ppst.setInt(1, (page - 1) * 5);
			}
			else if (loai.equals("Giỏ quà tặng trái cây") || loai.equals("Mua nguyên thùng")
					|| loai.equals("Đồ khô") || loai.equals("Combo") || loai.equals("Mua lẻ")) {
				tonTaiLoaiSanPham = true;
				ppst = conn.prepareStatement(
					"select a1.id, a1.ten, a1.tien_tren_don_vi, a1.anh, a1.don_vi, a1.so_luong_ban, a1.phan_loai, a2.so_sao_vote\r\n"
					+ "from\r\n"
					+ "(\r\n"
					+ "	select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, sp.phan_loai, sum(dh.so_luong) as so_luong_ban\r\n"
					+ "	from sanpham sp, donhang dh\r\n"
					+ "	where sp.id = dh.id_sp\r\n"
					+ "	group by dh.id_sp\r\n"
					+ ") as a1 left join\r\n"
					+ "(\r\n"
					+ "	select sp.id, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
					+ "	from donhang dh, sanpham sp\r\n"
					+ "	where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
					+ "	group by dh.id_sp\r\n"
					+ ") as a2\r\n"
					+ "on a1.id = a2.id\r\n"
					+ "where phan_loai = ?\r\n"
					+ "order by so_luong_ban desc, so_sao_vote desc\r\n"
					+ "limit ?, 12;"
				);
				ppst.setString(1, loai);
				ppst.setInt(2, (page - 1) * 12);
			}
			else if (loaiTraiCayTheoNguonGoc.contains(loai) == true) {
				System.out.println(loai);
				tonTaiLoaiSanPham = true;
				ppst = conn.prepareStatement(
					"select a1.id, a1.ten, a1.tien_tren_don_vi, a1.anh, a1.don_vi, a1.so_luong_ban, a1.nguon_goc, a2.so_sao_vote\r\n"
					+ "from\r\n"
					+ "(\r\n"
					+ "	select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, sp.nguon_goc, sum(dh.so_luong) as so_luong_ban\r\n"
					+ "	from sanpham sp, donhang dh\r\n"
					+ "	where sp.id = dh.id_sp\r\n"
					+ "	group by dh.id_sp\r\n"
					+ ") as a1 left join\r\n"
					+ "(\r\n"
					+ "	select sp.id, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
					+ "	from donhang dh, sanpham sp\r\n"
					+ "	where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
					+ "	group by dh.id_sp\r\n"
					+ ") as a2\r\n"
					+ "on a1.id = a2.id\r\n"
					+ "where nguon_goc = ?\r\n"
					+ "order by so_luong_ban desc, so_sao_vote desc\r\n"
					+ "limit ?, 12;"
				);
				ppst.setString(1, loai);
				ppst.setInt(2, (page - 1) * 12);
			}
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				SanPham sanPham = new SanPham();
				sanPham.setId(res.getInt("id"));
				sanPham.setTen(res.getString("ten"));
				sanPham.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPham.setAnh(res.getString("anh"));
				sanPham.setSoLuongBan(res.getInt("so_luong_ban"));
				float soSaoVote = res.getFloat("so_sao_vote");
				if (res.wasNull()) {
					sanPham.setSoSaoVote(-1);
				}
				else {
					sanPham.setSoSaoVote(soSaoVote);
				}
				sanPham.setDonVi(res.getString("don_vi"));
				
				list.add(sanPham);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		if (tonTaiLoaiSanPham == false) return null;
		return list;
	}

	@Override
	public int getSoLuongSanPhamByLoai(String loai) {
		int soLuong = 0;
		PreparedStatement ppst = null;
		try {
			if (loai.equals("Tất cả")) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong from sanpham;"
				);
			}
			else if (loai.equals("Giỏ quà tặng trái cây") || loai.equals("Mua nguyên thùng")
					|| loai.equals("Đồ khô") || loai.equals("Combo") || loai.equals("Mua lẻ")) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong\r\n"
					+ "from sanpham\r\n"
					+ "where phan_loai = ?;"
				);
				ppst.setString(1, loai);
			}
			else if (loaiTraiCayTheoNguonGoc.contains(loai) == true) {
				ppst = conn.prepareStatement(
					"select count(*) as so_luong\r\n"
					+ "from sanpham\r\n"
					+ "where nguon_goc = ?;"
				);
				ppst.setString(1, loai);
			}
			ResultSet res = ppst.executeQuery();
			if (res.next()) {
				soLuong = res.getInt("so_luong");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return soLuong;
	}
	
	@Override
	public int getSoLuongSanPhamByName(String tenSanPham) {
		int soLuong = 0;
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select count(*) as so_luong from (\r\n"
				+ "	select a1.id, a1.ten, a1.tien_tren_don_vi, a1.anh, a1.don_vi, a1.so_luong_ban, a1.nguon_goc, a2.so_sao_vote\r\n"
				+ "	from\r\n"
				+ "	(\r\n"
				+ "		select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, sp.nguon_goc, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "		from sanpham sp, donhang dh\r\n"
				+ "		where sp.id = dh.id_sp and match(sp.ten) against(? in natural language mode)\r\n"
				+ "		group by dh.id_sp\r\n"
				+ "	) as a1 left join\r\n"
				+ "	(\r\n"
				+ "		select sp.id, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
				+ "		from donhang dh, sanpham sp\r\n"
				+ "		where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
				+ "		group by dh.id_sp\r\n"
				+ "	) as a2\r\n"
				+ "	on a1.id = a2.id\r\n"
				+ "	order by id\r\n"
				+ ") as sanpham_tmp;"
			);
			ppst.setString(1, tenSanPham);
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
	public List<SanPham> searchSanPhamByName(String tenSanPham, int page) {
		List<SanPham> listSanPham = new ArrayList<>();
		try {
			PreparedStatement ppst = conn.prepareStatement(
				"select a1.id, a1.ten, a1.tien_tren_don_vi, a1.anh, a1.don_vi, a1.so_luong_ban, a1.nguon_goc, a2.so_sao_vote\r\n"
				+ "from\r\n"
				+ "(\r\n"
				+ "	select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, sp.nguon_goc, sum(dh.so_luong) as so_luong_ban\r\n"
				+ "	from sanpham sp, donhang dh\r\n"
				+ "	where sp.id = dh.id_sp and match(sp.ten) against(? in natural language mode)\r\n"
				+ "	group by dh.id_sp\r\n"
				+ ") as a1 left join\r\n"
				+ "(\r\n"
				+ "	select sp.id, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
				+ "	from donhang dh, sanpham sp\r\n"
				+ "	where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
				+ "	group by dh.id_sp\r\n"
				+ ") as a2\r\n"
				+ "on a1.id = a2.id\r\n"
				+ "order by so_luong_ban desc, so_sao_vote desc\r\n"
				+ "limit ?, 12;"
			);
			ppst.setString(1, tenSanPham);
			ppst.setInt(2, (page - 1) * 12);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
				SanPham sanPham = new SanPham();
				sanPham.setId(res.getInt("id"));
				sanPham.setTen(res.getString("ten"));
				sanPham.setTienTrenDonVi(res.getInt("tien_tren_don_vi"));
				sanPham.setAnh(res.getString("anh"));
				sanPham.setSoLuongBan(res.getInt("so_luong_ban"));
				float soSaoVote = res.getFloat("so_sao_vote");
				if (res.wasNull()) {
					sanPham.setSoSaoVote(-1);
				}
				else {
					sanPham.setSoSaoVote(soSaoVote);
				}
				sanPham.setDonVi(res.getString("don_vi"));
				
				listSanPham.add(sanPham);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listSanPham;
	}
}
