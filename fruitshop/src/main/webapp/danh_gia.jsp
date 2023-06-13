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
    <title>Đánh giá sản phẩm | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/chi_tiet_don_hang.css">
    <link rel="stylesheet" href="./css/phan_trang.css">
    <link rel="stylesheet" href="./css/danh_gia.css">
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
	
	<c:set var="now" value="<%=new Date().getTime()%>"/>
	<c:set var="null" value="<%=null%>"/>
	
    <!-- Than website -->
    <div class="than-website">
        <div class="dieu-huong-va-tieu-de" style="display: flex; flex-direction: column;">
            <div class="dieu-huong-danh-gia">
            	<a href="./home" class="link-to-page">Trang chủ</a>
            	<i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px; padding-left: 10px; padding-right: 10px;"></i>
            	<a href="./don-hang" class="link-to-page">Đơn hàng</a>
            	<i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px; padding-left: 10px; padding-right: 10px;"></i>
            	<a href="./chi-tiet-don-hang?id=${danhSachDonHang.id}" class="link-to-page">Chi tiết đơn hàng</a>
            	<i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px; padding-left: 10px; padding-right: 10px;"></i>
            	<div style="color: #a1a1a1; display: inline-block;">Đánh giá sản phẩm</div>
            </div>
        </div>
        
        <form action="./danh-gia?id=${donHang.idSanPham}" method="post" accept-charset="utf-8" class="boc-input-danh-gia">
            <div class="tieu-de-cua-hang" style="margin: 0px auto 20px auto; padding-top: 0px"><h4>Đánh giá sản phẩm</h4></div>
            
            <c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() > 7 * 3600000 * 24 && donHang.danhGia == 1}">
        		<textarea placeholder="Nhập đánh giá của bạn..." class="input-danh-gia chi-xem-danh-gia" name="noi-dung-danh-gia" required autofocus readonly><c:out value="${danhGia.noiDungBinhLuan}"/></textarea>
        	</c:if>
        	
        	<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() <= 7 * 3600000 * 24}">
        		<textarea placeholder="Nhập đánh giá của bạn..." class="input-danh-gia" name="noi-dung-danh-gia" required autofocus><c:out value="${danhGia.noiDungBinhLuan}"/></textarea>
        	</c:if>
        	
        	<div style="font-size: 16px; font-weight: 600; margin-top: 24px; margin-left: auto; margin-right: auto;">Vote sao</div>
        	<div style="display: flex; margin-top: 12px; margin-left: auto; margin-right: auto;">
       			<input type="hidden" name="so-sao-vote" id="so-sao-vote" value="${danhGia.soSaoVote}">
       			
        		<div class="sao-vote" id="vote-0-sao">
        			0 <i class="fa-solid fa-star sao-phan-loai" style="color: #fdd836;"></i>
        		</div>
        		
        		<div class="sao-vote" id="vote-1-sao">
        			1 <i class="fa-solid fa-star sao-phan-loai" style="color: #fdd836;"></i>
        		</div>
        		
        		<div class="sao-vote" id="vote-2-sao">
        			2 <i class="fa-solid fa-star sao-phan-loai" style="color: #fdd836;"></i>
        		</div>
        		
        		<div class="sao-vote" id="vote-3-sao">
        			3 <i class="fa-solid fa-star sao-phan-loai" style="color: #fdd836;"></i>
        		</div>
        		
        		<div class="sao-vote" id="vote-4-sao">
        			4 <i class="fa-solid fa-star sao-phan-loai" style="color: #fdd836;"></i>
        		</div>
        		
        		<div class="sao-vote" id="vote-5-sao">
        			5 <i class="fa-solid fa-star sao-phan-loai" style="color: #fdd836;"></i>
        		</div>
        	</div>
        	
        	<div class="thong-bao-chua-vote-sao">
        		Note: Bạn chưa vote sao, vui lòng vote sao trước khi gửi đánh giá!
        	</div>
        	
        	<c:if test="${danhGia.ngayBinhLuan != null}">        	
        		<div style="font-size: 16px; margin-top: 24px;"><span style="font-weight: 600;">Ngày đánh giá: </span><fmt:formatDate value="${danhGia.ngayBinhLuan}" pattern="dd/MM/yyyy HH:mm:ss"/></div>
        	</c:if>
        	
        	<div class="boc-ben-ngoai-thao-tac-danh-gia">
	        	<input type="hidden" id="thao-tac" name="thao-tac" value="">
	        	<a href="./chi-tiet-don-hang?id=${danhSachDonHang.id}" class="thao-tac-danh-gia not-button-xoa"><span style="margin: auto;">Quay lại</span></a>
	        	<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() <= 7 * 3600000 * 24}">
		        	<button class="thao-tac-danh-gia not-button-xoa">
	        			<span style="margin: auto;">OK</span>
		        	</button>
	        	</c:if>
	        	<c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() <= 7 * 3600000 * 24 && donHang.danhGia == 1}">
		        	<button class="thao-tac-danh-gia xoa-danh-gia">
		        		<i class="fa-solid fa-trash-can" style="margin-left: auto; margin-top: auto; margin-bottom: auto;"></i>
		        		<span style="margin: auto auto auto 5px;">Xóa</span>
		        	</button>
		        </c:if>
        	</div>
        </form>
    </div>
  
   	<jsp:include page="./footer.jsp"/>
   	<script src="./js/utils.js"></script>
    <script src="./js/common.js"></script>
	
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
			window.scrollTo(0, 0);
		}, 1200);
    	
    	var phanTuTruoc = null;
    	try {
    		phanTuTruoc = document.querySelector('#vote-${danhGia.soSaoVote}-sao');
    		phanTuTruoc.style.color = '#0b74e5';
    		phanTuTruoc.style.backgroundColor = '#f0f8ff';
    		phanTuTruoc.style.border = '1px solid #1a94ff';
    		
    		document.querySelectorAll('.sao-vote').forEach(item => {
    			item.style.cursor = 'default';
    		});
    	}
    	catch (e) {}
    </script>
    
    <!-- danh gia, sua -->
    <c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() <= 7 * 3600000 * 24}"> 
    	<script src="./js/danh_gia.js"></script>
    </c:if>
    
    <!-- xem -->
    <c:if test="${danhSachDonHang.huy == 0 && danhSachDonHang.ngayNhan != null && now - danhSachDonHang.ngayNhan.getTime() > 7 * 3600000 * 24 && item.danhGia == 1}">
    	<script src="./js/danh_gia.js"></script>
    </c:if>

</body>
</html>