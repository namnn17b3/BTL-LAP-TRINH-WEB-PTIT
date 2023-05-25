package fruitshop.dao;

import java.util.List;

import fruitshop.model.DanhGia;

public interface DanhGiaDao {
	public List<DanhGia> getAllDanhGiaChoSanPhamById(int idSanPham, int choose);
	public int getAllSoLuongDanhGiaChoSanPhamByIdAndStar(int idSanPham, int choose);
	public List<DanhGia> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page);
}
