<%@page import="fruitshop.model.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fruitshop.model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trang chủ | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/home.css">
    <link rel="stylesheet" href="./css/san_pham.css">
    <link rel="stylesheet" href="./css/thong_bao.css">
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />	

	<style type="text/css">
		.than-website-duoc-hien-thi {
			display: none;
		}
	</style>
	
	<c:if test="${thongBaoDangKiThanhCong == 1}">
		<style type="text/css">
			body {
    			--scrollbar-width: 0px;
			}
		</style>
	</c:if>

</head>
<body>
	<jsp:include page="header.jsp"/>
 	<jsp:include page="load_page.jsp"/>
 	
 	<c:if test="${thongBaoDangKiThanhCong == 1}">
	    <div class="thong-bao">
	        <div class="thong-bao-ve-viec" style="height: 230px">
	            <div class="tieu-de-thong-bao"><h1 style="margin: auto;"><i class="fa-solid fa-circle-info" id="info-icon"></i> Thông báo</h1></div>
	            <div class="noi-dung-thong-bao">
	                <div class="doan-van-thong-bao" style="margin-bottom: 20px">
	                    <p class="chi-tiet-doan-van-thong-bao">Bạn đã đăng kí tài khoản thành công</p>
	                </div>
	                <div class="bang-nut-thao-tac">
	                    <div id="nut-ok" style="display: flex;"><h3 style="margin: auto;">OK</h3></div>
	                </div>
	            </div>
	        </div>
	    </div>
	</c:if>
	
	<c:if test="${themGioHangStatus == 1 || themGioHangStatus == 2 || themGioHangStatus == 3}">	
	   	<jsp:include page="thong_bao_mini.jsp"/>
	</c:if>
   
    <!-- than website -->
    <div class="than-website than-website-duoc-hien-thi">
        <div class="than-website anh-truot">
            <div class="nut-chuyen-slide nut-truoc"><i class="fa-solid fa-chevron-left"></i></div>
            <div class="dai-anh-truot">
                <img src="./img/dau_tay_slide_show.jpg" class="anh-truot-hien-tai" alt=""/>
                <!-- <img src="./img/cam_slide_show.jpg" class="anh-truot-hien-tai" alt=""/> -->
                <!-- <img src="./img/tao_slide_show.jpg" class="anh-truot-hien-tai" alt=""/> -->
            </div>
            <div class="nut-chuyen-slide nut-sau"><i class="fa-solid fa-chevron-right"></i></div>
            <div class="danh-sach-nut-nho">
                <div class="nut-nho"></div>
                <div class="nut-nho"></div>
                <div class="nut-nho"></div>
            </div>
        </div>
        <%
        	List<SanPham> listSanPham = new ArrayList<>();
        	int soLuongDongSanPham = 0;
        %>
        <h2 class="than-website tieu-de-ban-chay-nhat">Bán chạy nhất</h2>
        <div class="than-website ban-chay-nhat">
        	<%
        		listSanPham = (List<SanPham>) request.getAttribute("listSanPhamOderBySoLuongBan");
        		soLuongDongSanPham = (listSanPham.size() % 4 == 0) ? listSanPham.size() / 4 : listSanPham.size() / 4 + 1;
        		pageContext.setAttribute("listSanPham", listSanPham, PageContext.REQUEST_SCOPE);
        	%>
        	<jsp:include page="./single_page_san_pham.jsp"/>
        </div>
        <h2 class="than-website tieu-de-co-the-ban-thich">Có thể bạn thích</h2>
        <div class="than-website co-the-ban-thich">
        	<%
        		listSanPham = (List<SanPham>) request.getAttribute("listSanPhamOrderBySoSao");
        		soLuongDongSanPham = (listSanPham.size() % 4 == 0) ? listSanPham.size() / 4 : listSanPham.size() / 4 + 1;
        		pageContext.setAttribute("listSanPham", listSanPham, PageContext.REQUEST_SCOPE);
        	%>
        	<jsp:include page="./single_page_san_pham.jsp"/>
        </div>
        <h2 class="than-website tieu-de-tin-tuc">Tin tức và sức khỏe</h2>
        <div class="than-website tin-tuc">
            <div class="tin-tuc-trai-cay">
                <div class="anh-tin-tuc">
                    <div class="lam-mo-anh-tin-tuc"></div>
                    <a href="./news-fruit" style="margin: auto;">
                        <img src="./img/tin_tuc_trai_cay_demo1.jpg" style="width: 199.38px; height: 300px;" alt="">
                    </a>
                </div>
                <div class="bang-tin-tuc">
                    <div class="ngay-dang-tin-tuc">30/05/2020</div>
                    <div style="margin-top: 8px;">
                        <a href="./news-fruit" class="link-tieu-de-tin-tuc">Cherry vàng - nữ hoàng của mọi loại Cherry</a>
                    </div>
                    <div class="tom-tat-noi-dung-tin-tuc">Cherry vàng sở dĩ được gọi là "nữ hoàng" vì độ hiếm, cũng như công đoạn chăm sóc lẫn thu hoạch đòi hỏi rất cao và cầu kỳ. Công đoạn bảo quản cũng vô cùng nghiêm ngặt và phải đạt tiêu chuẩn cao hơn các loại cherry khác. Với vỏ màu vàng kết hợp với màu đỏ tươi dễ phân biệt, vị ngọt ngon khó cưỡng, không ngoa khi nói cherry vàng là loại cherry ngon nhất thế giới</div>
                    <div class="nut-chuyen-huong-den-trang-tin-tuc">
                        <a href="./news-fruit" style="text-decoration: none; color: #3D464D; font-size: 14px; font-weight: 600; margin: auto; display: flex; width: 100%; height: 100%;">
                            <div style="margin: auto;">Đọc thêm</div>
                        </a>
                    </div>
                </div>
            </div>
            <div class="tin-tuc-suc-khoe">
                <div class="anh-tin-tuc">
                    <div class="lam-mo-anh-tin-tuc"></div>
                    <a href="./news-health" style="margin: auto;">
                        <img src="./img/tin_tuc_suc_khoe_demo1.jpg" style="width: 450px; height: 300px;" alt="">
                    </a>
                </div>
                <div class="bang-tin-tuc">
                    <div class="ngay-dang-tin-tuc">26/05/2020</div>
                    <div style="margin-top: 8px;">
                        <a href="./news-health" class="link-tieu-de-tin-tuc">☀️Tiết trời ẩm ương, tăng cường đề kháng 💦</a>
                    </div>
                    <div class="tom-tat-noi-dung-tin-tuc">🤧 Thời điểm giao mùa là lúc mọi người dễ mắc bệnh (cảm lạnh, viêm xoang, đau họng, nhức đầu). Đặc biệt là những người có sức đề kháng và hệ miễn dịch yếu. Để giải quyết tình trạng này, bạn nên sử dụng các nguyên liệu tự nhiên có sẵn trong nhà để tăng đề kháng nhé!</div>
                    <div class="nut-chuyen-huong-den-trang-tin-tuc">
                        <a href="./news-health" style="text-decoration: none; color: #3D464D; font-size: 14px; font-weight: 600; margin: auto; display: flex; width: 100%; height: 100%;">
                            <div style="margin: auto;">Đọc thêm</div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
