package fruitshop.dao;

import java.util.List;

import fruitshop.model.DonHang;

public interface DonHangDao {
	public List<DonHang> getAllDanhGiaChoSanPhamById(int idSanPham, int choose);
	public int getAllSoLuongDanhGiaChoSanPhamByIdAndStar(int idSanPham, int choose);
	public List<DonHang> getDanhGiaChoSanPhamByIdAndPageChoose(int idSanPham, int choose, int page);
	public int getSoLuongSanPhamTrongGioHangByIdUser(int idUser);
	public List<DonHang> getSanPhamTrongGioHangByPage(int idUser, int page);
	public DonHang getDonHangByIdUserAndIdSanPham(int idUser, int idSanPham);
	public void themDonHang(DonHang donHang);
	public void capNhatDonHang(DonHang donHang);
	public int getTongTienByIdUser(int idUser);
	public void xoaDonHangByIdUserAndIdSanPham(int idUser, int idSanPham);
}
