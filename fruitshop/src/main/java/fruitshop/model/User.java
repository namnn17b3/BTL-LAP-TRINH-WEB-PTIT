package fruitshop.model;

public class User {
	private int id;
	private String ten;
	private String email;
	private String matKhau;
	private String anh;
	private String vaiTro;
	
	public User() {}

	public User(int id, String ten, String email, String matKhau, String anh, String vaiTro) {
		this.id = id;
		this.ten = ten;
		this.email = email;
		this.matKhau = matKhau;
		this.anh = anh;
		this.vaiTro = vaiTro;
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
}
