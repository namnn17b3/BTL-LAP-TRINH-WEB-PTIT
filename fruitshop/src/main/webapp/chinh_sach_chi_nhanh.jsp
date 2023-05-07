<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Chính sách chi nhánh | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/chinh_sach_chi_nhanh.css">
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
            <div class="dieu-huong-chinh-chi-nhanh"><a href="./home" id="link-to-trang-chu">Trang chủ</a><i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px;"></i><div style="color: #a1a1a1; display: inline-block;">Chính sách chi nhánh</div></div>
        </div>
        <div class="thong-tin-chinh-sach-chi-nhanh">
            <div style="display: flex; flex-direction: column; margin: 0px 0px 80px;">
                <h1 class="tieu-de-chinh-sach-chi-nhanh">CHÍNH SÁCH CHI NHÁNH</h1>
                <div class="ngay-thang-ban-hanh">Ngày 20 tháng 9 năm 2020</div>
            </div>
            <p class="doan-van-gioi-thieu">Chúng tôi tự hào là công ty bán lẻ và sỉ trực tiếp của công ty nhập khẩu An Minh - 1 trong 5 công ty nhập khẩu trái cây lớn nhất Việt Nam. Chi nhánh của công ty là những người sẽ mở cửa hàng F5 Fruit shop tại khu vực mà chưa có F5 Fruit shop. Vậy làm chi nhánh của chúng tôi bạn sẽ được gì.</p>
            <ol>
                <li>Hàng hoá tốt nhất từ hệ thống kiểm tra chất lượng của chúng tôi: Thông thường hàng hoá đã được lựa chọn kĩ từ nước ngoài và được bảo quản tốt trong quá trình vận chuyển, nhưng khi về tới Việt Nam, nhân viên của chúng tôi luôn kiểm tra lại hàng và lập tức chuyển vào kho lạnh bảo quản. Đồng thời nhân viên chúng tôi luôn kiểm tra hàng ngày trong kho, đảm bảo hàng luôn chất lượng tốt nhất.</li>
                <li>Giá cả tốt nhất: Nguồn hàCơ hội tham gia hệ thống thương mại điện tử công ty đang phát triểnng của công ty là hàng nhập khẩu trực tiếp và lớn, nên giá cả luôn tốt nhất</li>
                <li>Chính sách chiết khấu rất cao: Công ty luôn có chính sách chiết khấu tốt cho đối tác dựa theo số lượng hàng bán được</li>
                <li>Hỗ trợ marketing: Công ty sẽ hỗ trợ hình ảnh và các thiết kế cần thiết cho việc mở cửa hàng, công ty cũng hỗ trợ việc quảng bá hình ảnh online qua Facebook.</li>
                <li>Hỗ trợ kĩ thuật: Công ty tư vấn về mặt phần mềm và thiết bị cần thiết cho một cửa hàng trái cây</li>
                <li>Cơ hội tham gia hệ thống thương mại điện tử công ty đang phát triển</li>
            </ol>
            <p class="doan-van-ket-thuc">Nếu bạn muốn trở thành đối tác của chúng tôi, còn chần chờ gì nữa mà ko tham gia ngay. LH: 037.450.8888</p>
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