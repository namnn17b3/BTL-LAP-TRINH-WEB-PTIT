package fruitshop.model;

import java.util.Date;

public class DanhSachDonHang {
	private int id;
	private int idUser;
	private String tenNguoiNhan;
	private String diaChiNguoiNhan;
	private String soDienThoaiNguoiNhan;
	private Date ngayGui;
	private Date ngayNhan;
	private Date ngayXuat;
	private int tongTien;
	private String thanhToan;
	private int soLuongSanPham;
	private int huy = 0;
	
	public DanhSachDonHang() {}
	
	public DanhSachDonHang(int id, String tenNguoiNhan, String diaChiNguoiNhan, String soDienThoaiNguoiNhan,
			Date ngayGui, Date ngayNhan, Date ngayXuat, int tongTien, String thanhToan, int idUser, int soLuongSanPham, int huy) {
		super();
		this.id = id;
		this.tenNguoiNhan = tenNguoiNhan;
		this.diaChiNguoiNhan = diaChiNguoiNhan;
		this.soDienThoaiNguoiNhan = soDienThoaiNguoiNhan;
		this.ngayGui = ngayGui;
		this.ngayNhan = ngayNhan;
		this.tongTien = tongTien;
		this.thanhToan = thanhToan;
		this.idUser = idUser;
		this.ngayXuat = ngayXuat;
		this.soLuongSanPham = soLuongSanPham;
		this.huy = huy;
	}

	public int getSoLuongSanPham() {
		return soLuongSanPham;
	}

	public void setSoLuongSanPham(int soLuongSanPham) {
		this.soLuongSanPham = soLuongSanPham;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTenNguoiNhan() {
		return tenNguoiNhan;
	}

	public void setTenNguoiNhan(String tenNguoiNhan) {
		this.tenNguoiNhan = tenNguoiNhan;
	}

	public String getDiaChiNguoiNhan() {
		return diaChiNguoiNhan;
	}

	public void setDiaChiNguoiNhan(String diaChiNguoiNhan) {
		this.diaChiNguoiNhan = diaChiNguoiNhan;
	}

	public String getSoDienThoaiNguoiNhan() {
		return soDienThoaiNguoiNhan;
	}

	public void setSoDienThoaiNguoiNhan(String soDienThoaiNguoiNhan) {
		this.soDienThoaiNguoiNhan = soDienThoaiNguoiNhan;
	}

	public Date getNgayGui() {
		return ngayGui;
	}

	public void setNgayGui(Date ngayGui) {
		this.ngayGui = ngayGui;
	}

	public Date getNgayNhan() {
		return ngayNhan;
	}

	public void setNgayNhan(Date ngayNhan) {
		this.ngayNhan = ngayNhan;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}

	public String getThanhToan() {
		return thanhToan;
	}

	public void setThanhToan(String thanhToan) {
		this.thanhToan = thanhToan;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public int getHuy() {
		return huy;
	}

	public void setHuy(int huy) {
		this.huy = huy;
	}
}
