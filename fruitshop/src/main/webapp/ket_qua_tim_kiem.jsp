<%@page import="java.util.ArrayList"%>
<%@page import="fruitshop.model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kết quả tìm kiếm | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/san_pham.css">
    <link rel="stylesheet" href="./css/danh_sach_san_pham.css">
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
	
	<c:if test="${themGioHangStatus == 1 || themGioHangStatus == 2 || themGioHangStatus == 3}">	
	   	<jsp:include page="thong_bao_mini.jsp"/>
	</c:if>
	
    <!-- Than website -->    
    <div class="than-website">
        <h2 id="danh-sach-san-pham">Kết quả tìm kiếm</h2>
        <%
        	List<SanPham> listSanPham = new ArrayList<>();
        	int soLuongDongSanPham = 0;
       		listSanPham = (List<SanPham>) session.getAttribute("listSanPham");
       		soLuongDongSanPham = (listSanPham.size() % 4 == 0) ? listSanPham.size() / 4 : listSanPham.size() / 4 + 1;
       		pageContext.setAttribute("listSanPham", listSanPham, PageContext.REQUEST_SCOPE);
       		int soLuongSanPhamAll = (int) session.getAttribute("soLuongSanPhamAll");
       		int soLuongPage = (soLuongSanPhamAll % 12 == 0) ? soLuongSanPhamAll / 12 : soLuongSanPhamAll / 12 + 1;
       		int currentPage = (int) session.getAttribute("page");
       		int prePage = currentPage - 1;
       		int nextPage = currentPage + 1;
       		int pageBegin = currentPage - (currentPage - 1) % 5;
			int pageEnd = (pageBegin + 4 > soLuongPage) ? soLuongPage : (pageBegin + 4);
       	%>
       	<div class="boc-ben-ngoai-danh-sach-san-pham">       	
       		<jsp:include page="./single_page_san_pham.jsp"/>
       	</div>
       	
       	<!-- phan trang -->
       	<c:if test="<%=soLuongPage > 1%>">
	       	<c:set var="currentPage" value="<%=currentPage%>"></c:set>
	       	<div class="boc-ben-ngoai-phan-trang">
				<div class="phan-trang">
					<c:if test="<%=prePage > 0%>">
						<form action="./search" method="post" class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
							<button style="display: none;"></button>
							<i class="fa-solid fa-chevron-left" style="margin: auto"></i>
							<input type="hidden" name="ten-san-pham" value="${tenSanPham}">
							<input type="hidden" name="page" value="<%=prePage%>">
						</form>
					</c:if>
					<c:forEach begin="<%=pageBegin%>" end="<%=pageEnd%>" var="index">
						<c:if test="${index == currentPage}">
							<form action="./search" method="post" class="trang-hien-tai thanh-phan-phan-trang">
								<button style="display: none;"></button>
								<input type="hidden" name="ten-san-pham" value="${tenSanPham}">
								<input type="hidden" name="page" value="${index}">
								<p style="margin: auto; font-weight: 600">${index}</p>
							</form>
						</c:if>
						<c:if test="${index != currentPage}">
							<form action="./search" method="post" class="khong-phai-trang-hien-tai thanh-phan-phan-trang">								
								<button style="display: none;"></button>
								<input type="hidden" name="ten-san-pham" value="${tenSanPham}">
								<input type="hidden" name="page" value="${index}">
								<p style="margin: auto; font-weight: 600">${index}</p>
							</form>
						</c:if>
					</c:forEach>
					<c:if test="<%=nextPage <= soLuongPage%>">
						<form action="./search" method="post" class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
							<button style="display: none;"></button>
							<i class="fa-solid fa-chevron-right" style="margin: auto"></i>
							<input type="hidden" name="ten-san-pham" value="${tenSanPham}">
							<input type="hidden" name="page" value="<%=nextPage%>">
						</form>
					</c:if>
				</div>
			</div>
       	</c:if>
    </div>
    
	<jsp:include page="./footer.jsp"/>
	<script src="./js/utils.js"></script>
	<script src="./js/mua_ngay.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/san_pham.js"></script>
    <script src="./js/danh_sach_san_pham.js"></script>
    <script src="./js/xu_ly_gio_hang.js"></script>
    
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
		}, 1200);
    </script>
    
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
		session.removeAttribute("soLuong");
		session.removeAttribute("x");
		session.removeAttribute("y");
		session.removeAttribute("url");
		session.removeAttribute("id");
    %>
    
</body>
</html>