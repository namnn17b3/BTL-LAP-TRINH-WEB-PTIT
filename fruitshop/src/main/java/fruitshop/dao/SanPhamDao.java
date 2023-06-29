package fruitshop.dao;

import java.util.List;

import fruitshop.model.SanPham;

public interface SanPhamDao {

	public List<SanPham> getSanPhamOrderBySoLuongBan(int limit);
	public List<SanPham> getSanPhamOrderBySoSao(int limit);
	public SanPham getSanPhamById(int id);
	public int getSoLuongSanPhamByLoai(String loai);
	public List<SanPham> getListSanPhamByLoai(String loai, int page);
	public List<SanPham> searchSanPhamByName(String tenSanPham, int page);
	public int getSoLuongSanPhamByName(String tenSanPham);
	public List<SanPham> getListTop10SanPhamTheoDoanhThu();
	public List<SanPham> getListTop10SanPhamTheoSoLuong();
}
