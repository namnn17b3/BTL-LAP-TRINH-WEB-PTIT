<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tin tức về sức khỏe | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/news_health.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
    
    <jsp:include page="header.jsp"/>

    <!-- Than website -->
    <div class="than-website">
    	<jsp:include page="load_page.jsp"/>
    	<div class="phan-duoc-hien-thi">
	        <div class="tin-tuc-suc-khoe">
	            <h1 class="tieu-de-tin-tuc-suc-khoe">☀️Tiết trời ẩm ương, tăng cường đề kháng 💦</h1>
	            <h5 class="mo-dau-tin-tuc-suc-khoe">🤧 Thời điểm giao mùa là lúc mọi người dễ mắc bệnh (cảm lạnh, viêm xoang, đau họng, nhức đầu). Đặc biệt là những người có sức đề kháng và hệ miễn dịch yếu. Để giải quyết tình trạng này, bạn nên sử dụng các nguyên liệu tự nhiên có sẵn trong nhà để tăng đề kháng nhé!</h5>
	            <div class="ngay-dang-tin-tuc-suc-khoe">26/05/2020</div>
	            <div class="anh-demo">
	                <img src="./img/tin_tuc_suc_khoe_demo1.jpg" alt="" style="width: 450px; height: 300px; margin: auto;"/>
	            </div>
	            <p>🍋🍯Chanh tươi + mật ong: kháng khuẩn mạnh, thức uống detox buổi sáng cực tốt.<br/>
	                - Mật ong có tính kháng khẩu cao, trị ho rất tốt. Hiệu quả có thể thấy ngay khi kết hợp với chanh (hoặc chanh đào) làm thức uống mỗi sáng. Đây cũng là món uống detox giúp diệt những vi khuẩn xấu trong đường ruột, đào thải các độc tố và làm hoạt động của gan được dễ chịu hơn.</p>
	            <p>🍯 Gừng + mật ong: ấm cơ thể, chống virus gây bệnh.<br/>
	                Với tính nóng và khả năng sát khuẩn cao, gừng và mật ong giúp chống lại các virus gây bệnh, làm ấm cơ thể, cải thiện hệ miễn dịch và cung cấp các dưỡng chất cũng như vitamin cho cơ thể.</p>
	            <p>>>>> Tuy nhiên, nếu bạn mới uống, sẽ cảm thấy lít kít vì phải chuẩn bị nhiều thứ, hoặc khó uống vì không rõ liều lượng.<br/>
	                Giải pháp tốt nhất là mua các loại chanh-mật ong, gừng-mật ong chế biến sẵn với hàm lượng đầy đủ mật ong, chanh, gừng tự nhiên sẽ giúp bạn tiết kiệm thời gian, có mùi thơm hấp dẫn dễ uống. Sản phẩm có dùng đường biotic fructose (loại đường thấp năng lượng, giúp tăng cường tiêu hóa).</p>
	            <p>🍯 GỪNG - MẬT ONG hũ 1kg
	                - Giá niêm yết: 205k -> 155k</p>
	            <div class="anh-demo">
	                <img src="./img/tin_tuc_suc_khoe_demo2.jpg" alt="" style="width: 300px; height: 300px;" />
	            </div>
	            <p>🍯 CHANH - MẬT ONG hũ 1kg </p>
	            <p>- Giá niêm yết: 185k -> 135k</p>
	            <div class="anh-demo">
	                <img src="./img/tin_tuc_suc_khoe_demo3.jpg" alt="" style="width: 300px; height: 300px;" />
	            </div>
	            <p>Sản phẩm của tập đoàn Nonghyup (tập đoàn nông nghiệp lớn nhất Hàn Quốc) với dây chuyền chế biến hiện đại, vệ sinh, an toàn cho sức khoẻ, được nhập trực tiếp từ Hàn Quốc bởi An Minh IET. - F5 Fruit shop.</p>
	            <p>----</p>
	            <p>Lý do bạn nên mua sản phẩm ở F5 Fruit shop:</p>
	            <ul>
	                <li>Tất cả các sản phẩm đều được nhập trực tiếp bởi công ty Xuất nhập khẩu An Minh</li>
	                <li>Giấy tờ nhập khẩu, giấy kiểm dịch đầy đủ, rõ ràng</li>
	                <li>Sản phẩm mới sản phẩm, hạn sử dụng còn rất xa</li>
	                <li>Nói không với hàng Trung Quốc, hàng sản xuất tại Trung Quốc gắn mác nước ngoài</li>
	                <li>Các loại mứt, trái cây sấy khô không có chất bảo quản, an toàn cho sức khoẻ người tiêu dùng.</li>
	            </ul>
	        </div>
	    </div>
    </div>

    <jsp:include page="footer.jsp"/>
    
    <script src="./js/common.js"></script>
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').style.display = 'none';
			document.querySelector('.phan-duoc-hien-thi').style.display = 'block';
		}, 1200);
    </script>
</body>
</html>