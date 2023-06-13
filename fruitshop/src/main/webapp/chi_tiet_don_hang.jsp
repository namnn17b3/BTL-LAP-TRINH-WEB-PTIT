<%@page import="java.util.Date"%>
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
    <title>Chi tiết đơn hàng | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/chi_tiet_don_hang.css">
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
   	<jsp:include page="./header.jsp"/>
	<jsp:include page="./load_page.jsp"/>
	
	<c:if test="${danhGiaStatus == 1 || danhGiaStatus == 2 || danhGiaStatus == 3 || danhGiaStatus == 4}">	
	   	<jsp:include page="thong_bao_mini.jsp"/>
	</c:if>
	
	<c:set var="now" value="<%=new Date().getTime()%>"/>
	<c:set var="null" value="<%=null%>"/>
	
	<%
		int soLuongDonHang = (int) request.getAttribute("soLuongDonHang");
		int soLuongPage = (soLuongDonHang % 5 == 0) ? soLuongDonHang / 5 : soLuongDonHang / 5 + 1;
		int trang = (Integer) request.getAttribute("page");
		
		int pageBegin = trang - (trang - 1) % 5;
		int pageEnd = (pageBegin + 4 > soLuongPage) ? soLuongPage : (pageBegin + 4);
		int prePage = trang - 1;
		int nextPage = trang + 1;
	%>
	
    <!-- Than website -->
    <div class="than-website">
        <div class="dieu-huong-va-tieu-de">
            <div class="dieu-huong-gio-hang">
            	<a href="./home" class="link-to-page">Trang chủ</a>
            	<i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px; padding-left: 10px; padding-right: 10px;"></i>
            	<a href="./don-hang" class="link-to-page">Đơn hàng</a>
            	<i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px; padding-left: 10px; padding-right: 10px;"></i>
            	<div style="color: #a1a1a1; display: inline-block;">Chi tiết đơn hàng</div>
            </div>
            <div class="tieu-de-cua-hang"><h4>Chi tiết đơn hàng</h4></div>
        </div>
        
        <div style="width: 100%; display: flex; justify-content: space-between; margin-bottom: 30px;">        
	        <div style="margin-top: auto; font-size: 14px; font-weight: 600; margin-bottom: auto;"><i><span style="color: #f61d1d;">(*)</span> Lưu ý: Bạn có 7 ngày kể từ ngày nhận hàng để đánh giá sản phẩm</i></div>
	        <a class="dowload-file-excel" href="./xuat-don-hang">
	        	<i class="fa-solid fa-download" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        	<span style="margin: auto auto auto 5px;">Xuất file excel</span>
	        </a>
        </div>
        
        <div class="thong-tin-chi-tiet-don-hang">
       		<div class="bang-chi-tiet-don-hang">
       			<div class="danh-sach-de-muc-chi-tiet-don-hang">        			
        			<div class="hinh-anh-san-pham__chi-tiet-don-hang de-muc-chi-tiet-don-hang"><span>Hình ảnh</span></div>
        			<div class="ten-san-pham__chi-tiet-don-hang de-muc-chi-tiet-don-hang"><span>Tên sản phẩm</span></div>
        			<div class="gia-san-pham__chi-tiet-don-hang de-muc-chi-tiet-don-hang"><span>Đơn giá</span></div>
        			<div class="so-luong-san-pham__chi-tiet-don-hang de-muc-chi-tiet-don-hang"><span>Số lượng</span></div>
        			<div class="tong-tien-san-pham__chi-tiet-don-hang de-muc-chi-tiet-don-hang"><span>Tổng tiền</span> </div>
        			<div class="thao-tac-san-pham__chi-tiet-don-hang de-muc-chi-tiet-don-hang"><span>Đánh giá</span></div>
       			</div>
       			
       			<c:forEach items="${listDonHang}" var="item" varStatus="index">
       				<div class="id-san-pham" style="display: none;">${item.idSanPham}</div>
        			<div class="dong-chi-tiet-don-hang">
        				<div class="hinh-anh-san-pham hinh-anh-san-pham__chi-tiet-don-hang chung-cua-cac-thong-tin-san-pham__chi-tiet-don-hang"><img src="${item.anh}" width="80px" height="80px"/></div>
	        			<div class="ten-san-pham ten-san-pham__chi-tiet-don-hang chung-cua-cac-thong-tin-san-pham__chi-tiet-don-hang"><a class="go-to-chi-tiet-san-pham" href="./chi-tiet-san-pham?id=${item.idSanPham}">${item.tenSanPham}</a></div>
	        			<div class="gia-san-pham gia-san-pham__chi-tiet-don-hang chung-cua-cac-thong-tin-san-pham__chi-tiet-don-hang"><span style="margin: auto; font-weight: 600"><span style="color: #ff2626;" class="gia-san-pham tien-format">${item.donGia}</span><span style="color: #ff2626;"> VNĐ</span>/${item.donVi}</span></div>
	        			
	        			<div class="so-luong-san-pham so-luong-san-pham__chi-tiet-don-hang chung-cua-cac-thong-tin-san-pham__chi-tiet-don-hang">
       						<c:if test="${item.soLuong <= 99}">
       							<span style="margin: auto; color: #3d464d;" title="${item.soLuong}">${item.soLuong}</span>
       						</c:if>
       						<c:if test="${item.soLuong > 99}">
       							<span style="margin: auto; color: #3d464d;" title="${item.soLuong}">99+</span>
       						</c:if>
	        			</div>
	        			
	        			<div class="tong-tien-san-pham tong-tien-san-pham__chi-tiet-don-hang chung-cua-cac-thong-tin-san-pham__chi-tiet-don-hang"><span style="margin: auto; font-weight: 600"><span class="tien-format" style="color: #ff2626;">${item.donGia * item.soLuong}</span> VNĐ</span></div>
	        			<div class="thao-tac-san-pham thao-tac-san-pham__chi-tiet-don-hang chung-cua-cac-thong-tin-san-pham__chi-tiet-don-hang">
	        				<!-- khong the danh gia -->
	        				<c:if test="${danhSachDonHang.huy == 1}">	        				
		        				<div class="khong-the-danh-gia thao-tac-don-hang" title="Đã hủy đơn hàng">
		        					<i class="fa-regular fa-pen-to-square" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Đánh giá</span>
	        					</div>
	        				</c:if>
	        				
	        				<!-- khong the danh gia -->
	        				<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan == null}">        				
		        				<div class="khong-the-danh-gia thao-tac-don-hang" title="Chưa nhận hàng">
		        					<i class="fa-regular fa-pen-to-square" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Đánh giá</span>
	        					</div>
	        				</c:if>

	        				<!-- khong the danh gia -->
	        				<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && danhSachDonHang.ngayNhan.getTime() - now > 7 * 3600000 * 24 && item.danhGia == 0}">
	        					<div class="khong-the-danh-gia thao-tac-don-hang" title="Quá thời hạn cho phép đánh giá">
		        					<i class="fa-regular fa-pen-to-square" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Đánh giá</span>
	        					</div>
	        				</c:if>
	        				
	        				<!-- xem -->
	        				<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() > 7 * 3600000 * 24 && item.danhGia == 1}">
	        					<a class="xem-danh-gia co-the-click thao-tac-don-hang" href="./danh-gia?id=${item.idSanPham}">
	        						<i class="fa-solid fa-eye" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Xem</span>
	        					</a>
	        					<!--<a class="sua-danh-gia" href="./danh-gia?id=${item.idSanPham}">
	        						<i class="fa-regular fa-pen-to-square" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Sửa</span>
	        					</a>-->
	        					<!--<a class="co-the-danh-gia" href="./danh-gia?id=${item.idSanPham}">
	        						<i class="fa-regular fa-pen-to-square" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Đánh giá</span>
	        					</a>-->
	        				</c:if>
	        				
	        				<!-- sua -->
	        				<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() <= 7 * 3600000 * 24 && item.danhGia == 1}">
	        					<a class="sua-danh-gia co-the-click thao-tac-don-hang" href="./danh-gia?id=${item.idSanPham}">
	        						<i class="fa-regular fa-pen-to-square" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Sửa</span>
	        					</a>
	        				</c:if>
	        				
	        				<!-- co the danh gia -->
	        				<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() <= 7 * 3600000 * 24 && item.danhGia == 0}">
	        					<a class="co-the-danh-gia co-the-click thao-tac-don-hang" href="./danh-gia?id=${item.idSanPham}">
	        						<i class="fa-regular fa-pen-to-square" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
	        						<span style="margin: auto auto auto 5px;">Đánh giá</span>
	        					</a>
	        				</c:if>
	        			</div>
       				</div>
       			</c:forEach>
       		</div>
       		
       		<!-- phan trang -->
			<c:if test="<%=soLuongDonHang > 0%>">
			    <c:if test="<%=soLuongPage > 1%>">
			        <div class="boc-ben-ngoai-phan-trang">
			            <div class="phan-trang">
			                <c:if test="<%=prePage > 0%>">
			                    <div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
			                        <a href="./chi-tiet-don-hang?id=${danhSachDonHang.id}&page=<%=prePage%>"></a>
			                        <i class="fa-solid fa-chevron-left" style="margin: auto"></i>
			                    </div>
			                </c:if>
			                <c:forEach begin="<%=pageBegin%>" end="<%=pageEnd%>" var="index">
			                    <c:if test="${index == page}">
			                        <div class="trang-hien-tai thanh-phan-phan-trang">
			                            <a href="./chi-tiet-don-hang?id=${danhSachDonHang.id}&page=${index}"></a>
			                            <p style="margin: auto; font-weight: 600">${index}</p>
			                        </div>
			                    </c:if>
			                    <c:if test="${index != page}">
			                        <div class="khong-phai-trang-hien-tai thanh-phan-phan-trang">
			                            <a href="./chi-tiet-don-hang?id=${danhSachDonHang.id}&page=${index}"></a>
			                            <p style="margin: auto; font-weight: 600">${index}</p>
			                        </div>
			                    </c:if>
			                </c:forEach>
			                <c:if test="<%=nextPage <= soLuongPage%>">
			                    <div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
			                        <a href="./chi-tiet-don-hang?id=${danhSachDonHang.id}&page=<%=nextPage%>"></a>
			                        <i class="fa-solid fa-chevron-right" style="margin: auto"></i>
			                    </div>
			                </c:if>
			            </div>
			        </div>
			    </c:if>
			</c:if>
        </div>
    </div>
  
   	<jsp:include page="./footer.jsp"/>
   	<script src="./js/utils.js"></script>
    <script src="./js/common.js"></script>
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
		}, 1200);
    </script>
    
    <c:if test="${soLuongDonHang > 0}">
	    <script type="text/javascript">
		    document.querySelectorAll('.thanh-phan-phan-trang').forEach(item => {
		    	item.onclick = () => {
		    		item.firstElementChild.click();
		    	}
		    });
	    </script>
    </c:if>
    
    <script type="text/javascript">
    	document.querySelectorAll('.thao-tac-don-hang').forEach(item => {
    		item.onclick = () => {    			
		    	themCookie('toaDo', 'x==' + window.pageXOffset.toString() + '_y==' + window.pageYOffset.toString(), 3600000 * 24, 60, '/fruitshop/danh-gia');
		    	themCookie('flag', 0, 3600000 * 24, 60, '/fruitshop/chi-tiet-don-hang');
    		}
    	});
    	var url = window.location.href;
    	url = url.substring(0, url.indexOf('?'));
    	var flag = layCookie('flag', 0);
    </script>
    
    <c:if test="${danhGiaStatus == 1 || danhGiaStatus == 2 || danhGiaStatus == 3 || danhGiaStatus == 4}">
    	<script type="text/javascript">
    		setTimeout(() => {
		    	window.scrollTo(<%=session.getAttribute("x")%>, <%=session.getAttribute("y")%>);
	    	}, 1200);
    	</script>
    </c:if>
    
    <c:if test="${danhGiaStatus == 1}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.thong-bao-mini').style.borderLeft = '5px solid #f00';
    			document.querySelector('.icon-thong-bao-mini').innerHTML = '<i class="fa-sharp fa-solid fa-circle-xmark" id="infomini-icon"></i>';
    			document.querySelector('#infomini-icon').style.color = '#f00';
    			document.querySelector('.icon-thong-bao-mini').style.color = '#f00';
    			document.querySelector('.noi-dung-thong-bao-mini-1').innerText = 'Thất bại';
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Đã quá hạn đánh giá hoặc bị hủy đơn';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${danhGiaStatus == 2}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Đã thêm đánh giá cho sản phẩm ${tenSanPhamDanhGia} thành công';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${danhGiaStatus == 3}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Đã sửa đánh giá cho sản phẩm ${tenSanPhamDanhGia} thành công';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${danhGiaStatus == 4}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Đã xóa đánh giá cho sản phẩm ${tenSanPhamDanhGia} thành công';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${danhGiaStatus == 1 || danhGiaStatus == 2 || danhGiaStatus == 3 || danhGiaStatus == 4}">
    	<script src="./js/thong_bao_mini.js"></script>
    </c:if>
    
    <c:if test='<%=session.getAttribute("danhGiaStatus") == null%>'>
	    <script type="text/javascript">
	    	setTimeout(() => {
	    		window.scrollTo(0, 0);
	    	}, 1200);
    	</script>
    </c:if>
    
    <%
    	session.removeAttribute("danhGiaStatus");
    	session.removeAttribute("tenSanPhamDanhGia");
    %>
    
</body>
</html>