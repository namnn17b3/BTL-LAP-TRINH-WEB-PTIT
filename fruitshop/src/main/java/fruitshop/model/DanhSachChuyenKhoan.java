package fruitshop.model;

import java.util.Date;

public class DanhSachChuyenKhoan {
	private int id;
	private int idDanhSachDonHang;
	private Date ngayChuyenKhoan;
	private int soTienChuyenKhoan;
	private String soTaiKhoanNguoiChuyen;
	private String tenNganHangNguoiChuyen;
	
	public DanhSachChuyenKhoan() {}

	public DanhSachChuyenKhoan(int id, int idDanhSachDonHang, Date ngayChuyenKhoan, int soTienChuyenKhoan,
			String soTaiKhoanNguoiChuyen, String tenNganHangNguoiChuyen) {
		super();
		this.id = id;
		this.idDanhSachDonHang = idDanhSachDonHang;
		this.ngayChuyenKhoan = ngayChuyenKhoan;
		this.soTienChuyenKhoan = soTienChuyenKhoan;
		this.soTaiKhoanNguoiChuyen = soTaiKhoanNguoiChuyen;
		this.tenNganHangNguoiChuyen = tenNganHangNguoiChuyen;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdDanhSachDonHang() {
		return idDanhSachDonHang;
	}

	public void setIdDanhSachDonHang(int idDanhSachDonHang) {
		this.idDanhSachDonHang = idDanhSachDonHang;
	}

	public Date getNgayChuyenKhoan() {
		return ngayChuyenKhoan;
	}

	public void setNgayChuyenKhoan(Date ngayChuyenKhoan) {
		this.ngayChuyenKhoan = ngayChuyenKhoan;
	}

	public int getSoTienChuyenKhoan() {
		return soTienChuyenKhoan;
	}

	public void setSoTienChuyenKhoan(int soTienChuyenKhoan) {
		this.soTienChuyenKhoan = soTienChuyenKhoan;
	}

	public String getSoTaiKhoanNguoiChuyen() {
		return soTaiKhoanNguoiChuyen;
	}

	public void setSoTaiKhoanNguoiChuyen(String soTaiKhoanNguoiChuyen) {
		this.soTaiKhoanNguoiChuyen = soTaiKhoanNguoiChuyen;
	}

	public String getTenNganHangNguoiChuyen() {
		return tenNganHangNguoiChuyen;
	}

	public void setTenNganHangNguoiChuyen(String tenNganHangNguoiChuyen) {
		this.tenNganHangNguoiChuyen = tenNganHangNguoiChuyen;
	};
}
