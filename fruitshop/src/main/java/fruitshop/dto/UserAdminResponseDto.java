package fruitshop.dto;

import java.util.List;

import fruitshop.model.User;

public class UserAdminResponseDto {
	private List<User> listUser;
	private int soLuongBanGhi;
	private int soLuongPage;
	private int startPageWebview;
	private int endPageWebview;
	
	public UserAdminResponseDto(List<User> listUser, int soLuongBanGhi, int soLuongPage, int startPageWebview,
			int endPageWebview) {
		super();
		this.listUser = listUser;
		this.soLuongBanGhi = soLuongBanGhi;
		this.soLuongPage = soLuongPage;
		this.startPageWebview = startPageWebview;
		this.endPageWebview = endPageWebview;
	}
	
	public UserAdminResponseDto() {}

	public List<User> getListUser() {
		return listUser;
	}

	public void setListUser(List<User> listUser) {
		this.listUser = listUser;
	}

	public int getSoLuongBanGhi() {
		return soLuongBanGhi;
	}

	public void setSoLuongBanGhi(int soLuongBanGhi) {
		this.soLuongBanGhi = soLuongBanGhi;
	}

	public int getSoLuongPage() {
		return soLuongPage;
	}

	public void setSoLuongPage(int soLuongPage) {
		this.soLuongPage = soLuongPage;
	}

	public int getStartPageWebview() {
		return startPageWebview;
	}

	public void setStartPageWebview(int startPageWebview) {
		this.startPageWebview = startPageWebview;
	}

	public int getEndPageWebview() {
		return endPageWebview;
	}

	public void setEndPageWebview(int endPageWebview) {
		this.endPageWebview = endPageWebview;
	}

	@Override
	public String toString() {
		return "UserAdminResponseDto {listUser=" + listUser + ", soLuongBanGhi=" + soLuongBanGhi + ", soLuongPage="
				+ soLuongPage + ", startPageWebview=" + startPageWebview + ", endPageWebview=" + endPageWebview + "}";
	}
}
