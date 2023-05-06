package fruitshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fruitshop.dao.Dao;
import fruitshop.iservice.ISanPhamService;
import fruitshop.model.SanPham;

public class SanPhamService implements ISanPhamService {
	private static Connection conn = Dao.getConnection();
	
	public SanPhamService() {}

	@Override
	public List<SanPham> getSanPhamOrderBySoLuongBan(int limit) {
		List<SanPham> list = new ArrayList<>();
		try {
			PreparedStatement ppstm = conn.prepareStatement(
			    "select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sum(dh.so_luong) as so_luong_ban, sp.don_vi, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
			    + "from donhang dh, sanpham sp\r\n"
			    + "where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
			    + "group by dh.id_sp\r\n"
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
				sanPham.setSoSaoVote(res.getFloat("so_sao_vote"));
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
			    "select sp.id, sp.ten, sp.tien_tren_don_vi, sp.anh, sp.don_vi, sum(dh.so_luong) as so_luong_ban, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
			    + "from donhang dh, sanpham sp\r\n"
			    + "where dh.id_sp = sp.id and dh.so_sao_vote is not null\r\n"
			    + "group by dh.id_sp\r\n"
			    + "order by so_sao_vote desc\r\n"
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
				sanPham.setSoSaoVote(res.getFloat("so_sao_vote"));
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
				"select sp.*, sum(dh.so_luong) as so_luong_ban, round(cast(sum(dh.so_sao_vote) as float) / count(sp.id), 1) as so_sao_vote\r\n"
				+ "from donhang dh, sanpham sp\r\n"
				+ "where dh.id_sp = sp.id and sp.id = ? and dh.so_sao_vote is not null\r\n"
				+ "group by dh.id_sp"
			);
			ppst.setInt(1, id);
			ResultSet res = ppst.executeQuery();
			while (res.next()) {
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
				sanPham.setSoSaoVote(res.getFloat("so_sao_vote"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sanPham;
	}

}
