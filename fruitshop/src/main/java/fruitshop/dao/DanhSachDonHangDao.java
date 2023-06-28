package fruitshop.dao;

import java.util.Date;
import java.util.List;

import fruitshop.model.DanhSachDonHang;

public interface DanhSachDonHangDao {
	public void themDanhSachDonHang(DanhSachDonHang danhSachDonHang);
	public int getIdLastDanhSachDonHang();
	public List<DanhSachDonHang> getAllDanhSachDonHangByIdUser(int idUser);
	public int getSoLuongDanhSachDonHangByIdUser(int idUser, int choose);
	public List<DanhSachDonHang> getListDanhSachDonHangByIdUser(int idUser, int choose, int page);
	public DanhSachDonHang getDanhSachDonHangById(int idDanhSachDonHang);
	public void updateDanhSachDonHang(DanhSachDonHang danhSachDonHang);
	public long getTongDoanhThu();
	public long getDoanhThuTheoNgay(Date date);
	public long getDoanhThuTheoThang(int nam, int thang);
}
