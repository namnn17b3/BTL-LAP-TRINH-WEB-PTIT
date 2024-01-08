<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String prefix = (String) request.getAttribute("prefix");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin page | Fruit Shop</title>
    <link rel="stylesheet" href="<%=prefix%>/css/common.css">
    <link rel="stylesheet" href="<%=prefix%>/css/admin_page.css">
    <link rel="icon" type="image/x-icon" href="<%=prefix%>/img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<div class="wapper-form"></div>
	<div class="form-user">
		<h1>Thêm tài khoản</h1>
		<div class="user-input">
			<span>Email: </span>
			<input type="text" name="email" id="user-email" value="" placeholder="Nhập email"/>
		</div>
		<div class="user-input">
			<span>Tên:</span>
			<input type="text" name="ten" id="user-ten" value="" placeholder="Nhập tên"/>
		</div>
		<div class="user-input">
			<span>Mật khẩu:</span>
			<input type="password" name="mat-khau" id="user-mat-khau" value="" placeholder="Nhập mật khẩu"/>
		</div>
		<div class="user-input">
			<span>Avatar:</span>
			<input type="file" name="user-avatar" id="user-avatar" accept="image/*"/>
		</div>
		<div class="user-input input-img">
			<div>
				<img alt=""/>
				<div class="close-img">
					<span><i class="fa-solid fa-xmark"></i></span>
				</div>
			</div>
		</div>
		<input type="hidden" name="user-id" id="user-id" value=""/>
		<input type="hidden" name="user-close-image" id="user-close-image" value="0"/>
		<div class="btn-form-user">
			<div class="user-btn-cancel user-btn"><span>Cancel</span></div>
			<div class="user-btn-ok user-btn"><span>OK</span></div>
		</div>
	</div>

    <div class="thanh-tieu-de">
        <span class="tieu-de-admin-page">FruitShop Admin Page</span>
    </div>
    <div class="noi-dung-chinh">
        <div class="thanh-chuc-nang">
            <div class="tai-khoan-admin">
                <div style="display: flex; margin-top: 100px; margin-left: 20px; height: 50px;">
                    <a href="<%=prefix%>/profile" style="text-decoration: none; user-select: none;"><img src="<%=prefix%>/${currentUser.anh}" alt="" class="anh-admin"></a>
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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<%=prefix%>/js/utils.js"></script>
    <script src="<%=prefix%>/js/quan_ly_tai_khoan.js"></script>
    <script src="<%=prefix%>/js/admin_page.js"></script>
</body>
</html>