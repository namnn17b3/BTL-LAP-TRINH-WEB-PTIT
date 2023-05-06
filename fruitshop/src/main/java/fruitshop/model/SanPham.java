package fruitshop.model;

public class SanPham {
	private int id;
	private String ten;
	private String donVi;
	private int tienTrenDonVi;
	private String nguonGoc;
	private int soLuongNhap;
	private int soLuongBan;
	private float soSaoVote;
	private String soLuongTrenDonVi;
	private String tomTat;
	private String vi;
	private String dinhDuong;
	private String baoQuan;
	private String anh;
	
	public SanPham() {}

	public SanPham(int id, String ten, String donVi, int tienTrenDonVi, String nguonGoc, int soLuongNhap, int soLuongBan,
			float soSaoVote, String soLuongTrenDonVi, String tomTat, String vi, String dinhDuong, String baoQuan, String anh) {
		this.id = id;
		this.ten = ten;
		this.donVi = donVi;
		this.tienTrenDonVi = tienTrenDonVi;
		this.nguonGoc = nguonGoc;
		this.soLuongNhap = soLuongNhap;
		this.soLuongBan = soLuongBan;
		this.soSaoVote = soSaoVote;
		this.soLuongTrenDonVi = soLuongTrenDonVi;
		this.tomTat = tomTat;
		this.vi = vi;
		this.dinhDuong = dinhDuong;
		this.baoQuan = baoQuan;
		this.anh = anh;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getDonVi() {
		return donVi;
	}

	public void setDonVi(String donVi) {
		this.donVi = donVi;
	}

	public int getTienTrenDonVi() {
		return tienTrenDonVi;
	}

	public void setTienTrenDonVi(int tienTrenDonVi) {
		this.tienTrenDonVi = tienTrenDonVi;
	}

	public String getNguonGoc() {
		return nguonGoc;
	}

	public void setNguonGoc(String nguonGoc) {
		this.nguonGoc = nguonGoc;
	}

	public int getSoLuongNhap() {
		return soLuongNhap;
	}

	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	
	public int getSoLuongBan() {
		return soLuongBan;
	}

	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

	public String getSoLuongTrenDonVi() {
		return soLuongTrenDonVi;
	}

	public void setSoLuongTrenDonVi(String soLuongTrenDonVi) {
		this.soLuongTrenDonVi = soLuongTrenDonVi;
	}

	public String getTomTat() {
		return tomTat;
	}

	public void setTomTat(String tomTat) {
		this.tomTat = tomTat;
	}

	public String getVi() {
		return vi;
	}

	public void setVi(String vi) {
		this.vi = vi;
	}

	public String getDinhDuong() {
		return dinhDuong;
	}

	public void setDinhDuong(String dinhDuong) {
		this.dinhDuong = dinhDuong;
	}

	public String getBaoQuan() {
		return baoQuan;
	}

	public void setBaoQuan(String baoQuan) {
		this.baoQuan = baoQuan;
	}

	public float getSoSaoVote() {
		return soSaoVote;
	}

	public void setSoSaoVote(float soSaoVote) {
		this.soSaoVote = soSaoVote;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}
}
