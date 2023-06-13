<%@page import="fruitshop.model.DonHang"%>
<%@page import="java.util.HashMap"%>
<%@page import="fruitshop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	User currentUser = (User) session.getAttribute("currentUser");
%>
<div class="tieu-de-chung">
	<div class="top">
		<div class="khau-hieu-goi">
			<div class="khau-hieu">
				<b>Uy tín chất lượng được chúng tôi đặt lên hàng đầu</b>
			</div>
			<div class="goi">
				<i class="fa-solid fa-phone"></i> <b>097.796.3450</b>
			</div>
		</div>
	</div>
	<a class="tieu-de" href="./home"> <img
		src="./img/top_banner_desktop.png" alt="" class="tieu-de anh-tieu-de" />
	</a>
	<div class="thanh-lua-chon">
		<div class="thanh-lua-chon--middle">
			<div class="thanh-lua-chon-chinh">
				<a class="lua-chon-chinh trang-chu" href="./home">Trang chủ</a>
				<div class="lua-chon-chinh danh-muc-san-pham">
					Danh mục sản phẩm <i class="fa-sharp fa-solid fa-chevron-down"
						style="font-size: 14px;"></i>
					<div class="bang-danh-muc-con">
						<div class="danh-muc-con tat-ca">
							<a href="./danh-sach-san-pham?loai=tat-ca" style="text-decoration: none; color: #212529;">Tất
								cả</a>
						</div>
						<div class="danh-muc-con qua-tang-cao-cap">
							Quà tặng cao cấp <i class="fa-sharp fa-solid fa-chevron-right"
								style="font-size: 14px;"></i>
							<div class="bang-qua-tang-cao-cap">
								<div class="muc-con-qua-tang-cap-cap">
									<a href="./danh-sach-san-pham?loai=gio-qua-tang" style="text-decoration: none; color: #212529;">GIỎ
										QUÀ TẶNG TRÁI CÂY</a>
								</div>
								<div class="muc-con-qua-tang-cap-cap">
									<a href="./danh-sach-san-pham?loai=mua-nguyen-thung" style="text-decoration: none; color: #212529;">
										MUA NGUYÊN THÙNG
									</a>
								</div>
							</div>
						</div>
						<div class="danh-muc-con trai-cay-tuoi">
							Trái cây tươi <i class="fa-sharp fa-solid fa-chevron-right"
								style="font-size: 14px;"></i>
							<div class="bang-trai-cay-tuoi">
								<!-- <div class="muc-con-trai-cay-tuoi trai-cay-nam-phi">
									<a href="./danh-sach-san-pham?loai=trai-cay-nam-phi" style="text-decoration: none; color: #212529;">Trái
										cây Nam Phi</a>
								</div> -->
								<div class="muc-con-trai-cay-tuoi mua-le">
									<a href="./danh-sach-san-pham?loai=mua-le" style="text-decoration: none; color: #212529;">MUA
										LẺ</a>
								</div>
								<div class="muc-con-trai-cay-tuoi combo">
									<a href="./danh-sach-san-pham?loai=combo" style="text-decoration: none; color: #212529;">COMBO</a>
								</div>
								<div class="muc-con-trai-cay-tuoi trai-cay-nam-phi">
									<a href="./danh-sach-san-pham?loai=trai-cay-nam-phi" style="text-decoration: none; color: #212529;">Trái
										cây Nam Phi</a>
								</div>
								<div class="muc-con-trai-cay-tuoi trai-cay-han-quoc">
									<a href="./danh-sach-san-pham?loai=trai-cay-han-quoc" style="text-decoration: none; color: #212529;">Trái
										cây Hàn Quốc</a>
								</div>
								<div class="muc-con-trai-cay-tuoi trai-cay-uc">
									<a href="./danh-sach-san-pham?loai=trai-cay-uc" style="text-decoration: none; color: #212529;">Trái
										cây Úc</a>
								</div>
								<div class="muc-con-trai-cay-tuoi trai-cay-new-zealand">
									<a href="./danh-sach-san-pham?loai=trai-cay-newzealand" style="text-decoration: none; color: #212529;">Trái
										cây New Zealand</a>
								</div>
								<div class="muc-con-trai-cay-tuoi trai-cay-my">
									<a href="./danh-sach-san-pham?loai=trai-cay-my" style="text-decoration: none; color: #212529;">Trái
										cây Mỹ</a>
								</div>
								<div class="muc-con-trai-cay-tuoi trai-cay-chile">
									<a href="./danh-sach-san-pham?loai=trai-cay-chile" style="text-decoration: none; color: #212529;">Trái
										cây Chile</a>
								</div>
								<div class="muc-con-trai-cay-tuoi trai-cay-cac-nuoc-khac">
									<a href="./danh-sach-san-pham?loai=trai-cay-nuoc-khac" style="text-decoration: none; color: #212529;">Trái
										cây các nước khác</a>
								</div>
							</div>
						</div>
						<div class="danh-muc-con do-kho">
							<a href="./danh-sach-san-pham?loai=do-kho" style="text-decoration: none; color: #212529;">Đồ khô</a>
						</div>
					</div>
				</div>
				<a href="./about-me" class="lua-chon-chinh gioi-thieu">Giới thiệu</a>
				<a href="./shop-detail" class="lua-chon-chinh cua-hang">Cửa hàng</a>
				<a href="./contact-us" class="lua-chon-chinh lien-he">Liên hệ</a>
			</div>
			<div class="thanh-lua-chon-khac">
				<c:if test="<%=currentUser == null%>">				
					<div class="lua-chon-khac dang-nhap">
						<div style="width: 48px;">
							<div class="dang-nhap-khong-anh"></div>
						</div>
						<div class="bang-dang-nhap">
							<div class="dn">
								<a href="./login" style="text-decoration: none; color: #212529; width: 80%;">Đăng nhập</a>
								<i class="fa-solid fa-right-to-bracket" style="color: #3D464D; font-size: 20px; paddng-top: 0;"></i>
							</div>
						</div>
					</div>
				</c:if>
				
				<c:if test="<%=currentUser != null%>">				
					<div class="lua-chon-khac chuc-nang-cua-user">
						<div style="width: 48px; display: flex;">
							<img class="anh-dai-dien-cua-user" src="${currentUser.anh}"/>
						</div>
						<div class="bang-chuc-nang-cua-user">
							<div class="thanh-phan-bang-chuc-nang-user ho-so-user">
								<a href="./profile" style="text-decoration: none; color: #212529; width: 50%; margin: auto 0px auto auto">Hồ sơ</a>
								<i class="fa-solid fa-user" style="color: #3D464D; font-size: 20px; paddng-top: 0; margin: auto"></i>
							</div>
							<div class="thanh-phan-bang-chuc-nang-user don-hang-user">
								<a href="./don-hang" style="text-decoration: none; color: #212529; width: 50%; margin: auto 0px auto auto">Đơn hàng</a>
								<i class="fa-solid fa-list" style="color: #3D464D; font-size: 20px; paddng-top: 0; margin: auto"></i>
							</div>
							<div class="thanh-phan-bang-chuc-nang-user logout">
								<a href="./logout" style="text-decoration: none; color: #212529; width: 50%; margin: auto 0px auto auto">Đăng xuất</a>
								<i class="fa-solid fa-right-from-bracket" style="color: #3D464D; font-size: 20px; paddng-top: 0; margin: auto"></i>
							</div>
						</div>
					</div>
				</c:if>
				
				<div class="lua-chon-khac tim-kiem">
					<div class="tim-kiem-hieu-ung-mo"></div>
					<div class="tim-kiem-icon">
						<i class="fa-solid fa-magnifying-glass"></i>
					</div>
					<div class="bo-tim-kiem-icon">
						<i class="fa-solid fa-xmark"></i>
					</div>
				</div>

				<%
					String soLuongSanPhamTrongGioHangString = "0";
					int soLuongSanPhamTrongGioHang = 0;
					if (session.getAttribute("soLuongSanPhamTrongGioHang") != null) {						
						soLuongSanPhamTrongGioHang = (int) session.getAttribute("soLuongSanPhamTrongGioHang");
						soLuongSanPhamTrongGioHangString = soLuongSanPhamTrongGioHang > 99 ? "99+" : String.valueOf(soLuongSanPhamTrongGioHang);
					}
				%>
				<div class="lua-chon-khac gio-hang">
					<a href="./gio-hang"></a>
					<div class="vong-tron-so-luong" title="<%=soLuongSanPhamTrongGioHang%>"><%=soLuongSanPhamTrongGioHangString%></div>
					<div class="gio-hang-hieu-ung-mo"></div>
					<div class="gio-hang-icon">
						<i class="fa-solid fa-cart-shopping"></i>
					</div>
				</div>
				<form action="./search" method="post" class="nhap-ten-san-pham">
					<input type="text" placeholder="Tên sản phẩm..." class="o-nhap-ten-san-pham" name="ten-san-pham" value='<c:out value="${tenSanPhamTimKiem}"/>'>
					<div id="tim-kiem-icon">
						<i class="fa-solid fa-magnifying-glass" style="margin: auto;"></i>
						<button style="width: 100%; height: 100%; position: absolute; top: 0; left: 0; bottom: 0; right: 0; opacity: 0; cursor: pointer">1</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<script src="./js/utils.js"></script>
<script type="text/javascript">
	[...document.querySelectorAll('.muc-con-qua-tang-cap-cap')]
	.concat([...document.querySelectorAll('.muc-con-trai-cay-tuoi')])
	.concat([...document.querySelectorAll('.tat-ca')])
	.concat([...document.querySelectorAll('.do-kho')])
	.forEach(item => {
		item.onclick = () => {
			item.firstElementChild.click();
		}
	});
	
	var gioHang = document.querySelector('.gio-hang');
	gioHang.onclick = () => {
		themCookie('clickGioHang', 1, 3600000 * 24, 60, '/fruitshop/gio-hang');
		gioHang.firstElementChild.click();
	}
</script>