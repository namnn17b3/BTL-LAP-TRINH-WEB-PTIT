<%@page import="fruitshop.model.SanPhamTrongGioHang"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/gio_hang.css">
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
	<%
		int soLuongSanPhamTrongGioHang = (int) session.getAttribute("soLuongSanPhamTrongGioHang");
		int soLuongPage = (soLuongSanPhamTrongGioHang % 5 == 0) ? soLuongSanPhamTrongGioHang / 5 : soLuongSanPhamTrongGioHang / 5 + 1;
		int trang = (Integer) request.getAttribute("page");
		
		int pageBegin = trang - (trang - 1) % 5;
		int pageEnd = (pageBegin + 4 > soLuongPage) ? soLuongPage : (pageBegin + 4);
		int prePage = trang - 1;
		int nextPage = trang + 1;
	%>
   	<jsp:include page="./header.jsp"/>
	<jsp:include page="./load_page.jsp"/>
	
	<c:if test="${gioHangStatus == 1 || gioHangStatus == 2}">	
	   	<jsp:include page="thong_bao_mini.jsp"/>
	</c:if>
	
    <!-- Than website -->
    <div class="than-website">
        <div class="dieu-huong-va-tieu-de">
            <div class="dieu-huong-gio-hang"><a href="./home" id="link-to-trang-chu">Trang chủ</a><i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px; padding-left: 10px; padding-right: 10px;"></i><div style="color: #a1a1a1; display: inline-block;">Giỏ hàng</div></div>
            <div class="tieu-de-cua-hang"><h4>Giỏ hàng</h4></div>
        </div>
        
        <div class="thong-tin-gio-hang">
        	<c:if test="${soLuongSanPhamTrongGioHang == 0}">        	
	            <div class="tieu-de-gio-hang">Không có gì trong giỏ hàng!</div>
	            <div class="thong-tin-chi-tiet-gio-hang">
	            	<a href="./home" class="nut-quay-lai-trang-chu_gio-hang">            	
		                <span style="font-weight: 600; margin: auto; font-size: 14px;">Trang chủ</span>
	            	</a>
	            </div>
        	</c:if>
        	
        	<c:if test="${soLuongSanPhamTrongGioHang > 0}">
        		<style>
        			.thong-tin-gio-hang {
        				padding-top: 0px;
        			}
        		</style>
        		
        		<div class="bang-gio-hang">
        			<div class="danh-sach-de-muc-gio-hang">        			
	        			<div class="hinh-anh-san-pham__gio-hang de-muc-gio-hang"><span>Hình ảnh</span></div>
	        			<div class="ten-san-pham__gio-hang de-muc-gio-hang"><span>Tên sản phẩm</span></div>
	        			<div class="gia-san-pham__gio-hang de-muc-gio-hang"><span>Đơn giá</span></div>
	        			<div class="so-luong-san-pham__gio-hang de-muc-gio-hang"><span>Số lượng</span></div>
	        			<div class="trang-thai-san-pham__gio-hang de-muc-gio-hang"><span>Trạng thái</span></div>
	        			<div class="tong-tien-san-pham__gio-hang de-muc-gio-hang"><span>Tổng tiền</span> </div>
	        			<div class="thoi-gian-them-san-pham__gio-hang de-muc-gio-hang"><span>Thời gian</span></div>
	        			<div class="loai-bo-san-pham__gio-hang de-muc-gio-hang"><span>   </span></div>
        			</div>
        			
        			<c:forEach items="${listSanPhamTrongGioHang}" var="item" varStatus="index">
        				<div class="id-san-pham" style="display: none;">${item.idSanPham}</div>
	        			<div class="dong-gio-hang">
	        				<div class="hinh-anh-san-pham hinh-anh-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang"><img src="${item.anh}" width="80px" height="80px"/></div>
		        			<div class="ten-san-pham ten-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang"><a class="go-to-chi-tiet-san-pham" href="./chi-tiet-san-pham?id=${item.idSanPham}">${item.tenSanPham}</a></div>
		        			<div class="gia-san-pham gia-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang"><span style="margin: auto; font-weight: 600"><span style="color: #ff2626;" class="gia-san-pham tien-format">${item.tienTrenDonVi}</span> <span style="color: #ff2626;">VNĐ</span>/${item.donVi}</span></div>
		        			
        					<div class="so-luong-san-pham-hidden" style="display: none;">${item.soLuong}</div>
        					<div class="so-luong-san-pham-con-lai" style="display: none;">${item.soLuongSanPhamConLai}</div>
		        			
		        			<div class="so-luong-san-pham so-luong-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang">
		        				<div class="o-nhap-so-luong">
		        					<div class="nut-tru thanh-phan-o-nhap-so-luong nut-tang-giam-so-luong" style="flex: 2; padding-left: 6px;"><span style="margin: auto">-</span></div>
		        					
		        					<div class="hien-thi-so-luong thanh-phan-o-nhap-so-luong" style="flex: 6" title="${item.soLuong}">
		        						<c:if test="${item.soLuong <= 99}">
		        							<span style="margin: auto; color: #3d464d;">${item.soLuong}</span>
		        						</c:if>
		        						<c:if test="${item.soLuong > 99}">
		        							<span style="margin: auto; color: #3d464d;">99+</span>
		        						</c:if>
		        					</div>
		        					
		        					<div class="nut-cong thanh-phan-o-nhap-so-luong nut-tang-giam-so-luong" style="flex: 2; padding-right: 6px;"><span style="margin: auto">+</span></div>
		        				</div>
		        			</div>
		        			
		        			<div class="trang-thai-san-pham trang-thai-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang">
			        			<c:if test="${item.soLuongSanPhamConLai >= item.soLuong}">
			        				<c:set var="trangThai" value='<%="Còn hàng"%>'/>
			        				<span style="margin: auto; color: #28a745; text-align: center;">${trangThai}</span>
			        			</c:if>
			        			<c:if test="${item.soLuongSanPhamConLai < item.soLuong && item.soLuongSanPhamConLai > 0}">
			        				<c:set var="trangThai" value='<%="Hết hàng"%>'/>
			        				<span style="margin: auto; color: #dc3545; text-align: center;">${trangThai}</span>
			        			</c:if>
			        			<c:if test="${item.soLuongSanPhamConLai == 0}">
			        				<c:set var="trangThai" value='<%="Vượt quá số lượng trong kho"%>'/>
			        				<span style="margin: auto; color: #dc3545; text-align: center;">${trangThai}</span>
			        			</c:if>
		        			</div>
		        			
		        			<div class="tong-tien-san-pham tong-tien-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang"><span style="margin: auto; font-weight: 600"><span class="tien-format" style="color: #ff2626;">${item.tienTrenDonVi * item.soLuong}</span> VNĐ</span></div>
		        			
		        			<div class="thoi-gian-them-san-pham thoi-gian-them-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang"><span style="margin: auto; text-align: center;"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.ngayThem}"/></span></div>
		        			
		        			<div class="loai-bo-san-pham loai-bo-san-pham__gio-hang chung-cua-cac-thong-tin-san-pham__gio-hang">
		        				<div class="nut-loai-bo-san-pham"><i class="fa-solid fa-xmark" style="margin: auto;"></i></div>
		        			</div>
        				</div>
        			</c:forEach>
        		</div>
        		
        		<div class="phantrang-donhang">
	        		<!-- phan trang -->
					<c:if test="<%=soLuongSanPhamTrongGioHang > 0%>">
					    <c:if test="<%=soLuongPage > 1%>">
					        <div class="boc-ben-ngoai-phan-trang">
					            <div class="phan-trang">
					                <c:if test="<%=prePage > 0%>">
					                    <div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
					                        <a href="./gio-hang?page=<%=prePage%>"></a>
					                        <i class="fa-solid fa-chevron-left" style="margin: auto"></i>
					                    </div>
					                </c:if>
					                <c:forEach begin="<%=pageBegin%>" end="<%=pageEnd%>" var="index">
					                    <c:if test="${index == page}">
					                        <div class="trang-hien-tai thanh-phan-phan-trang">
					                            <a href="./gio-hang?page=${index}"></a>
					                            <p style="margin: auto; font-weight: 600">${index}</p>
					                        </div>
					                    </c:if>
					                    <c:if test="${index != page}">
					                        <div class="khong-phai-trang-hien-tai thanh-phan-phan-trang">
					                            <a href="./gio-hang?page=${index}"></a>
					                            <p style="margin: auto; font-weight: 600">${index}</p>
					                        </div>
					                    </c:if>
					                </c:forEach>
					                <c:if test="<%=nextPage <= soLuongPage%>">
					                    <div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
					                        <a href="./gio-hang?page=<%=nextPage%>"></a>
					                        <i class="fa-solid fa-chevron-right" style="margin: auto"></i>
					                    </div>
					                </c:if>
					            </div>
					        </div>
					    </c:if>
					</c:if>
					
					<div style="margin-bottom: 18px; font-size: 14px; font-weight: 600"><i><span style="color: #f61d1d;">(*)</span> Lưu ý: Cập nhật giỏ hàng của bạn để lưu lại mọi thay đổi</i></div>
	
					<div class="nut-thao-tac-dieu-huong__gio-hang">
						<a href="./home" class="quay-lai-trang-chu__gio-hang chuyen-huong__gio-hang"><span style="margin: auto;">Trở lại trang chủ</span></a>
						<a href="./gio-hang" class="cap-nhat-gio-hang__gio-hang chuyen-huong__gio-hang"><span style="margin: auto;">Cập nhật giỏ hàng</span></a>
					</div>
									
					<div class="don-hang__gio-hang">
						<div style="font-size: 28px; font-weight: 600; margin-bottom: 28px;">Đơn hàng</div>
						<div style="display: flex; justify-content: space-between; margin-bottom: 48px;">				
							<div style="font-size: 24px; font-weight: 600;">Thành tiền</div>
							<div style="font-size: 24px; font-weight: 600;"><span style="color: #ff2626;" class="tien-format" id="thanh-tien">${tongTien}</span> VNĐ</div>
						</div>
						<div>Phí trên bao gồm 5% VAT</div>
						<a href="./thanh-toan" class="thanh-toan__gio-hang"><span style="margin: auto;">Tiến hành thanh toán</span></a>
					</div>
        		</div>
				
        	</c:if>
        </div>
    </div>
  
   	<jsp:include page="./footer.jsp"/>
   	<script src="./js/utils.js"></script>
    <script src="./js/common.js"></script>
    
    <c:if test="${soLuongSanPhamTrongGioHang > 0}">
	    <script src="./js/gio_hang.js"></script>
    </c:if>
    
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
			window.scrollTo(0, 0);
		}, 1200);
    	
    	var flag = layCookie('flag', 0);
    	var url = window.location.href;
    </script>
    
    <c:if test="${gioHangStatus == 1}">
    	<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.thong-bao-mini').style.borderLeft = '5px solid #f00';
    			document.querySelector('.icon-thong-bao-mini').innerHTML = '<i class="fa-sharp fa-solid fa-circle-xmark" id="infomini-icon"></i>';
    			document.querySelector('#infomini-icon').style.color = '#f00';
    			document.querySelector('.icon-thong-bao-mini').style.color = '#f00';
    			document.querySelector('.noi-dung-thong-bao-mini-1').innerText = 'Thất bại';
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Sản phẩm ${tenSanPhamBiLoi} bạn chọn có số lượng không hợp lý';
    		}, 1200);
    	</script>
    </c:if>
    
    <c:if test="${gioHangStatus == 2}">
    	<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Cập nhật giỏ hàng thành công';
    		}, 1200);
    	</script>
    </c:if>
    
    <c:if test="${gioHangStatus == 1 || gioHangStatus == 2}">
    	<script src="./js/thong_bao_mini.js"></script>
    </c:if>
</body>
</html>