<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chính sách cong-tac-vien | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/chinh_sach_cong_tac_vien.css">
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
            <div class="dieu-huong-chinh-sach-cong-tac-vien"><a href="./home" id="link-to-trang-chu">Trang chủ</a><i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px;"></i><div style="color: #a1a1a1; display: inline-block;">Chính sách đối tác</div></div>
        </div>
        <div class="thong-tin-chinh-sach-cong-tac-vien">
            <div style="display: flex; flex-direction: column; margin: 0px 0px 80px;">
                <h1 class="tieu-de-chinh-sach-cong-tac-vien">CHÍNH SÁCH CỘNG TÁC VIÊN</h1>
                <div class="ngay-thang-ban-hanh">Ngày 20 tháng 9 năm 2020</div>
            </div>
            <p class="doan-van-gioi-thieu">Chúng tôi tự hào là công ty bán lẻ và sỉ trực tiếp của công ty nhập khẩu An Minh - 1 trong 5 công ty nhập khẩu trái cây lớn nhất Việt Nam. Cộng tác viên là người sẽ kiếm đơn hàng online, đặt hàng qua web/app cho khách. Vậy làm cộng tác viên của chúng tôi bạn sẽ được gì.</p>
            <ol>
                <li>Hàng hoá tốt nhất từ hệ thống kiểm tra chất lượng của chúng tôi: Thông thường hàng hoá đã được lựa chọn kĩ từ nước ngoài và được bảo quản tốt trong quá trình vận chuyển, nhưng khi về tới Việt Nam, nhân viên của chúng tôi luôn kiểm tra lại hàng và lập tức chuyển vào kho lạnh bảo quản. Đồng thời nhân viên chúng tôi luôn kiểm tra hàng ngày trong kho, đảm bảo hàng luôn chất lượng tốt nhất.</li>
                <li>Chính sách chiết khấu cao: Công ty luôn có chính sách chiết khấu tốt cho cộng tác viên dựa theo số lượng hàng bán được</li>
            </ol>
            <p class="doan-van-ket-thuc">Nếu bạn muốn trở thành cộng tác viên của chúng tôi, còn chần chờ gì nữa mà ko tham gia ngay. LH: 123.456.7890</p>
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