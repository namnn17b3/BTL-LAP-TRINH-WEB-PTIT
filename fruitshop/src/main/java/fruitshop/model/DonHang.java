package fruitshop.model;

public class DonHang {
	private int id;
	private int idSanPham;
	private int idDanhSachDonHang;
	private int soLuong;
	private String tenSanPham;
	private String anh;
	private int donGia;
	private String donVi;
	private int danhGia = 0;
	
	public DonHang() {}

	public DonHang(int id, int idSanPham, int idDanhSachDonHang, int soLuong, String tenSanPham,
			String anh, int donGia, String donVi, int danhGia) {
		super();
		this.id = id;
		this.idSanPham = idSanPham;
		this.idDanhSachDonHang = idDanhSachDonHang;
		this.soLuong = soLuong;
		this.tenSanPham = tenSanPham;
		this.anh = anh;
		this.donGia = donGia;
		this.donVi = donVi;
		this.danhGia = danhGia;
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
	
	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public int getDonGia() {
		return donGia;
	}

	public void setDonGia(int donGia) {
		this.donGia = donGia;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public int getDanhGia() {
		return danhGia;
	}

	public void setDanhGia(int danhGia) {
		this.danhGia = danhGia;
	}
}
