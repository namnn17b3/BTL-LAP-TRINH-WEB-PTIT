<%@page import="fruitshop.model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lỗi | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/san_pham.css">
    <link rel="stylesheet" href="./css/404_page_not_found.css">
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

    <!-- than website -->
    <div class="than-website">
		<div class="not-found-404">Oop! Thao tác thất bại</div>
		<div class="page-not-found">Không tìm thấy sản phẩm hoặc yêu cầu</div>
		<p class="chi-tiet-loi-404">Chúng tôi không thể tìm thấy sản phẩm hoặc xử lý yêu cầu của bạn</p>
		<p class="chi-tiet-loi-404">Trở lại trang chủ và thử một sản phẩm khác hoặc một yêu cầu khác</p>
		<a href="./home" class="nut-tro-lai-trang-chu">Trở lại trang chủ</a>
    </div>
    
	<jsp:include page="footer.jsp"/>
    
    <script src="./js/common.js"></script>
    <script type="text/javascript">
	   	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').style.display = 'none';
			document.querySelector('.than-website').style.display = 'block';
			window.scrollTo(0, 0);
		}, 1200);
    </script>
</body>
</html>