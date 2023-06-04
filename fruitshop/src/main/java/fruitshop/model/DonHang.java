package fruitshop.model;

public class DonHang {
	private int id;
	private int idSanPham;
	private int idDanhSachDonHang;
	private int soLuong;
	private int tongTien;
	
	public DonHang() {}

	public DonHang(int id, int idSanPham, int idDanhSachDonHang, int soLuong, int tongTien) {
		super();
		this.id = id;
		this.idSanPham = idSanPham;
		this.idDanhSachDonHang = idDanhSachDonHang;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdSanPham() {
		return idSanPham;
	}

	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}

	public int getIdDanhSachDonHang() {
		return idDanhSachDonHang;
	}

	public void setIdDanhSachDonHang(int idDanhSachDonHang) {
		this.idDanhSachDonHang = idDanhSachDonHang;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getTongTien() {
		return tongTien;
	}

	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
}
