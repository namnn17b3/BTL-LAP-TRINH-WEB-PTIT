package fruitshop.model;

import java.text.ParseException;
import java.util.Date;

public class DonHang {
	private int id;
	private int idSanPham;
	private int idUser;
	private String tenNguoiNhan;
	private int soLuong;
	private String diaChiNguoiNhan;
	private String soDienThoaiNguoiNhan;
	private String trangThai;
	private String ghiChu;
	private Date ngayXuat;
	private String noiDungBinhLuan;
	private Date ngayBinhLuan;
	private int soSaoVote;
	private String tenUser;
	private String anhUser;
	
	public DonHang() {}

	public DonHang(int id, int idSanPham, int idUser, String tenNguoiNhan, int soLuong, String diaChiNguoiNhan,
			String soDienThoaiNguoiNhan, String trangThai, String ghiChu, Date ngayXuat, String noiDungBinhLuan,
			Date ngayBinhLuan, int soSaoVote, String tenUser, String anhUser) {
		this.id = id;
		this.idSanPham = idSanPham;
		this.idUser = idUser;
		this.tenNguoiNhan = tenNguoiNhan;
		this.soLuong = soLuong;
		this.diaChiNguoiNhan = diaChiNguoiNhan;
		this.soDienThoaiNguoiNhan = soDienThoaiNguoiNhan;
		this.trangThai = trangThai;
		this.ghiChu = ghiChu;
		this.ngayXuat = ngayXuat;
		this.noiDungBinhLuan = noiDungBinhLuan;
		this.ngayBinhLuan = ngayBinhLuan;
		this.soSaoVote = soSaoVote;
		this.tenUser = tenUser;
		this.anhUser = anhUser;
	}

	public String getTenUser() {
		return tenUser;
	}

	public void setTenUser(String tenUser) {
		this.tenUser = tenUser;
	}

	public String getAnhUser() {
		return anhUser;
	}

	public void setAnhUser(String anhUser) {
		this.anhUser = anhUser;
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

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getTenNguoiNhan() {
		return tenNguoiNhan;
	}

	public void setTenNguoiNhan(String tenNguoiNhan) {
		this.tenNguoiNhan = tenNguoiNhan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
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

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Date getNgayXuat() {
		return ngayXuat;
	}

	public void setNgayXuat(Date ngayXuat) {
		this.ngayXuat = ngayXuat;
	}

	public String getNoiDungBinhLuan() {
		return noiDungBinhLuan;
	}

	public void setNoiDungBinhLuan(String noiDungBinhLuan) {
		this.noiDungBinhLuan = noiDungBinhLuan;
	}

	public Date getNgayBinhLuan() throws ParseException {
		return ngayBinhLuan;
	}

	public void setNgayBinhLuan(Date ngayBinhLuan) {
		this.ngayBinhLuan = ngayBinhLuan;
	}

	public int getSoSaoVote() {
		return soSaoVote;
	}

	public void setSoSaoVote(int soSaoVote) {
		this.soSaoVote = soSaoVote;
	}
}
