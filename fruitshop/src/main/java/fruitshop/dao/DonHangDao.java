package fruitshop.dao;

import java.util.List;

import fruitshop.model.DonHang;

public interface DonHangDao {
	public List<DonHang> getAllDanhGiaChoSanPhamById(int idSanPham, int choose);
	public int getAllSoLuongDanhGiaChoSanPhamByIdAndStar(int idSanPham, int choose);
	public List<DonHang> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page);
}
