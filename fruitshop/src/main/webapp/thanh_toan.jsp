<%@page import="fruitshop.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thanh toán | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/register.css">
    <link rel="stylesheet" href="./css/thanh_toan.css">
    <link rel="stylesheet" href="./css/thong_bao_mini.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<style type="text/css">
 		.than-website {
 			display: none;
 		}
	</style>
</head>
<body>

	<%
		User currentUser = (User) session.getAttribute("currentUser");
	%>

    <jsp:include page="./header.jsp"/>
    <jsp:include page="./load_page.jsp"/>
    
	<div class="thong-bao-mini" style="display: none; border-left: 5px solid #0571e6; top: 160px; right: 15px;">
		<div class="icon-thong-bao-mini thanh-phan-thong-bao-mini">
			<i class="fa-solid fa-circle-info" id="infomini-icon" style="color: #0571e6;"></i>
		</div>
		<div class="noi-dung-thong-bao-mini thanh-phan-thong-bao-mini" style="margin-right: 15px;">
			<div class="noi-dung-thong-bao-mini-1">Thông báo</div>
			<div class="noi-dung-thong-bao-mini-2">Đơn hàng của bạn sẽ tự động hủy sau 5 phút</div>
		</div>
		<div class="thanh-dem-thoi-gian"></div>
	</div>
    
    <!-- Than website -->
    <div class="than-website" style="height: auto;">
        <form action="./thanh-toan" class="form-dang-ki" method="post" style="height: auto; width: 700px; margin-top: 25px; margin-bottom: 25px;">
            <input type="hidden" name="csrf-token" value="${csrfToken}">
            <div class="noi-dung">
            	<div style="display: flex;">
	                <h3 class="ten-form">Hóa đơn</h3>
            	</div>
                <div class="ten">
                    <div class="ten-input">Tên người nhận <span style="color: #ff0020">(*)</span></div>
                    <input placeholder="Tên người nhận" type="text" name="ten" id="ten"/>
                    <!-- <div class="canh-bao-invalid">
                        <div class="canh-bao">
                            <i class="fa-solid fa-circle-exclamation"></i>
                            <p class="doan-van-canh-bao">Invalid ${item.querySelector('.ten-input').innerText} !</p>
                        </div>
                    </div> -->
                </div>
                <div class="email">
                    <div class="ten-input">Email <span style="color: #ff0020">(*)</span></div>
                    <input value="<c:out value='<%=currentUser.getEmail()%>'/>" type="text" name="email" id="email" readonly style="cursor: not-allowed; user-select: none; caret-color: transparent; background-color: #efece8; color: #c6c3bd;"/>
                </div>
                <div class="mat-khau" style="height: 66px;">
                    <div class="ten-input">Địa chỉ nhận hàng <span style="color: #ff0020">(*)</span></div>
                    <input placeholder="Địa chỉ nhận hàng" type="text" name="dia-chi-nguoi-nhan" id="dia-chi-nguoi-nhan" />
                </div>
                <div class="nhap-lai-mat-khau">
                    <div class="ten-input">Số điện thoại người nhận <span style="color: #ff0020">(*)</span></div>
                    <input placeholder="Số điện thoại người nhận" type="text" name="so-dien-thoai-nguoi-nhan" id="so-dien-thoai-nguoi-nhan" />
                </div>
                <div style="margin-bottom: 24px;">
                	<div class="ten-input" style="margin-bottom: 4px;">Hình thức thanh toán <span style="color: #ff0020">(*)</span></div>
                	<div style="display: flex; margin-bottom: 4px;">
                		<input type="radio" name="hinh-thuc-thanh-toan" value="0" id="tien-mat" style="display: none;">
                		<label for="tien-mat" class="hinh-thuc-thanh-toan">
                			<div class="hinh-thuc-thanh-toan-child"></div>
                		</label>
                		<span>Tiền mặt</span>
                	</div>
                	<div style="display: flex;">
                		<input type="radio" name="hinh-thuc-thanh-toan" value="1" id="chuyen-khoan" style="display: none;">
                		<label for="chuyen-khoan" class="hinh-thuc-thanh-toan">
                			<div class="hinh-thuc-thanh-toan-child"></div>
                		</label>
                		<span>Chuyển khoản</span>
                	</div>
                </div>
                <div class="hinh-thuc-thanh-toan-chuyen-khoan">
                	<div style="margin-bottom: 24px;">
                		<h3>STK: 19036749148018</h3>
                		<h3>Ngân hàng: Techcombank</h3>
                		<h3>Chi nhánh: Hà Nội</h3>
                		<h3>Chủ TK: Nguyễn Ngọc Nam</h3>
                	</div>
	                <div class="so-tai-khoan-nguoi-chuyen">
	                    <div class="ten-input">Số tài khoản của người chuyển <span style="color: #ff0020">(*)</span></div>
	                    <input placeholder="Số tài khoản của người chuyển" type="text" name="so-tai-khoan-nguoi-chuyen" id="so-tai-khoan-nguoi-chuyen" />
	                </div>
	                <div class="ngan-hang-nguoi-chuyen">
	                    <div class="ten-input">Ngân hàng người chuyển <span style="color: #ff0020">(*)</span></div>
	                    <input placeholder="Ngân hàng người chuyển" type="text" name="ngan-hang-nguoi-chuyen" id="ngan-hang-nguoi-chuyen" />
	                </div>
	                <div class="ngay-chuyen-khoan">
	                    <div class="ten-input">Ngày chuyển khoản(dd/MM/yyyy HH:mm:ss) <span style="color: #ff0020">(*)</span></div>
	                    <input placeholder="Ngày chuyển khoản" type="text" name="ngay-chuyen-khoan" id="ngay-chuyen-khoan" />
	                </div>
                </div>
                <div style="font-size: 24px; font-weight: 600; margin-bottom: 24px;">Tổng tiền: <span style="color: #ff2626;" id="thanh-tien" class="tien-format">${tongTien}</span> VNĐ</div>
                <div style="margin-bottom: 24px; font-size: 14px; font-weight: 600"><i><span style="color: #f61d1d;">(*)</span> Lưu ý: Điền đầy đủ thông tin để chúng tôi xác minh đơn hàng cho bạn</i></div>
                <c:if test="${sttValidation == 1}">
                	<div style="font-size: 16px; margin-bottom: 24px; color: #f61d1d">Tên không được để trống</div>
                </c:if>
                <c:if test="${sttValidation == 2}">
                	<div style="font-size: 16px; margin-bottom: 24px; color: #f61d1d">Không được thay đổi email sau khi đã đăng kí</div>
                </c:if>
                <c:if test="${sttValidation == 3}">
                	<div style="font-size: 16px; margin-bottom: 24px; color: #f61d1d">Địa chỉ người nhận không được để trống</div>
                </c:if>
                <c:if test="${sttValidation == 4}">
                	<div style="font-size: 16px; margin-bottom: 24px; color: #f61d1d">Số điện thoạicó định dạng không đúng</div>
                </c:if>
                <c:if test="${sttValidation == 5}">
                	<div style="font-size: 16px; margin-bottom: 24px; color: #f61d1d">Số tài khoản người chuyển có định dạng không đúng</div>
                </c:if>
                <c:if test="${sttValidation == 6}">
                	<div style="font-size: 16px; margin-bottom: 24px; color: #f61d1d">Tên ngân hàng người chuyển không được để trống</div>
                </c:if>
                <c:if test="${sttValidation == 7}">
                	<div style="font-size: 16px; margin-bottom: 24px; color: #f61d1d">Sai định dạng ngày tháng</div>
                </c:if>
                <button class="nut-dang-ki">Thanh toán</button>
            </div>
        </form>
    </div>

    <jsp:include page="./footer.jsp"/>
    
    <script src="./js/utils.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/thanh_toan.js"></script>
    
    <c:choose>    
	    <c:when test='<%=session.getAttribute("lanDauTruyCap") == null%>'>
	    	<%
	    		session.setAttribute("lanDauTruyCap", 1);
	    	%>
	    	<script type="text/javascript">
	    		var time = 299;
	    	</script>
	    </c:when>
	    
	    <c:otherwise>
	    	<script type="text/javascript">
	    		var time = layCookie('time', 299);
	    	</script>
	    </c:otherwise>
    </c:choose>
    
    <script type="text/javascript">
	    setTimeout(() => {
	    	window.scrollTo(0, 0);
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
			document.querySelector('.thong-bao-mini').style.display = 'flex';
			
			if (time <= 0) {
				time = 299;
			}
			var doDaiThanhDemThoiGianBanDau = 378;
			var phut = parseInt(time / 60).toString();
			var giay = parseInt(time % 60).toString();
			if (phut.length < 2) {
				phut = '0' + phut;
			}
			if (giay.length < 2) {
				giay = '0' + giay;
			}
			noiDungThongBaoMini2.innerText = 'Đơn hàng của bạn sẽ tự động hủy sau ' + phut + ':' + giay;
			var doDaiThanhDemThoiGian = time * doDaiThanhDemThoiGianBanDau / 299;
			thanhDemThoiGian.style.width = parseFloat(doDaiThanhDemThoiGian).toString() + 'px';
			time--;
			themCookie('time', time, 3600000 * 24, 60, '/fruitshop/thanh-toan');
			var interval = setInterval(() => {
				if (time == 0) {
					clearInterval(interval);
				}
				phut = parseInt(time / 60).toString();
				giay = parseInt(time % 60).toString();
				if (phut.length < 2) {
					phut = '0' + phut;
				}
				if (giay.length < 2) {
					giay = '0' + giay;
				}
				noiDungThongBaoMini2.innerText = 'Đơn hàng của bạn sẽ tự động hủy sau ' + phut + ':' + giay;
				
				doDaiThanhDemThoiGian = time * doDaiThanhDemThoiGianBanDau / 299;
				thanhDemThoiGian.style.width = parseFloat(doDaiThanhDemThoiGian).toString() + 'px';
				time--;
				themCookie('time', time, 3600000 * 24, 60, '/fruitshop/thanh-toan');
			}, 1000);
		}, 1200);
    </script>
</body>
</html>