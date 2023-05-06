<%@page import="fruitshop.model.DonHang"%>
<%@page import="java.util.Arrays"%>
<%@page import="fruitshop.model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chi tiết sản phẩm | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/chi_tiet_san_pham.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<jsp:include page="header.jsp"/>
    <!-- than website -->
    <div class="than-website">
		<c:if test="${loadPage == 1}">	
			<jsp:include page="load_page.jsp"/>
		</c:if>
		<%
			SanPham sanPham = (SanPham) request.getAttribute("sanPham");
			String tinhTrang = (sanPham.getSoLuongNhap() > sanPham.getSoLuongBan()) ? "Còn hàng" : "Hết hàng";
			
			int soLuongDanhGia = (Integer) request.getAttribute("soLuongDanhGia");
			int soLuongNamSao = (Integer) request.getAttribute("soLuongNamSao");
			int soLuongBonSao = (Integer) request.getAttribute("soLuongBonSao");
			int soLuongBaSao = (Integer) request.getAttribute("soLuongBaSao");
			int soLuongHaiSao = (Integer) request.getAttribute("soLuongHaiSao");
			int soLuongMotSao = (Integer) request.getAttribute("soLuongMotSao");
			int soLuongKhongSao = (Integer) request.getAttribute("soLuongKhongSao");
			
			float phanTramNamSao = (float)soLuongNamSao / soLuongDanhGia * 100;
			float phanTramBonSao = (float)soLuongBonSao / soLuongDanhGia * 100;
			float phanTramBaSao = (float)soLuongBaSao / soLuongDanhGia * 100;
			float phanTramHaiSao = (float)soLuongHaiSao / soLuongDanhGia * 100;
			float phanTramMotSao = (float)soLuongMotSao / soLuongDanhGia * 100;
			float phanTramKhongSao = (float)soLuongKhongSao / soLuongDanhGia * 100;
			
			List<DonHang> currentListDanhGia = (List<DonHang>) request.getAttribute("currentListDanhGia");
			
			int trang = (Integer) request.getAttribute("page");
		%>
    	<div class="phan-duoc-hien-thi">
    		<div class="phan-1">
			    <div class="thong-tin-chung-cua-san-pham anh-san-pham"">
			    	<img alt="" src="<%=sanPham.getAnh()%>" style="width: 100%; height: 100%">
			    </div>
			    <div class="thong-tin-chung-cua-san-pham">
				    <h1 class="ten-san-pham"><%=sanPham.getTen()%></h1>
				    <div class="so-sao-va-luot-ban"><%=sanPham.getSoSaoVote()%><i class="fa-solid fa-star" id="sao"></i>(<%=soLuongDanhGia%> đánh giá) | Đã bán <%=sanPham.getSoLuongBan() %></div>
				    <div class="mo-ta-ngan-gon-san-pham">
				    	<div class="thong-tin-ngan-gon" style="flex: 2">Đơn vị:<p style="color: #28a745; display: inline;"><%=sanPham.getDonVi()%></p></div>
				    	<div class="thong-tin-ngan-gon" style="flex: 3">Nguồn gốc:<p style="color: #28a745; display: inline;"><%=sanPham.getNguonGoc()%></p></div>
				    	<div class="thong-tin-ngan-gon" style="flex: 3">Tình trạng:<p style="color: #28a745; display: inline;"><%=tinhTrang%></p></div>
				    </div>
				    <h3 id="gia"><%=sanPham.getTienTrenDonVi()%> VNĐ<p style="display: inline; color: #3d464d">/<%=sanPham.getDonVi()%></p></h3>
				    <div class="so-luong-tieu-de">Số lượng</div>
				    <div class="thao-tac-voi-san-pham">
				    	<div id="tang-giam-so-luong">
				    		<div class="nut-tang-giam-so-luong tru"><p style="margin: auto">-</p></div>
				    		<div id="so-luong"><p style="margin: auto">1</p></div>
				    		<div class="nut-tang-giam-so-luong cong"><p style="margin: auto">+</p></div>
				    	</div>
				    	<form action="./them-vao-gio-hang?id=<%=sanPham.getId()%>" method="post" style="display: flex; margin-left: auto; margin-right: auto;">
					    	<input type="hidden" name="so-luong" value="1">
					    	<button class="them-vao-gio-hang"><p style="margin: auto">Thêm vào giỏ</p></button>
					    </form>
				    	<a id="mua-ngay" href="#"><p style="margin: auto;">Mua ngay</p></a>
				    </div>
			    </div>
    		</div>
    		<div class="phan-2">
    			<div class="thanh-lua-chon-item">
    				<div class="item item-mo-ta"><p style="margin: auto; font-size: 20px; font-weight: 600">Mô tả</p></div>
    				<div class="item item-danh-gia"><p style="margin: auto; font-size: 20px; font-weight: 600">Đánh giá</p></div>
    				<div class="thanh-gach-chan-item"></div>
    			</div>
    			<div class="mo-ta-chi-tiet-san-pham">
    				<div class="boc-ben-ngoai-mo-ta-chi-tiet-san-pham">
	    				<h3 class="nguon-goc">Nguồn gốc</h3>
	    				<p class="thong-tin-mo-ta-thuoc-tinh-san-pham"><%=sanPham.getNguonGoc()%></p>
	    				
	    				<h3 class="tieu-de-thuoc-tinh-mo-ta-chi-tiet-san-pham">Số lượng trên đơn vị</h3>
	   					<p class="thong-tin-mo-ta-thuoc-tinh-san-pham"><%=sanPham.getSoLuongTrenDonVi()%></p>
	    				
	    				<h3 class="tieu-de-thuoc-tinh-mo-ta-chi-tiet-san-pham">Tóm tắt</h3>
	   					<%List<String> tomTat = Arrays.asList(sanPham.getTomTat().split("\\$")); %>
	   					<c:forEach items="<%=tomTat%>" var="item">
	   						<p class="thong-tin-mo-ta-thuoc-tinh-san-pham">${item}</p>
	   					</c:forEach>
	    				
	    				<h3 class="tieu-de-thuoc-tinh-mo-ta-chi-tiet-san-pham">Vị</h3>
	   					<%List<String> vi = Arrays.asList(sanPham.getVi().split("\\$")); %>
	   					<c:forEach items="<%=vi%>" var="item">
	   						<p class="thong-tin-mo-ta-thuoc-tinh-san-pham">${item}</p>
	   					</c:forEach>
	    				<h3 class="tieu-de-thuoc-tinh-mo-ta-chi-tiet-san-pham">Dinh dưỡng</h3>
	   					<%List<String> dinhDuong = Arrays.asList(sanPham.getDinhDuong().split("\\$")); %>
	   					<c:forEach items="<%=dinhDuong%>" var="item">
	   						<p class="thong-tin-mo-ta-thuoc-tinh-san-pham">${item}</p>
	   					</c:forEach>
	    				
	    				<h3 class="tieu-de-thuoc-tinh-mo-ta-chi-tiet-san-pham">Bảo quản</h3>
	   					<%List<String> baoQuan = Arrays.asList(sanPham.getBaoQuan().split("\\$")); %>
	   					<c:forEach items="<%=baoQuan%>" var="item">
	   						<p class="thong-tin-mo-ta-thuoc-tinh-san-pham">${item}</p>
	   					</c:forEach>
	   				</div>
    			</div>
    			<div class="danh-gia-san-pham">
    				<div class="boc-ben-ngoai-danh-gia-san-pham">
	    				<h3 id="danh-gia-nhan-xet-title">Đánh Giá - Nhận Xét Từ Khách Hàng</h3>
	    				<c:choose>
		    				<c:when test="<%=soLuongDanhGia > 0%>">
		    					<div class="thong-ke-danh-gia-va-cac-danh-gia">
			    					<div class="thong-ke-danh-gia">
			    						<div class="thong-ke-sao-co-ban">
				    						<h1 class="trung-binh-so-sao"><%=sanPham.getSoSaoVote()%></h1>
				    						<div class="sao-icon-va-tong-so-luong-danh-gia">
				    							<i class="fa-solid fa-star" id="sao-icon"></i>
				    							<p id="tong-so-luong-danh-gia"><%=soLuongDanhGia%> đánh giá</p>
				    						</div>
			    						</div>
				    					<div class="thong-ke-chi-tiet">
				    						<div class="dong-sao-va-thanh-sao">
				    							<div class="dong-sao">
					    							<c:forEach begin="1" end="5">
					    								<i class="fa-solid fa-star sao-vang"></i>
					    							</c:forEach>
				    							</div>
				    							<div class="thanh-sao"><div class="thanh-sao-dam" style="width: <%=phanTramNamSao%>%"></div></div>
				    							<p style="color: #808089; margin-top: auto; margin-bottom: auto;"><%=soLuongNamSao%></p>
				    						</div>
				    						<div class="dong-sao-va-thanh-sao">
				    							<div class="dong-sao">
					    							<c:forEach begin="1" end="4">
					    								<i class="fa-solid fa-star sao-vang"></i>
					    							</c:forEach>
					    							<c:forEach begin="5" end="5">
					    								<i class="fa-solid fa-star sao-xam"></i>
					    							</c:forEach>
				    							</div>
				    							<div class="thanh-sao"><div class="thanh-sao-dam" style="width: <%=phanTramBonSao%>%"></div></div>
				    							<p style="color: #808089; margin-top: auto; margin-bottom: auto;"><%=soLuongBonSao%></p>
				    						</div>
				    						<div class="dong-sao-va-thanh-sao">
				    							<div class="dong-sao">
					    							<c:forEach begin="1" end="3">
					    								<i class="fa-solid fa-star sao-vang"></i>
					    							</c:forEach>
					    							<c:forEach begin="4" end="5">
					    								<i class="fa-solid fa-star sao-xam"></i>
					    							</c:forEach>
				    							</div>
				    							<div class="thanh-sao"><div class="thanh-sao-dam" style="width: <%=phanTramBaSao%>%"></div></div>
				    							<p style="color: #808089; margin-top: auto; margin-bottom: auto;"><%=soLuongBaSao%></p>
				    						</div>
				    						<div class="dong-sao-va-thanh-sao">
				    							<div class="dong-sao">				    							
					    							<c:forEach begin="1" end="2">
					    								<i class="fa-solid fa-star sao-vang"></i>
					    							</c:forEach>
					    							<c:forEach begin="3" end="5">
					    								<i class="fa-solid fa-star sao-xam"></i>
					    							</c:forEach>
				    							</div>
				    							<div class="thanh-sao"><div class="thanh-sao-dam" style="width: <%=phanTramHaiSao%>%"></div></div>
				    							<p style="color: #808089; margin-top: auto; margin-bottom: auto;"><%=soLuongHaiSao%></p>
				    						</div>
				    						<div class="dong-sao-va-thanh-sao">
				    							<div class="dong-sao">
					    							<c:forEach begin="1" end="1">
					    								<i class="fa-solid fa-star sao-vang"></i>
					    							</c:forEach>
					    							<c:forEach begin="2" end="5">
					    								<i class="fa-solid fa-star sao-xam"></i>
					    							</c:forEach>
				    							</div>
				    							<div class="thanh-sao"><div class="thanh-sao-dam" style="width: <%=phanTramMotSao%>%"></div></div>
				    							<p style="color: #808089; margin-top: auto; margin-bottom: auto;"><%=soLuongMotSao%></p>
				    						</div>
				    						<div class="dong-sao-va-thanh-sao">
				    							<div class="dong-sao">				    							
					    							<c:forEach begin="1" end="5">
					    								<i class="fa-solid fa-star sao-xam"></i>
					    							</c:forEach>
				    							</div>
				    							<div class="thanh-sao"><div class="thanh-sao-dam" style="width: <%=phanTramKhongSao%>%"></div></div>
				    							<p style="color: #808089; margin-top: auto; margin-bottom: auto;"><%=soLuongKhongSao%></p>
				    						</div>
				    					</div>
			    					</div>
			    					<div class="cac-danh-gia">
			    						<div class="phan-loai-danh-gia-theo-sao">
			    							<div class="label-phan-loai-danh-gia">Phân loại theo:</div>
			    							<div class="nut-phan-loai">
			    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=-1&page=1"></a>
			    								Tất cả
			    							</div>
			    							<div class="nut-phan-loai">
			    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=5&page=1"></a>
			    								5 <i class="fa-solid fa-star"></i>
			    							</div>
			    							<div class="nut-phan-loai">
			    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=4&page=1"></a>
			    								4 <i class="fa-solid fa-star"></i>
			    							</div>
			    							<div class="nut-phan-loai">
			    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=3&page=1"></a>
			    								3 <i class="fa-solid fa-star"></i>
			    							</div>
			    							<div class="nut-phan-loai">
			    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=2&page=1"></a>
			    								2 <i class="fa-solid fa-star"></i>
			    							</div>
			    							<div class="nut-phan-loai">
			    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=1&page=1"></a>
			    								1 <i class="fa-solid fa-star"></i>
			    							</div>
			    							<div class="nut-phan-loai">
			    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=0&page=1"></a>
			    								0 <i class="fa-solid fa-star"></i>
			    							</div>
			    						</div>
			    						<div class="danh-sach-danh-gia-trong-trang">
			    							<c:forEach items="<%=currentListDanhGia%>" var="item">
			    								<div class="chi-tiet-danh-gia-cua-nguoi-dung">
					    							<div class="anh-nguoi-danh-gia_ten-nguoi-danh-gia_so-sao-danh-gia_ngay-danh-gia">
					    								<img alt="" src="${item.anhUser}" class="anh-nguoi-danh-gia">
					    								<div style="margin-left: 10px;margin-bottom: 10px">
					    									<h4 class="ten-nguoi-danh-gia">${item.tenUser}</h4>
					    									<c:forEach begin="1" end="${item.soSaoVote}">
					    										<i class="fa-solid fa-star" style="color: #fdd836; font-size: 11px"></i>
					    									</c:forEach>
					    									<c:forEach begin="1" end="${5-item.soSaoVote}">
					    										<i class="fa-solid fa-star" style="color: #dddde3; font-size: 11px"></i>
					    									</c:forEach>
					    									<div class="thoi-gian-danh-gia">
					    										<fmt:formatDate value="${item.ngayBinhLuan}" pattern="dd/MM/yyyy HH:mm:ss"/>
					    									</div>
					    								</div>
					    							</div>
					    							<div class="noi-dung-danh-gia">${item.noiDungBinhLuan}</div>
			    								</div>   							
			    							</c:forEach>
			    						</div>
			    					</div>
		    					</div>
		    				</c:when>
		    				
		    				<c:otherwise>
		    					<jsp:include page="khong_co_danh_gia.jsp"/>
		    				</c:otherwise>
	    				</c:choose>
	    			</div>
    			</div>
    		</div>
		</div>
    </div>
	<jsp:include page="footer.jsp"/>
    <script src="./js/common.js"></script>
    <script src="./js/chi_tiet_san_pham.js"></script>
</body>
</html>