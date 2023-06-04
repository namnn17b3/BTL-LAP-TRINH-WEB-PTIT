<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán thành công | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

	<style type="text/css">
 		.than-website {
 			display: none;
 		}

		.quay-lai-trang-chu { 		
 			display: flex;
 			background-color: #ffd333;
 			margin-bottom: 30px;
 			text-decoration: none;
 			color: #3d464d;
 			width: 158px;
 			height: 40px;
 			font-weight: 600;
 			border-radius: 6px;
 			margin-left: auto;
 			margin-right: auto;
		}
		
		.quay-lai-trang-chu:hover {
  			background-color: #3d464d;
  			color: #fff;
 		}
	</style>

</head>
<body>

    <jsp:include page="./header.jsp"/>
    <jsp:include page="./load_page.jsp"/>
    
    <!-- Than website -->
    <div class="than-website" style="background-image: none; height: auto; width: 1110px; margin-left: auto; margin-right: auto;">
    	<div style="margin-left: auto; margin-right: auto; margin-top: 30px;">
	        <h2 style="color: #008000; font-size: 28px; display: flex;">
	        	<img alt="" src="./img/check_complete.png" style="width: 50px; height: 50px; margin-left: auto;">&nbsp;
	        	<span style="margin-right: auto; margin-top: auto; margin-bottom: auto;">QUÝ KHÁCH ĐÃ ĐẶT HÀNG THÀNH CÔNG</span>
	        </h2>
	        <h3 style="margin: 15px auto 15px auto; font-size: 26px; text-align: center;">
	        	<div>Cảm ơn quý khách đã đặt mua sản phẩm của chúng tôi !</div>
				<div>Đơn hàng của quý khách sẽ được nhân viên kiểm tra và giao hàng trong thời gian sớm nhất.</div>
				<div>Chúng tôi đã gửi cho bạn email thông tin chi tiết về đơn hàng của bạn, vui lòng kiểm tra email để nắm thông tin chi tiết.</div>
	        </h3>
	        <a href="./home" class="quay-lai-trang-chu" >
	        	<span style="margin: auto;">Quay lại trang chủ</span>
	        </a>
    	</div>
    </div>

    <jsp:include page="./footer.jsp"/>
    <script src="./js/common.js"></script>
    
    <script type="text/javascript">
	    setTimeout(() => {
	    	window.scrollTo(0, 0);
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
		}, 1200);
    </script>

</body>
</html>