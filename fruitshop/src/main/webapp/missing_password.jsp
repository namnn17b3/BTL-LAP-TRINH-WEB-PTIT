<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quên mật khẩu | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/missing_password.css">
    <link rel="stylesheet" href="./css/thong_bao.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

	<c:if test="${thongBaoDaGuiMatKhauMoi == 1}">
		<style type="text/css">
			body {
    			--scrollbar-width: 0px;
			}
		</style>
	</c:if>

</head>
<body>

	<!-- Thong bao da gui mat khau moi -->
 	<c:if test="${thongBaoDaGuiMatKhauMoi == 1}">
	    <div class="thong-bao">
	        <div class="thong-bao-ve-viec" style="height: 280px">
	            <div class="tieu-de-thong-bao"><h1 style="margin: auto;"><i class="fa-solid fa-circle-info" id="info-icon"></i> Thông báo</h1></div>
	            <div class="noi-dung-thong-bao">
	                <div class="doan-van-thong-bao" style="margin-bottom: 20px">
	                    <p class="chi-tiet-doan-van-thong-bao">chúng tôi đã gửi mật khẩu mới tới email của bạn</p>
	                    <p class="chi-tiet-doan-van-thong-bao">Bạn hãy đăng nhập và đổi lại mật khẩu</p>
	                </div>
	                <div class="bang-nut-thao-tac">
	                    <div id="nut-ok" style="display: flex;"><h3 style="margin: auto;">OK</h3></div>
	                </div>
	            </div>
	        </div>
	    </div>
	</c:if>

    <jsp:include page="./header.jsp"/>
    
    <!-- Than website -->
    <div class="than-website">
        <form action="#" method="post" class="form-quen-mat-khau">
            <h3 class="ten-form">Tạo lại mật khẩu</h3>
            <div class="email">
                <div class="ten-input">Email</div>
                <input placeholder="Nhập email cần khôi phục mật khẩu" type="text" name="email" id="email"/>
                
                <c:if test="${emailKhongTonTai == 1}">                
	                <div class="loi-email">Không có tài khoản khớp với email này</div>
                </c:if>
                
                <c:if test="${chuaDienEmail == 1}">                
                	<div class="loi-email">Error: Bạn chưa điền email</div>
                </c:if>
                
                <div style="display: flex">
	                <button class="nut-dang-ki">Xác nhận</button>
	                <div class="quay-lai-trang-chu">
		                <a href="./home"></a>
		                <span style="margin: auto;">Trang chủ</span>          
	                </div>
                </div>
            </div>
        </form>
    </div>

    <jsp:include page="./footer.jsp"/>
    <script src="./js/utils.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/missing_password.js"></script>
    
    <script type="text/javascript">
    	var nhanOk = layCookie('nhanOk', 0);
	    document.querySelector('.nut-dang-ki').onclick = () => {
	    	themCookie('nhanOk', 0, 3600000 * 24, 60, '/fruitshop/missing-password');
	    }
    	document.querySelector('.quay-lai-trang-chu').onclick = () => {
	    	themCookie('update_success', 0, 0, 60, '/fruitshop/missing-password');
	    	document.querySelector('.quay-lai-trang-chu').firstElementChild.click();
	    }
    	try {
			document.querySelector('.thong-bao').style.display = 'flex';
			if (nhanOk == 0) {
				setTimeout(() => {
    	    	    document.querySelector('.thong-bao-ve-viec').style.display = 'flex';
    	    	}, 600);
			}
		}
		catch (exception) {}
    </script>
    
    <c:if test="${thongBaoDaGuiMatKhauMoi == 1}">
    	<script type="text/javascript">
    		if (nhanOk == 0) {
    			window.addEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
    		}
    		else {
    			document.querySelector('.thong-bao').style.display = 'none';
    			window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
    			document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
    		}
    		
    		document.querySelector('#nut-ok').onclick = () => {
	    		themCookie('nhanOk', 1, 3600000 * 24, 60, '/fruitshop/missing-password');
	    	    document.querySelector('.thong-bao').style.animation = 'truotTuDuoiLen linear 0.5s forwards';
	    	    document.querySelector('html').style.scrollBehavior = 'smooth';
	    	    window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
	    	    document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
	    	    setTimeout(() => {
	    	        document.querySelector('.thong-bao').style.display = 'none';
	    	    }, 500);
	    	}
    	</script>
    </c:if>
    
</body>
</html>