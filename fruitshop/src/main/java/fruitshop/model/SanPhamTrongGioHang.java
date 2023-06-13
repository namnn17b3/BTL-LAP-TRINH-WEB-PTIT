package fruitshop.model;

import java.util.Date;

public class SanPhamTrongGioHang {
	private int id;
	private int idUser;
	private int idSanPham;
	private Date ngayThem;
	private String tenSanPham;
	private int tienTrenDonVi;
	private int soLuong;
	private int soLuongSanPhamConLai;
	private String anh;
	private String donVi;
	
	public SanPhamTrongGioHang() {}

	public SanPhamTrongGioHang(int id, int idUser, int idSanPham, Date ngayThem, String tenSanPham,
			int tienTrenDonVi, int soLuong, int soLuongSanPhamConLai, String anh, String donVi) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idSanPham = idSanPham;
		this.ngayThem = ngayThem;
		this.tenSanPham = tenSanPham;
		this.tienTrenDonVi = tienTrenDonVi;
		this.soLuong = soLuong;
		this.soLuongSanPhamConLai = soLuongSanPhamConLai;
		this.anh = anh;
		this.donVi = donVi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getIdSanPham() {
		return idSanPham;
	}

	public void setIdSanPham(int idSanPham) {
		this.idSanPham = idSanPham;
	}

	public Date getNgayThem() {
		return ngayThem;
	}

	public void setNgayThem(Date ngayThem) {
		this.ngayThem = ngayThem;
	}

	public String getTenSanPham() {
		return tenSanPham;
	}

	public void setTenSanPham(String tenSanPham) {
		this.tenSanPham = tenSanPham;
	}

	public int getTienTrenDonVi() {
		return tienTrenDonVi;
	}

	public void setTienTrenDonVi(int tienTrenDonVi) {
		this.tienTrenDonVi = tienTrenDonVi;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public int getSoLuongSanPhamConLai() {
		return soLuongSanPhamConLai;
	}

	public void setSoLuongSanPhamConLai(int soLuongSanPhamConLai) {
		this.soLuongSanPhamConLai = soLuongSanPhamConLai;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}
}
