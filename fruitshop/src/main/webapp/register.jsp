<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng kí | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/register.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<!-- Thong bao dang ki thanh cong -->
	<c:if test="${thongBaoXacNhan == 1}">
	    <form class="thong-bao" action="./register" method="post">
	        <div class="thong-bao-ve-viec">
	            <div class="tieu-de-thong-bao"><h1 style="margin: auto;"><i class="fa-solid fa-circle-info" id="info-icon"></i> Thông báo</h1></div>
	            <div class="noi-dung-thong-bao">
	                <div class="doan-van-thong-bao">
	                    <p class="chi-tiet-doan-van-thong-bao">Chúng tôi đã gửi mã xác nhận về email cho bạn</p>
	                    <p class="chi-tiet-doan-van-thong-bao">Bạn có 1 phút để điền mã xác nhận</p>
	                </div>
	                <h2 class="thoi-gian"></h2>
	                <input type="text" name="code" id="code"/>
	                <input type="hidden" name="sent" id="sent" value="1"/>
	                <div class="bang-nut-thao-tac">
	                    <button id="nut-ok" style="display: flex;"><h3 style="margin: auto;">OK</h3></button>
	                    <div id="nut-huy-bo"><h3 id="huy-bo-thong-bao">Hủy bỏ<h3></div>
	                </div>
	            </div>
	        </div>
	    </form>
	    
	    <form method="post" action="./register" class="xac-nhan-huy-bo" style="display: none">
	    	<button></button>	    	
			<input type="hidden" name="da-huy-bo" id="da-huy-bo" value="1"/>
	    </form>
	</c:if>
    
    <!-- Thong bao dang ki lai -->
    <c:if test="${thongBaoDangKiLai == 1}">
	    <div class="thong-bao">
	        <div class="thong-bao-ve-viec" style="height: 280px">
	            <div class="tieu-de-thong-bao"><h1 style="margin: auto;"><i class="fa-solid fa-circle-info" id="info-icon"></i> Thông báo</h1></div>
	            <div class="noi-dung-thong-bao">
	                <div class="doan-van-thong-bao" style="margin-bottom: 20px">
	                    <p class="chi-tiet-doan-van-thong-bao">Bạn đã nhập sai mã xác nhận quá 3 lần</p>
	                    <p class="chi-tiet-doan-van-thong-bao">Vui lòng đăng kí lại tài khoản</p>
	                </div>
	                <div class="bang-nut-thao-tac">
	                    <div id="nut-ok" style="display: flex;"><h3 style="margin: auto;">OK</h3></div>
	                </div>
	            </div>
	        </div>
	    </div>
	    
	    <form method="post" action="./register" class="dong-y-dang-nhap-lai" style="display: none">
	    	<button></button>	    	
			<input type="hidden" name="dong-y-dang-nhap-lai" id="dong-y-dang-nhap-lai" value="1"/>
	    </form>
    </c:if>

	<jsp:include page="./header.jsp"/>

    <!-- Than website -->
    <div class="than-website">
        <form action="./register" class="form-dang-ki" method="post" enctype="multipart/form-data">
            <div class="noi-dung">
                <div style="display: flex">
                	<h3 class="ten-form">Đăng kí</h3>
                </div>
                <div class="thong-tin-dang-ki">
                	<div class="phan-thong-tin">                		
		                <div class="ten">
		                    <div class="ten-input">Tên <span style="color: #f61d1d">(*)</span></div>
		                    <input placeholder="Tên" type="text" name="ten" id="ten"/>
		                    <!-- <div class="canh-bao-invalid">
		                        <div class="canh-bao">
		                            <i class="fa-solid fa-circle-exclamation"></i>
		                            <p class="doan-van-canh-bao">Invalid ${item.querySelector('.ten-input').innerText} !</p>
		                        </div>
		                    </div> -->
		                </div>
		                <div class="email">
		                    <div class="ten-input">Email <span style="color: #f61d1d">(*)</span></div>
		                    <input placeholder="Email" type="text" name="email" id="email"/>
		                    <div style="color: #f61d1d; display: none">Email đã tồn tại!</div>
		                </div>
		                <div class="mat-khau">
		                    <div class="ten-input">Mật khẩu <span style="color: #f61d1d">(*)</span></div>
		                    <input placeholder="Mật khẩu" type="password" name="mat-khau" id="mat-khau" />
		                    <i class="fa-solid fa-eye" id="con-mat-1"></i>
		                    <div style="font-size: 16px; margin-top: 4px; color: #3D464D">Mật khẩu phải có ít nhất 6 kí tự.</div>
		                </div>
		                <div class="nhap-lai-mat-khau">
		                    <div class="ten-input">Nhập lại mật khẩu <span style="color: #f61d1d">(*)</span></div>
		                    <input placeholder="Nhập lại mật khẩu" type="password" name="nhap-lai-mat-khau" id="nhap-lai-mat-khau" />
		                    <i class="fa-solid fa-eye" style="top: 53%" id="con-mat-2"></i>
		                </div>
                	</div>
                	<div class="phan-anh">
                		<div class="anh-dai-dien">
	                		<input type="file" name="anh" style="display: none" id="upload-file" accept="image/*"/>
	                		<img alt="" src="./img/fb-no-img.png" id="anh-upload"/>
	                		<div class="doi-anh-dai-dien"><span style="margin: auto">Đổi ảnh đại diện</span></div>
                		</div>
                		<div style="margin: 20px auto 0px auto">Cập nhật ảnh đại diện của bạn</div>
                	</div>
                </div>
                <button class="nut-dang-ki">Đăng kí</button>
                <div style="font-size: 16px; color: #3D464D">Đã có tài khoản?</div>
                <div style="margin-top: 12px;"><a href="./login.html" style="width: 100%; text-decoration: none; color: #1A66FF; font-size: 12.8px;">Đăng nhập</a></div>
            </div>
        </form>
    </div>

    <jsp:include page="./footer.jsp"/>
    <script src="./js/common.js"></script>
    <script src="./js/register.js"></script>
    <c:if test="${thongBaoXacNhan == 1}">
    	<script type="text/javascript">
	    	var time = <%=session.getAttribute("timeOut")%>;
	    	var timeCounter = document.querySelector('.thoi-gian')
	    	var nutOk = document.querySelector('#nut-ok');
			if (time >= 0 && time <= 59) {
				phut = parseInt(time / 60).toString();
    			giay = parseInt(time % 60).toString();
    			if (phut.length < 2) {
    				phut = '0' + phut;
    			}
    			if (giay.length < 2) {
    				giay = '0' + giay;
    			}
    			timeString = 'Thời gian còn lại: ' + phut + ':' + giay;
    			timeCounter.innerText = timeString;
    			time--;
			}
			if (time < 0) {
				timeCounter.innerText = 'Hết thời gian! Vui lòng đăng kí lại tài khoản';
				nutOk.style.opacity = '0.5';
			}
	    	var interval = setInterval(() => {
	    		if (time < 0) {
	    			timeCounter.innerText = 'Hết thời gian! Vui lòng đăng kí lại tài khoản';
	    			clearInterval(interval);
	    			nutOk.style.cursor = 'not-allowed';
	    			nutOk.onclick = (e) => {
	    				e.preventDefault();
	    			}
	    			nutOk.onmousemove = () => {
	    				nutOk.style.backgroundColor = '#ffd333';
	    				nutOk.style.color = '#3d464d';
	    			}
	    			nutOk.style.opacity = '0.5';
	    		}
	    		else {
	    			phut = parseInt(time / 60).toString();
	    			giay = parseInt(time % 60).toString();
	    			if (phut.length < 2) {
	    				phut = '0' + phut;
	    			}
	    			if (giay.length < 2) {
	    				giay = '0' + giay;
	    			}
	    			timeString = 'Thời gian còn lại: ' + phut + ':' + giay;
	    			timeCounter.innerText = timeString;
	    			time--;
	    		}
	    	}, 1000);
	    	
	    	window.addEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
	    	setTimeout(() => {
	    	    document.querySelector('.thong-bao-ve-viec').style.display = 'flex';
	    	}, 600);
	    	
	    	document.querySelector('#nut-huy-bo').onclick = () => {
	    	    document.querySelector('.thong-bao').style.animation = 'truotTuDuoiLen linear 0.5s forwards';
	    	    document.querySelector('html').style.scrollBehavior = 'smooth';
	    	    window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
	    	    document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
	    	    setTimeout(() => {
	    	        document.querySelector('.thong-bao').style.display = 'none';
		    	    document.querySelector('.xac-nhan-huy-bo').firstElementChild.click();
	    	    }, 500);
	    	}
    	</script>
    </c:if>
    
    <c:if test="${thongBaoDangKiLai == 1}">
    	<script type="text/javascript">
	    	window.addEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
	    	setTimeout(() => {
	    	    document.querySelector('.thong-bao-ve-viec').style.display = 'flex';
	    	}, 600);
	
	    	document.querySelector('#nut-ok').onclick = () => {
	    	    document.querySelector('.thong-bao').style.animation = 'truotTuDuoiLen linear 0.5s forwards';
	    	    document.querySelector('html').style.scrollBehavior = 'smooth';
	    	    window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
	    	    document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
	    	    setTimeout(() => {
	    	        document.querySelector('.thong-bao').style.display = 'none';
	    	        document.querySelector('.dong-y-dang-nhap-lai').firstElementChild.click();
	    	    }, 500);
	    	}
    	</script>
    </c:if>
</body>
</html>