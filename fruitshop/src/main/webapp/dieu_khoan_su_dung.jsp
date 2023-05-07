<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Điều khoản sử dụng | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/dieu_khoan_su_dung.css">
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
            <div class="dieu-huong-chinh-sach-chung"><a href="./home" id="link-to-trang-chu">Trang chủ</a><i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px;"></i><div style="color: #a1a1a1; display: inline-block;">Quy định chung</div></div>
        </div>
        <div class="thong-tin-chinh-sach-chung">
            <div style="display: flex; flex-direction: column; margin: 0px 0px 80px;">
                <h1 class="tieu-de-chinh-sach-chung">CHÍNH SÁCH VÀ QUY ĐỊNH CHUNG</h1>
                <div class="ngay-thang-ban-hanh">Ngày 20 tháng 9 năm 2020</div>
            </div>
            <p class="doan-van-gioi-thieu">Công ty TNHH XNK Thương Mại An Minh phát triển và giữ bản quyền website www.f5fruitshop.vn Quý khách xin vui lòng lưu ý những quy định dưới đây trước khi sử dụng:</p>
            <ol>
                <li>Công ty TNHH XNK Thương Mại An Minh cam kết rằng mọi thông tin và dữ liệu liên quan tới sản phẩm, dịch vụ hoặc các nội dung khác tại trang web được cung cấp với mức độ tin cậy cao nhất nhằm giúp người tiêu dùng tra cứu, tham khảo, tìm hiểu và định hướng thông tin trước khi quyết định mua sản phẩm. Vì vậy, Công ty TNHH XNK Thương Mại An Minh không chịu bất cứ trách nhiệm pháp lý gì đối với bất kỳ ai liên quan đến việc sử dụng các thông tin có trên website.</li>
                <li>Biểu trưng tên miền website www.f5fruitshop.vn, logo F5 Fruit shop, logo F5 Fruit shop đăng trên website là sở hữu của Công ty TNHH XNK Thương Mại An Minh, đã được đăng ký nhãn hiệu độc quyền và không được sử dụng lại với bất kỳ hình thức nào.</li>
                <li>Toàn bộ website, các nội dung và thông tin chi tiết cụ thể của website thuộc quyền sở hữu của Công ty TNHH XNK Thương Mại An Minh, được bảo hộ theo pháp luật của nước CHXHCN Việt Nam. Tuỳ thuộc yêu cầu hoạt động, Công ty TNHH XNK Thương Mại An Minh, có thể điều chỉnh tổng thể việc sử dụng website hoặc các nội dung của website mà không cần báo trước. Trong những trường hợp này, Công ty TNHH XNK Thương Mại An Minh cũng không chịu bất cứ trách nhiệm gì đối với bất cứ thiệt hại nào do việc thay đổi website gây ra.</li>
                <li>Người sử dụng phải tuân theo những hướng dẫn và chỉ dẫn sử dụng website www.f5fruitshop.vn một cách đầy đủ, đúng cách nhất và không có bất kỳ hành vi nào trái pháp luật, trái đạo đức trong việc sử dụng website.</li>
                <li>Khi Quý khách hàng truy cập vào trang web của chúng tôi thì được mặc nhiên hiểu rằng Quý khách đã đọc và hiểu rõ các Quy định sử dụng của trang web, đồng ý với các Quy định được đặt ra một cách tự nguyện. Trang web có quyền thay đổi, chỉnh sửa, bổ sung các quy định sử dụng website mà không cần phải thông báo trước và những quy định sử dụng mới đó sẽ có hiệu lực ngay khi vừa được đăng tải trên website. Khi Quý khách tiếp tục sử dụng website thì cũng đồng nghĩa rằng Quý khách chấp nhận những sự thay đổi đó.</li>
            </ol>
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