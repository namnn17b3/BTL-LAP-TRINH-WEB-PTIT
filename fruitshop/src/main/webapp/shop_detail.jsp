<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cửa hàng | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/shop_detail.css">
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
        <div class="dieu-huong-va-tieu-de">
            <div class="dieu-huong-cua-hang"><a href="./home" id="link-to-trang-chu">Trang chủ</a><i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px;"></i><div style="color: #a1a1a1; display: inline-block;">Cửa hàng</div></div>
            <div class="tieu-de-cua-hang"><h4>Cửa hàng<h4></div>
        </div>
        <div class="thong-tin-cua-hang">
            <h4 class="tieu-de-cua-hang" style="margin: 0px 0px 28px;">Bài tập lớn lập trình web - Học viên Công nghệ Bưu chính Viễn thông PTIT</h4>
            <div class="thong-tin-chi-tiet-cua-hang">
                <div class="chi-tiet-cua-hang"><i class="fa-sharp fa-solid fa-location-dot"></i> &nbsp;&nbsp;Cơ sở đào tạo tại Hà Nội: Km10, Đường Nguyễn Trãi, Q.Hà Đông, Hà Nội<br/></div>
                <div class="chi-tiet-cua-hang"><i class="fa-solid fa-phone"></i> &nbsp;&nbsp;Phone: 123.456.7890<br/></div>
                <div class="chi-tiet-cua-hang"><i class="fa-regular fa-envelope"></i> &nbsp;&nbsp;Mail: baitaplon@ptit.fruitshop.vn<br/></div>
                <div class="chi-tiet-cua-hang"><i class="fa-solid fa-money-check-dollar"></i> &nbsp;&nbsp;Bank account: ABCXYZBank - CTK: Tốc Biến Vào Trụ - STK: 9999999999999</div>
            </div>
        </div>
    </div>
  
   	<jsp:include page="./footer.jsp"/>
    <script src="./js/common.js"></script>
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
		}, 1200);
    </script>
</body>
</html>