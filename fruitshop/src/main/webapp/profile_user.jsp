<%@page import="fruitshop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hồ sơ người dùng | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/register.css">
    <link rel="stylesheet" href="./css/thong_bao.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<c:if test="${thongBaoCapNhatThanhCong == 1}">
		<style type="text/css">
			body {
    			--scrollbar-width: 0px;
			}
		</style>
	</c:if>
</head>
<body>
	<%
		User currentUser = (User) session.getAttribute("currentUser");
	%>
    <!-- Thong bao dang ki lai -->
 	<c:if test="${thongBaoCapNhatThanhCong == 1}">
	    <div class="thong-bao">
	        <div class="thong-bao-ve-viec" style="height: 230px">
	            <div class="tieu-de-thong-bao"><h1 style="margin: auto;"><i class="fa-solid fa-circle-info" id="info-icon"></i> Thông báo</h1></div>
	            <div class="noi-dung-thong-bao">
	                <div class="doan-van-thong-bao" style="margin-bottom: 20px">
	                    <p class="chi-tiet-doan-van-thong-bao">Bạn đã cập nhật thông tin thành công</p>
	                </div>
	                <div class="bang-nut-thao-tac">
	                    <div id="nut-ok" style="display: flex;"><h3 style="margin: auto;">OK</h3></div>
	                </div>
	            </div>
	        </div>
	    </div>
	</c:if>

	<!-- Webcam -->
	<jsp:include page="./webcam.jsp"/>

	<jsp:include page="./header.jsp"/>

    <!-- Than website -->
    <div class="than-website">
        <form action="./profile" class="form-dang-ki" method="post" enctype="multipart/form-data" style="height: 550px">
            <div class="noi-dung">
                <div style="display: flex">
                	<h3 class="ten-form">Hồ sơ</h3>
                </div>
                <div class="thong-tin-dang-ki">
                	<div class="phan-thong-tin">
		                <div class="ten">
		                    <div class="ten-input">Tên <span style="color: #f61d1d">(*)</span></div>
		                    <input value="<c:out value='<%=currentUser.getTen()%>'/>" placeholder="Tên" type="text" name="ten" id="ten"/>
		                    <!-- <div class="canh-bao-invalid">
		                        <div class="canh-bao">
		                            <i class="fa-solid fa-circle-exclamation"></i>
		                            <p class="doan-van-canh-bao">Invalid ${item.querySelector('.ten-input').innerText} !</p>
		                        </div>
		                    </div> -->
		                </div>
		                <div class="email">
		                    <div class="ten-input">Email <span style="color: #f61d1d">(*)</span></div>
		                    <input value="<c:out value='<%=currentUser.getEmail()%>'/>" type="text" name="email" id="email" readonly style="cursor: not-allowed; user-select: none; caret-color: transparent; background-color: #efece8; color: #c6c3bd;"/>
		                </div>
		                <div class="mat-khau">
		                    <div class="ten-input">Mật khẩu <span style="color: #f61d1d">(*)</span></div>
		                    <input value="<c:out value='<%=currentUser.getMatKhau()%>'/>" placeholder="Mật khẩu" type="password" name="mat-khau" id="mat-khau" />
		                    <i class="fa-solid fa-eye" id="con-mat-1"></i>
		                    <div style="font-size: 16px; margin-top: 4px; color: #3D464D">Mật khẩu phải có ít nhất 6 kí tự.</div>
		                </div>
		                <div class="nhap-lai-mat-khau">
		                    <div class="ten-input">Nhập lại mật khẩu <span style="color: #f61d1d">(*)</span></div>
		                    <input value="<c:out value='<%=currentUser.getMatKhau()%>'/>" placeholder="Nhập lại mật khẩu" type="password" name="nhap-lai-mat-khau" id="nhap-lai-mat-khau" />
		                    <i class="fa-solid fa-eye" style="top: 53%" id="con-mat-2"></i>
		                </div>
                	</div>
            		
            		<input type="hidden" name="co-yeu-cau-xoa-anh" id="co-yeu-cau-xoa-anh"/>
                	
                	<div class="phan-anh">
                		<div class="anh-dai-dien">
	                		<input type="file" name="anh" style="display: none" id="upload-file" accept="image/*"/>
	                		<img alt="" src="<c:out value='<%=currentUser.getAnh()%>'/>" id="anh-upload"/>
	                		<div class="doi-anh-dai-dien"><span style="margin: auto">Đổi ảnh đại diện</span></div>
                		</div>
                		<div class="go-anh-dai-dien"><span style="margin: auto">Gỡ ảnh đại diện</span></div>
                		<div class="chup-anh">
                			<i class="fa-solid fa-camera" style="margin: auto"></i>
                			<span style="margin: auto">Chụp ảnh</span>
                		</div>
                	</div>
                </div>
                <div style="display: flex">
	                <button class="nut-dang-ki">Cập nhật</button>
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
    <script src="./js/register.js"></script>
    <script src="./js/webcam.js"></script>
	<script src="./js/webcam_utils.js"></script>
    
    <script type="text/javascript">
    	var updateSuccess = layCookie('update_success', 0);
	    document.querySelector('.quay-lai-trang-chu').onclick = () => {
	    	themCookie('update_success', 0, 0, 60, '/fruitshop/profile');
	    	document.querySelector('.quay-lai-trang-chu').firstElementChild.click();
	    }
		//document.querySelector('.than-website').style.display = 'flex';
		//anhUpload.style.height = document.querySelector('.anh-dai-dien').offsetWidth.toString() + 'px';
		try {
			document.querySelector('.thong-bao').style.display = 'flex';
			if (done == 0) {
				setTimeout(() => {
    	    	    document.querySelector('.thong-bao-ve-viec').style.display = 'flex';
    	    	}, 600);
			}
		}
		catch (exception) {}
    </script>
    
    <c:if test="${thongBaoCapNhatThanhCong == 1}">
    	<script type="text/javascript">
    		if (updateSuccess == 0) {
		    	window.addEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
    		}
    		else {
    			document.querySelector('.thong-bao').style.display = 'none';
    			window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
    			document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
    		}
	    	document.querySelector('#nut-ok').onclick = () => {
	    		themCookie('update_success', 1, 3600000 * 24, 60, '/fruitshop/profile');
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