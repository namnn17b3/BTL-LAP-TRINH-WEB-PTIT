package fruitshop.iservice;

import java.util.List;

import fruitshop.model.DonHang;

public interface IDonHangService {
	public List<DonHang> getAllDanhGiaChoSanPhamById(int idSanPham, int choose);
	public int getAllSoLuongDanhGiaChoSanPhamByIdAndStar(int idSanPham, int choose);
	public List<DonHang> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page);
}