<!--     </div> -->
	<jsp:include page="footer.jsp"/>
	<script src="./js/utils.js"></script>
    <script src="./js/mua_ngay.js"></script>
    <script src="./js/common.js"></script>
    <script src="./js/home.js"></script>
    <script src="./js/san_pham.js"></script>
    <script src="./js/xu_ly_gio_hang.js"></script>
    
    <script type="text/javascript">
    	var updateSuccessPress = layCookie('updateSuccessPress', 0);
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
			try {
				if (updateSuccessPress == 0) {
					document.querySelector('.thong-bao').style.display = 'flex';
					if (updateSuccessPress == 0) {
						setTimeout(() => {
		    	    	    document.querySelector('.thong-bao-ve-viec').style.display = 'flex';
		    	    	}, 600);
					}
				}
			}
			catch (exception) {}
		}, 1200);
    </script>
    
    <c:if test="${thongBaoDangKiThanhCong == 1}">
    	<script type="text/javascript">
    		if (updateSuccessPress == 0) {
		    	window.addEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
    		}
    		else {
    			document.querySelector('.thong-bao').style.display = 'none';
    			window.removeEventListener('wheel', voHieuHoaCuonChuot, { passive: false });
    			document.querySelector('body').style.setProperty('--scrollbar-width', '15px');
    		}
	    	document.querySelector('#nut-ok').onclick = () => {
	    		themCookie('updateSuccessPress', 1, 3600000 * 24, 60, '/fruitshop/home');
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
    
    <script type="text/javascript">
    	var flag = layCookie('flag', 0);
    </script>
    
    <c:if test="${themGioHangStatus == 1 || themGioHangStatus == 2 || themGioHangStatus == 3}">
    	<script type="text/javascript">
    		setTimeout(() => {
	    		document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Đã thêm sản phẩm ${tenSanPham} vào giỏ hàng';
		    	window.scrollTo(<%=session.getAttribute("x")%>, <%=session.getAttribute("y")%>);
	    	}, 1200);
    	</script>
    </c:if>
    
 	<c:if test="${themGioHangStatus == 1}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.thong-bao-mini').style.borderLeft = '5px solid #f00';
    			document.querySelector('.icon-thong-bao-mini').innerHTML = '<i class="fa-sharp fa-solid fa-circle-xmark" id="infomini-icon"></i>';
    			document.querySelector('#infomini-icon').style.color = '#f00';
    			document.querySelector('.icon-thong-bao-mini').style.color = '#f00';
    			document.querySelector('.noi-dung-thong-bao-mini-1').innerText = 'Thất bại';
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Sản phẩm ${tenSanPham} đã hết hàng';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${themGioHangStatus == 2}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.thong-bao-mini').style.borderLeft = '5px solid #f00';
    			document.querySelector('.icon-thong-bao-mini').innerHTML = '<i class="fa-sharp fa-solid fa-circle-xmark" id="infomini-icon"></i>';
    			document.querySelector('#infomini-icon').style.color = '#f00';
    			document.querySelector('.icon-thong-bao-mini').style.color = '#f00';
    			document.querySelector('.noi-dung-thong-bao-mini-1').innerText = 'Thất bại';
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Sản phẩm ${tenSanPham} bạn chọn có số lượng vượt quá số lượng trong kho';
    		}, 1200);
    	</script>
 	</c:if>
    
    <c:if test="${themGioHangStatus == 1 || themGioHangStatus == 2 || themGioHangStatus == 3}">
    	<script src="./js/thong_bao_mini.js"></script>
    </c:if>

    <c:if test='<%=session.getAttribute("themGioHangStatus") == null%>'>
	    <script type="text/javascript">
	    	setTimeout(() => {
	    		window.scrollTo(0, 0);
	    	}, 1200);
    	</script>
    </c:if>
 
    <%
    	session.removeAttribute("themGioHangStatus");
    	session.removeAttribute("soLuong");
    	session.removeAttribute("x");
		session.removeAttribute("y");
		session.removeAttribute("url");
		session.removeAttribute("id");
    %>
    
</body>
</html>