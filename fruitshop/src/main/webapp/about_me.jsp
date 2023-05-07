<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Về chúng tôi | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/about_me.css">
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
        <div class="anh-nen">
            <img class="anh-nen" src="./img/anh_nen_about_me.jpg" alt="">
        </div>
        <div class="noi-dung-thong-tin">
            <h1 class="ve-chung-toi">About us - F5 Fruit Shop</h1>
            <p style="margin-bottom: 24px;">F5 Fruit Shop là hệ thống bán lẻ TRÁI CÂY NHẬP KHẨU cao cấp trực thuộc công ty TNHH XNK Thương mại An Minh (An Minh.IET). Sau 6 năm hoạt động, F5 Fruit shop đã có 16 cửa hàng trên cả nước Việt Nam.</p>
            <p style="margin-bottom: 40px;">Kinh doanh kết hợp với tầm nhìn công nghệ, công ty An Minh đã bắt tay (hợp tác) với công ty Real Fast Technology để chính thức lấn sân vào lĩnh vực thương mại điện tử, áp dụng công nghệ 4.0 vào hệ thống vận hành.</p>
            <h1 class="su-menh">Sứ Mệnh</h1>
            <p>Vì một Việt Nam khỏe mạnh, F5 Fruit Shop đem đến nguồn thực phẩm trái cây ngoại sạch cho cộng đồng. Thông qua việc đem đến nguồn trái cây, thực phẩm sạch, tốt cho khỏe từ khắp mọi nơi trên thế giới về Việt Nam; đem đến sức khỏe bền lâu cho người dân. Chỉ có công dân khỏe mạnh mới có thể làm cho một quốc gia khỏe mạnh.</p>
            <div class="doi-tac-cua-cong-ty">
                <h2 class="doi-tac-cua-cong-ty-tieu-de">Đối tác của công ty</h2>
            </div>
            <div class="slide-show-doi-tac">
                <div class="bang-thanh-phan-slide-show">
                    <div class="thanh-phan-slide-show">
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/hiep_hoi_cherry_my.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Hiệp hội cherry Mỹ</h3></div>
                        </div class="anh-va-ten-doi-tac">
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/sunview.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Sun View - Mỹ</h3></div>
                        </div>
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/super_fresh.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Super Fresh - Mỹ</h3></div>
                        </div>
                    </div>
                    
                    <!-- test -->
                    <!-- <div class="thanh-phan-slide-show">
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/nonghyup.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Nonghyup - Hàn Quốc</h3></div>
                        </div class="anh-va-ten-doi-tac">
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/ever_good.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Everygood - Hàn Quốc</h3></div>
                        </div>
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/Fruitico.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Fruitico - Úc</h3></div>
                        </div>
                    </div> -->

                    <!-- test -->
                    <!-- <div class="thanh-phan-slide-show">
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/enza.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Nonghyup - Hàn Quốc</h3></div>
                        </div class="anh-va-ten-doi-tac">
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/koru.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Everygood - Hàn Quốc</h3></div>
                        </div>
                        <div class="anh-va-ten-doi-tac">
                            <img src="./img/Costa_Rica.png" alt="" class="anh-dai-dien-doi-tac">
                            <div class="ten-doi-tac"><h3>Fruitico - Úc</h3></div>
                        </div>
                    </div> -->
                </div>
                <div class="bang-nut-chuyen-slide">
                    <div class="nut-chuyen-slide"></div>
                    <div class="nut-chuyen-slide"></div>
                    <div class="nut-chuyen-slide"></div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="./footer.jsp"/>
    <div><a href="#" class="nut-len-dau-trang"><i class="fa-solid fa-circle-chevron-up"></i></a></div>
    <script src="./js/common.js"></script>
    <script src="./js/about_me.js"></script>
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
		}, 1200);
    </script>
</body>
</html>