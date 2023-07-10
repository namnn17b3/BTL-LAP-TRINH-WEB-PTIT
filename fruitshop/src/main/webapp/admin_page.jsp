<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin page | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/admin_page.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    <div class="thanh-tieu-de">
        <span class="tieu-de-admin-page">FruitShop Admin Page</span>
    </div>
    <div class="noi-dung-chinh">
        <div class="thanh-chuc-nang">
            <div class="tai-khoan-admin">
                <div style="display: flex; margin-top: 100px; margin-left: 20px; height: 50px;">
                    <a href="./profile" style="text-decoration: none; user-select: none;"><img src="${currentUser.anh}" alt="" class="anh-admin"></a>
                    <div style="color: #fff; margin-top: auto; margin-bottom: auto; user-select: none; font-weight: 600;">
                        <div>Welcome Admin Page</div>
                        <div>Nguyễn Ngọc Nam</div>
                    </div>
                </div>
            </div>
            <div class="tap-hop-cac-chuc-nang">
                <div class="chuc-nang main">
                    <i class="fa-solid fa-gauge icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Main DashBoard</span>
                </div>
                <div class="chuc-nang doanh-thu-theo-ngay">
                    <i class="fa-solid fa-chart-pie icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Doanh thu theo ngày</span>
                </div>
                <div class="chuc-nang doanh-thu-theo-thang">
                    <i class="fa-solid fa-chart-simple icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Doanh thu theo tháng</span>
                </div>
                <div class="chuc-nang quan-ly-don-hang">
                    <i class="fa-solid fa-table-list icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Quản lý đơn hàng</span>
                </div>
                <div class="chuc-nang quan-ly-san-pham">
                    <i class="fa-solid fa-shoe-prints icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Quản lý sản phẩm</span>
                </div>
                <div class="chuc-nang quan-ly-tai-khoan">
                    <i class="fa-solid fa-user icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Quản lý tài khoản</span>
                </div>
                <div class="chuc-nang top-10-san-pham">
                    <i class="fa-solid fa-shoe-prints icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Top 10 sản phẩm</span>
                </div>
                <div class="chuc-nang top-5-khach-hang">
                    <i class="fa-solid fa-user icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Top 5 khách hàng</span>
                </div>
                <div class="chuc-nang quay-lai-home">
                    <i class="fa-solid fa-house icon-chuc-nang"></i>
                    <span class="ten-chuc-nang">Home</span>
                    <a href="./home" style="display: none;"></a>
                </div>
            </div>
        </div>
        <div class="noi-dung-show-wapper">
        	<iframe class="noi-dung-show"></iframe>
        </div>
    </div>

	<div id="chuc-nang"><%=request.getAttribute("chucNang")%></div>
    
	<div id="csrf-token" style="display: none;"><%=session.getAttribute("csrfToken")%></div>
    <script src="./js/admin_page.js"></script>
	<script src="./js/csrf_token.js"></script>

</body>
</html>