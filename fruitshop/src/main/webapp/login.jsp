<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng nhập | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/login.css">
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

    <!-- Than website -->
    <div class="than-website">
        <form action="./login" method="post" class="form-dang-nhap">
            <div class="noi-dung">
                <h3 class="ten-form">Đăng nhập</h3>
                <div class="email">
                    <div class="ten-input">Email</div>
                    <input placeholder="Email" type="text" name="email" id="email"/>
                    <!-- <div class="canh-bao-invalid-email">
                        <div class="canh-bao">
                            <i class="fa-solid fa-circle-exclamation"></i>
                            <p class="doan-van-canh-bao">Invalid Email !</p>
                        </div>
                    </div> -->
                </div>
                <div class="mat-khau">
                    <div class="ten-input">Mật khẩu</div>
                    <input placeholder="Mật khẩu" type="password" name="mat-khau" id="mat-khau" />
                    <i class="fa-solid fa-eye" id="con-mat"></i>
                    <div style="margin-top: 4px;"><a href="./missing-password" style="width: 100%; text-decoration: none; color: #1A66FF; font-size: 12.8px;">Quên mật khẩu</a></div>
                </div>
                
                <c:if test="${dangNhapKhongThanhCong == 1}">
	                <div class="loi-dang-nhap">Erorr: Tài khoản đang được đăng nhập ở nơi khác</div>                
                </c:if>
                
                <c:if test="${dangNhapKhongThanhCong == 2}">
	                <div class="loi-dang-nhap">Erorr: Thông tin đăng nhập không chính xác</div>                
                </c:if>
                
                <button class="nut-dang-nhap">Đăng nhập</button>
                <div style="margin-bottom: 4px;">Không có tài khoản?</div>
                <div><a href="./register" style="width: 100%; text-decoration: none; color: #1A66FF; font-size: 12.8px;">Tạo tài khoản</a></div>
            </div>
        </form>
    </div>

    <jsp:include page="./footer.jsp"/>
    <script src="./js/common.js"></script>
    <script src="./js/login.js"></script>
    <script type="text/javascript">
	    setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
			window.scrollTo(0, 0);
		}, 1200);
    </script>
</body>
</html>