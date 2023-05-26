<%@page import="fruitshop.model.DonHang"%>
<%@page import="java.util.Arrays"%>
<%@page import="fruitshop.model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link rel="stylesheet" href="./css/phan_trang.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

	<style type="text/css">
 		.than-website {
 			display: none;
 		}
	</style>

</head>
<body>
	<jsp:include page="header.jsp"/>
	<jsp:include page="load_page.jsp"/>
	
	<c:if test="${themGioHangStatus == 1 || themGioHangStatus == 2 || themGioHangStatus == 3}">	
	   	<jsp:include page="thong_bao_mini.jsp"/>
	</c:if>

    <!-- than website -->
    <div class="than-website">
		<%
			SanPham sanPham = (SanPham) request.getAttribute("sanPham");
			String tinhTrang = (sanPham.getSoLuongNhap() > sanPham.getSoLuongBan()) ? "Còn hàng" : "Hết hàng";
			
			int soLuongTatCa = (Integer) request.getAttribute("soLuongTatCa");
			int soLuongNamSao = (Integer) request.getAttribute("soLuongNamSao");
			int soLuongBonSao = (Integer) request.getAttribute("soLuongBonSao");
			int soLuongBaSao = (Integer) request.getAttribute("soLuongBaSao");
			int soLuongHaiSao = (Integer) request.getAttribute("soLuongHaiSao");
			int soLuongMotSao = (Integer) request.getAttribute("soLuongMotSao");
			int soLuongKhongSao = (Integer) request.getAttribute("soLuongKhongSao");
			int soLuongDanhGiaTheoPhanLoai = (Integer) request.getAttribute("soLuongDanhGiaTheoPhanLoai");
			
			float phanTramNamSao = (float)soLuongNamSao / soLuongTatCa * 100;
			float phanTramBonSao = (float)soLuongBonSao / soLuongTatCa * 100;
			float phanTramBaSao = (float)soLuongBaSao / soLuongTatCa * 100;
			float phanTramHaiSao = (float)soLuongHaiSao / soLuongTatCa * 100;
			float phanTramMotSao = (float)soLuongMotSao / soLuongTatCa * 100;
			float phanTramKhongSao = (float)soLuongKhongSao / soLuongTatCa * 100;
			
			List<DonHang> currentListDanhGia = (List<DonHang>) request.getAttribute("currentListDanhGia");
			
			int choose = (Integer) request.getAttribute("choose");
			int trang = (Integer) request.getAttribute("page");
			int soLuongPage = (soLuongDanhGiaTheoPhanLoai % 5 != 0) ? soLuongDanhGiaTheoPhanLoai / 5 + 1 : soLuongDanhGiaTheoPhanLoai / 5;
			
			int pageBegin = trang - (trang - 1) % 5;
			int pageEnd = (pageBegin + 4 > soLuongPage) ? soLuongPage : (pageBegin + 4);
			int prePage = trang - 1;
			int nextPage = trang + 1;
			String currentPhanLoai = (choose == -1) ? "phan-loai-tat-ca" : ("phan-loai-"+choose+"-sao");
		%>
		
		<style>
			#<%=currentPhanLoai%> {
				background-color: #f0f8ff;
				border: 1px solid #1a94ff;
				color: #0b74e5;
			}
			
			.sao-phan-loai {
				color: #fdd836;
			}
		</style>
		
   		<div class="phan-1">
		    <div class="thong-tin-chung-cua-san-pham anh-san-pham">
		    	<img alt="" src="<%=sanPham.getAnh()%>" style="width: 100%; height: 100%">
		    </div>
		    <div class="thong-tin-chung-cua-san-pham">
			    <h1 class="ten-san-pham"><%=sanPham.getTen()%></h1>
			    <c:if test="<%=soLuongTatCa > 0%>">
				    <div class="so-sao-va-luot-ban"><%=sanPham.getSoSaoVote()%><i class="fa-solid fa-star" id="sao"></i>(<%=soLuongTatCa%> đánh giá) | Đã bán <%=sanPham.getSoLuongBan() %></div>
			    </c:if>
			    <c:if test="<%=soLuongTatCa == 0%>">
				    <div class="so-sao-va-luot-ban">Chưa có đánh giá | Đã bán <%=sanPham.getSoLuongBan() %></div>
			    </c:if>
			    <div class="mo-ta-ngan-gon-san-pham">
			    	<div class="thong-tin-ngan-gon" style="flex: 2">Đơn vị:<p style="color: #28a745; display: inline;"><%=sanPham.getDonVi()%></p></div>
			    	<div class="thong-tin-ngan-gon" style="flex: 3">Nguồn gốc:<p style="color: #28a745; display: inline;"><%=sanPham.getNguonGoc()%></p></div>
			    	<div class="thong-tin-ngan-gon" style="flex: 3">Tình trạng:<p style="color: #28a745; display: inline;"><%=tinhTrang%></p></div>
			    </div>
			    <h3 id="gia"><%=sanPham.getTienTrenDonVi()%> VNĐ<p style="display: inline; color: #3d464d">/<%=sanPham.getDonVi()%></p></h3>
			    <div class="so-luong-tieu-de">Số lượng</div>
			    <div class="thao-tac-voi-san-pham">
			    	<div id="tang-giam-so-luong">
			    		<div class="so-luong-san-pham-con-lai" style="display: none;"><%=sanPham.getSoLuongNhap() - sanPham.getSoLuongBan()%></div>
			    		<div class="so-luong" style="display: none;">1</div>
			    		<div class="nut-tang-giam-so-luong tru"><p style="margin: auto">-</p></div>
			    		<div id="so-luong-hien-thi" title="1"><p style="margin: auto">1</p></div>
			    		<div class="nut-tang-giam-so-luong cong"><p style="margin: auto">+</p></div>
			    	</div>
				    <a class="them-vao-gio-hang" href="./xu-ly-gio-hang?id=<%=sanPham.getId()%>"><p style="margin: auto">Thêm vào giỏ</p></a>
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
	    				<c:when test="<%=soLuongTatCa > 0%>">
	    					<div class="thong-ke-danh-gia-va-cac-danh-gia">
		    					<div class="thong-ke-danh-gia">
		    						<div class="thong-ke-sao-co-ban">
			    						<h1 class="trung-binh-so-sao"><%=sanPham.getSoSaoVote()%></h1>
			    						<div class="sao-icon-va-tong-so-luong-danh-gia">
			    							<i class="fa-solid fa-star" id="sao-icon"></i>
			    							<p id="tong-so-luong-danh-gia"><%=soLuongTatCa%> đánh giá</p>
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
		    							<div class="nut-phan-loai" id="phan-loai-tat-ca">
		    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=-1&page=1"></a>
		    								Tất cả
		    							</div>
		    							<div class="nut-phan-loai" id="phan-loai-5-sao">
		    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=5&page=1"></a>
		    								5 <i class="fa-solid fa-star sao-phan-loai"></i>
		    							</div>
		    							<div class="nut-phan-loai" id="phan-loai-4-sao">
		    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=4&page=1"></a>
		    								4 <i class="fa-solid fa-star sao-phan-loai"></i>
		    							</div>
		    							<div class="nut-phan-loai" id="phan-loai-3-sao">
		    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=3&page=1"></a>
		    								3 <i class="fa-solid fa-star sao-phan-loai"></i>
		    							</div>
		    							<div class="nut-phan-loai" id="phan-loai-2-sao">
		    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=2&page=1"></a>
		    								2 <i class="fa-solid fa-star sao-phan-loai"></i>
		    							</div>
		    							<div class="nut-phan-loai" id="phan-loai-1-sao">
		    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=1&page=1"></a>
		    								1 <i class="fa-solid fa-star sao-phan-loai"></i>
		    							</div>
		    							<div class="nut-phan-loai" id="phan-loai-0-sao">
		    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=0&page=1"></a>
		    								0 <i class="fa-solid fa-star sao-phan-loai"></i>
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
		    						
		    						<!-- phan trang -->
		    						<c:if test="<%=soLuongDanhGiaTheoPhanLoai > 0%>">
		    							<c:if test="<%=soLuongPage > 1%>">
				    						<div class="boc-ben-ngoai-phan-trang">
					    						<div class="phan-trang">
					    							<c:if test="<%=prePage > 0%>">
						    							<div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
						    								<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=<%=choose%>&page=<%=prePage%>"></a>
						    								<i class="fa-solid fa-chevron-left" style="margin: auto"></i>
						    							</div>
					    							</c:if>
					    							<c:forEach begin="<%=pageBegin%>" end="<%=pageEnd%>" var="index">
					    								<c:if test="${index == page}">
						    								<div class="trang-hien-tai thanh-phan-phan-trang">
						    									<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=<%=choose%>&page=${index}"></a>
						    									<p style="margin: auto; font-weight: 600">${index}</p>
						    								</div>
					    								</c:if>
					    								<c:if test="${index != page}">
						    								<div class="khong-phai-trang-hien-tai thanh-phan-phan-trang">
						    									<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=<%=choose%>&page=${index}"></a>
						    									<p style="margin: auto; font-weight: 600">${index}</p>
						    								</div>
					    								</c:if>
					    							</c:forEach>
					    							<c:if test="<%=nextPage <= soLuongPage%>">
						    							<div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
															<a href="./chi-tiet-san-pham?id=<%=sanPham.getId()%>&choose=<%=choose%>&page=<%=nextPage%>"></a>
							    							<i class="fa-solid fa-chevron-right" style="margin: auto"></i>
						    							</div>
					    							</c:if>
					    						</div>
				    						</div>
		    							</c:if>
		    						</c:if>
		    					</div>
	    					</div>
	    				</c:when>
	    				
	    				<c:otherwise>
	    					<jsp:include page="khong_co_danh_gia.jsp"/>
	    					<script type="text/javascript">
	    						document.querySelector('.chua-co-danh-gia-nao-cho-san-pham-nay').innerText = 'Chưa có đánh giá nào cho sản phẩm này';
	    					</script>
	    				</c:otherwise>
    				</c:choose>
    				
    				<c:if test="<%=soLuongDanhGiaTheoPhanLoai == 0 && soLuongTatCa > 0%>">
    					<jsp:include page="khong_co_danh_gia.jsp"/>
    				</c:if>
    			</div>
   			</div>
   		</div>
	</div>
	<jsp:include page="footer.jsp"/>
    <script src="./js/common.js"></script>
    <script src="./js/chi_tiet_san_pham.js"></script>
    <script src="./js/xu_ly_gio_hang.js"></script>
    
    <c:if test="${forcusDanhGia == 1}">
    	<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.load-truoc-khi-vao-trang').style.display = 'none';
    			document.querySelector('.than-website').style.display = 'block';
    			setTimeout(() => {        			
	    			window.scrollTo(0, 780.7999877929688);
	    			thanhGachChan.style.animation = 'sangPhai 0s ease forwards';
	        		moTaChiTietSanPham.style.display = 'none';
	        		moTaChiTietSanPham.style.animation = '';
	        		danhGiaSanPham.style.display = 'block';
	        		xetGiaTriChoKeyframes(danhGiaSanPham, 'keoDai2');
	        		danhGiaSanPham.style.animation = 'keoDai2 1.2s ease';
	        		hienTaiDuocClick = 1;
        		}, 0);
    		}, 1200);
    	</script>
   
    </c:if>
    <c:if test="${forcusDanhGia == 0}">
    	<script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').style.display = 'none';
			document.querySelector('.than-website').style.display = 'block';
			window.scrollTo(0, 0);
		}, 1200);
    	</script>
    </c:if>
    
    <script type="text/javascript">
    	var flag = layCookie('flag', 0);
    </script>
    
    <c:if test="${themGioHangStatus == 1 || themGioHangStatus == 2 || themGioHangStatus == 3}">
    	<script type="text/javascript">
    		setTimeout(() => {
	    		document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Đã thêm sản phẩm ${tenSanPham} vào giỏ hàng';
		    	window.scrollTo(<%=session.getAttribute("x")%>, <%=session.getAttribute("y")%>);
	    	}, 1200);
    	</script>
    </c:if>
    
 	<c:if test="${themGioHangStatus == 1}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.thong-bao-mini').style.borderLeft = '5px solid #f00';
    			document.querySelector('.icon-thong-bao-mini').innerHTML = '<i class="fa-sharp fa-solid fa-circle-xmark" id="infomini-icon"></i>';
    			document.querySelector('#infomini-icon').style.color = '#f00';
    			document.querySelector('.icon-thong-bao-mini').style.color = '#f00';
    			document.querySelector('.noi-dung-thong-bao-mini-1').innerText = 'Thất bại';
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Sản phẩm ${tenSanPham} đã hết hàng';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${themGioHangStatus == 2}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.thong-bao-mini').style.borderLeft = '5px solid #f00';
    			document.querySelector('.icon-thong-bao-mini').innerHTML = '<i class="fa-sharp fa-solid fa-circle-xmark" id="infomini-icon"></i>';
    			document.querySelector('#infomini-icon').style.color = '#f00';
    			document.querySelector('.icon-thong-bao-mini').style.color = '#f00';
    			document.querySelector('.noi-dung-thong-bao-mini-1').innerText = 'Thất bại';
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Sản phẩm ${tenSanPham} bạn chọn có số lượng vượt quá số lượng trong kho';
    		}, 1200);
    	</script>
 	</c:if>
    
    <c:if test="${themGioHangStatus == 1 || themGioHangStatus == 2 || themGioHangStatus == 3}">
    	<script src="./js/thong_bao_mini.js"></script>
    </c:if>

    <c:if test='<%=session.getAttribute("themGioHangStatus") == null%>'>
	    <script type="text/javascript">
	    	setTimeout(() => {
	    		window.scrollTo(0, 0);
	    	}, 1200);
    	</script>
    </c:if>
 
    <%
    	session.removeAttribute("themGioHangStatus");
    %>

</body>
</html>