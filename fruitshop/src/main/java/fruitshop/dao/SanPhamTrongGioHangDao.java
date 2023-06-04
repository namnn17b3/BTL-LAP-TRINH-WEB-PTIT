package fruitshop.dao;

import java.util.List;

import fruitshop.model.SanPhamTrongGioHang;

public interface SanPhamTrongGioHangDao {
	public int getSoLuongSanPhamTrongGioHangByIdUser(int idUser);
	public List<SanPhamTrongGioHang> getSanPhamTrongGioHangByPage(int idUser, int page);
	public List<SanPhamTrongGioHang> getAllSanPhamTrongGioHang(int idUser);
	public SanPhamTrongGioHang getSanPhamTrongGioHangByIdUserAndIdSanPham(int idUser, int idSanPham);
	public int getTongTienByIdUser(int idUser);
	public void capNhatSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang);
	public void xoaSanPhamTrongGioHangByIdUserAndIdSanPham(int idUser, int idSanPham);
	public void xoaSanPhamTrongGioHangByIdUser(int idUser);
	public void themSanPhamTrongGioHang(SanPhamTrongGioHang sanPhamTrongGioHang);
}
