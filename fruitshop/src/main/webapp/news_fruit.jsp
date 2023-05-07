<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tin tức về trái cây | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/news_fruit.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
   
   <jsp:include page="header.jsp"/>

    <!-- Than website -->
    <div class="than-website">
  		<jsp:include page="load_page.jsp"/>
    	<div class="phan-duoc-hien-thi">
	        <div class="tin-tuc-trai-cay">
	            <h1 class="tieu-de-tin-tuc-trai-cay">Cherry vàng - nữ hoàng của mọi loại Cherry</h1>
	            <h5 class="mo-dau-tin-tuc-trai-cay">Cherry vàng sở dĩ được gọi là "nữ hoàng" vì độ hiếm, cũng như công đoạn chăm sóc lẫn thu hoạch đòi hỏi rất cao và cầu kỳ. Công đoạn bảo quản cũng vô cùng nghiêm ngặt và phải đạt tiêu chuẩn cao hơn các loại cherry khác. Với vỏ màu vàng kết hợp với màu đỏ tươi dễ phân biệt, vị ngọt ngon khó cưỡng, không ngoa khi nói cherry vàng là loại cherry ngon nhất thế giới</h5>
	            <div class="ngay-dang-tin-tuc-trai-cay">30/05/2020</div>
	            <div class="anh-demo">
	                <img src="./img/tin_tuc_trai_cay_demo1.jpg" alt="" style="width: 199.38px; height: 300px; margin: auto;"/>
	            </div>
	            <p>Cherry vàng Mỹ có mùa vụ ngắn hơn mùa vụ cherry đỏ, chỉ từ tháng 5 đến tháng 8 hàng năm. Có khi kết thúc sớm hơn và sản lượng cũng không nhiều như cherry đỏ.</p>
	            <p>Trồng cherry vàng rất cực vì đảm bảo đủ lượng nắng, nhạy cảm với mưa gió và nhiệt độ thay đổi của môi trường. Do vỏ mỏng và có màu sáng nên quá trình thu hái cũng như bảo quản người trồng phải cực kỳ thận trọng, nâng niu. </p>
	            <p>Trái Cherry hình trái tim, da căng mọng, quả to, chắc và giòn. Đặc biệt, loại cherry này có độ ngọt cao hơn nhiều so với các loại Cherry khác. Thịt quả có màu vàng kem và khi ăn có hậu mùi caramel rất dễ chịu.</p>
	            <div class="anh-demo">
	                <img src="./img/tin_tuc_trai_cay_demo2.jpg" alt="" style="width: 300px; height: 300px;" />
	            </div>
	            <p>Cũng giống cherry đỏ, cherry vàng Mỹ rất tốt cho sức khỏe với các công dụng như: </p>
	            <p>- Tốt cho hệ miễn dịch, tiêu hóa, là thực phẩm lành mạnh cho những người mắc bệnh tiểu đường.</p>
	            <p>- Tốt cho hệ tim mạch, có tác dụng phòng chống lại bệnh ung thư.</p>
	            <p>- Hiệu quả tốt cho việc giảm đau, giảm viêm với những người bị gout và khớp.</p>
	            <p>- Giúp bạn có những giấc ngủ say và ngon. </p>
	            <p>Mặc dù giá cherry vàng luôn cao hơn cherry đỏ nhưng vẫn là loại trái cây "sang chảnh" được nhiều người chờ đợi và săn đón. </p>
	            <p>Năm nay, đầu mùa cherry vàng, An Minh EIT - F5 Fruit shop về loại cherry của thương hiệu Pride Pack - một trong những thương hiệu nhà vườn cherry ngon nổi tiếng ở Mỹ. </p>
	            <p>----</p>
	            <p>5 lý do bạn nên mua Cherry ở F5 Fruit shop</p>
	            <ul>
	                <li>Nhà nhập khẩu cherry LỚN THỨ 2 toàn Việt Nam năm 2019 theo thống kê của Hiệp hội Cherry Mỹ. </li>
	                <li>Trước mỗi mùa vụ đều có bộ phận đối ngoại ĐẾN TẬN VƯỜN XEM HÀNG HOÁ, quy trình đóng gói trước khi nhập về</li>
	                <li>Giá cả CẠNH TRANH</li>
	                <li>Dịch vụ CHĂM SÓC SAU BÁN HÀNG tốt. Hỗ trợ đổi trả trong 24h</li>
	                <li>TƯ VẤN TẬN TÂM</li>
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