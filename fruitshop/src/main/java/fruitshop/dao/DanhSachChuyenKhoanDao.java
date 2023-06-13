package fruitshop.dao;

import java.util.List;

import fruitshop.model.DanhSachChuyenKhoan;
import fruitshop.model.DanhSachDonHang;

public interface DanhSachChuyenKhoanDao {
	public void themDanhSachChuyenKhoan(DanhSachChuyenKhoan danhSachChuyenKhoan);
	public List<DanhSachChuyenKhoan> getListDanhSachChuyenKhoanByListDanhSachDonHang(List<DanhSachDonHang> listDanhSachDonHang);
	public DanhSachChuyenKhoan getDanhSachChuyenKhoanByIdDanhSachDonHang(int idDanhSachDonHang);
//	public void xoaDanhSachChuyenKhoanByIdDanhSachDonHang(int idDanhSachDonHang);
}
