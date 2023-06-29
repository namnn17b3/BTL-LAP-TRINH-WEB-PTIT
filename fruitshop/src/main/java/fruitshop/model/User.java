package fruitshop.model;

public class User {
	private int id;
	private String ten;
	private String email;
	private String matKhau;
	private String anh;
	private String vaiTro;
	private int trangThai;
	private int soLuongDaMua;
	private long tongChiTieu;
	
	public User() {}

	public User(int id, String ten, String email, String matKhau, String anh, String vaiTro, int trangThai,
			int soLuongDaMua, long tongChiTieu) {
		super();
		this.id = id;
		this.ten = ten;
		this.email = email;
		this.matKhau = matKhau;
		this.anh = anh;
		this.vaiTro = vaiTro;
		this.trangThai = trangThai;
		this.soLuongDaMua = soLuongDaMua;
		this.tongChiTieu = tongChiTieu;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getAnh() {
		return anh;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public int getSoLuongDaMua() {
		return soLuongDaMua;
	}

	public void setSoLuongDaMua(int soLuongDaMua) {
		this.soLuongDaMua = soLuongDaMua;
	}

	public long getTongChiTieu() {
		return tongChiTieu;
	}

	public void setTongChiTieu(long tongChiTieu) {
		this.tongChiTieu = tongChiTieu;
	}
}
