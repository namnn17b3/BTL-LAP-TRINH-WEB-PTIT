package fruitshop.dao;

import java.util.List;

import fruitshop.model.DanhGia;

public interface DanhGiaDao {
	public List<DanhGia> getAllDanhGiaChoSanPhamById(int idSanPham, int choose);
	public int getAllSoLuongDanhGiaChoSanPhamByIdAndStar(int idSanPham, int choose);
	public List<DanhGia> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page);
	public DanhGia getDanhGiaByIdUserIdSanPhamIdDanhSachDonHang(int idUser, int idSanPham, int idDanhSachDonHang);
	public void themDanhGia(DanhGia danhGia);
	public void capNhatDanhGia(DanhGia danhGia);
	public void xoaDanhGia(DanhGia danhGia);
	public int getSoLuongDanhGia();
}
