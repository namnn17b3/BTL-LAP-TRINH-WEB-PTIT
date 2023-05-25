package fruitshop.model;

import java.util.Date;

public class DanhGia {
	private int id;
	private int idUser;
	private int idSanPham;
	private int soSaoVote;
	private Date ngayBinhLuan;
	private String noiDungBinhLuan;
	private String tenUser;
	private String anhUser;
	
	public DanhGia() {}
	
	

	public DanhGia(int id, int idUser, int idSanPham, int soSaoVote, Date ngayBinhLuan, String noiDungBinhLuan,
			String tenUser, String anhUser) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.idSanPham = idSanPham;
		this.soSaoVote = soSaoVote;
		this.ngayBinhLuan = ngayBinhLuan;
		this.noiDungBinhLuan = noiDungBinhLuan;
		this.tenUser = tenUser;
		this.anhUser = anhUser;
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

	public int getSoSaoVote() {
		return soSaoVote;
	}

	public void setSoSaoVote(int soSaoVote) {
		this.soSaoVote = soSaoVote;
	}

	public Date getNgayBinhLuan() {
		return ngayBinhLuan;
	}

	public void setNgayBinhLuan(Date ngayBinhLuan) {
		this.ngayBinhLuan = ngayBinhLuan;
	}

	public String getNoiDungBinhLuan() {
		return noiDungBinhLuan;
	}

	public void setNoiDungBinhLuan(String noiDungBinhLuan) {
		this.noiDungBinhLuan = noiDungBinhLuan;
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
}
