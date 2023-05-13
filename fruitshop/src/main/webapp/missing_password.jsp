<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên mật khẩu | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/missing_password.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <jsp:include page="./header.jsp"/>
    
    <!-- Than website -->
    <div class="than-website">
        <form action="#" method="post" class="form-quen-mat-khau">
            <h3 class="ten-form">Tạo lại mật khẩu</h3>
            <div class="email">
                <div class="ten-input">Email</div>
                <input placeholder="Nhập email cần khôi phục mật khẩu" type="text" name="email" id="email"/>
                <div class="loi-email">Không có tài khoản khớp với email này</div>
                <button class="nut-gui-ma">Gửi mã</button>
            </div>
        </form>
    </div>

    <jsp:include page="./footer.jsp"/>
    <script src="./js/common.js"></script>
    <script src="./js/missing_password.js"></script>
</body>
</html>