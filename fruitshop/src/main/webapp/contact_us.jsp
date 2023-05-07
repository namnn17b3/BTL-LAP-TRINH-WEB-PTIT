<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liên hệ | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/contact_us.css">
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
            <div class="dieu-huong-lien-he"><a href="./home" id="link-to-trang-chu">Trang chủ</a><i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px;"></i><div style="color: #a1a1a1; display: inline-block;">Liên hệ</div></div>
            <div class="tieu-de-lien-he"><h2>Liên hệ<h2></div>
        </div>
        <div class="thong-tin-lien-he">
            <h4 class="PTIT">Học viện Công Nghệ Bưu chính Viễn thông PTIT</h4>
            <div class="bang-dia-chi">
                <div class="dia-chi">
                    <i class="fa-sharp fa-solid fa-location-dot"></i> Trụ sở chính: 122 Hoàng Quốc Việt, Q.Cầu Giấy, Hà Nội.<br/>
                    Phone: 123.456.7890
                </div>
                <div class="dia-chi">
                    <i class="fa-sharp fa-solid fa-location-dot"></i> Cơ sở đào tạo tại Hà Nội: Km10, Đường Nguyễn Trãi, Q.Hà Đông, Hà Nội<br/>
                    Phone: 123.456.7890
                </div>
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