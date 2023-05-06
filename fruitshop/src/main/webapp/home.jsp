<%@page import="fruitshop.model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="icon" type="image/x-icon" href="./img/shop_icon.ico">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>
<body>
	<jsp:include page="header.jsp"/>
    <!-- than website -->
    <div class="than-website">
		<jsp:include page="load_page.jsp"/>
    	<div class="phan-duoc-hien-thi">
		    <!-- Thong bao dang nhap truoc khi thao tac voi san pham -->
		    <!-- <div class="buoc-phai-dang-nhap">
		        <div class="thong-bao-buoc-phai-dang-nhap">
		            <div class="tieu-de-thong-bao-buoc-phai-dang-nhap"><h1 style="margin: auto;"><i class="fa-solid fa-circle-info" id="info-icon"></i> Thông báo</h1></div>
		            <div class="noi-dung-thong-bao-buoc-phai-dang-nhap">
		                <div class="doan-van-thong-bao-buoc-phai-dang-nhap">
		                    <p class="chi-tiet-doan-van-thong-bao-buoc-phai-dang-nhap">Bạn chưa đăng nhập</p>
		                    <p class="chi-tiet-doan-van-thong-bao-buoc-phai-dang-nhap">Hãy đăng nhập để tiếp tục</p>
		                </div>
		                <div class="bang-nut-thao-tac">
		                    <a href="./login.html" id="link-dang-nhap"><h3 id="goto-dang-nhap">Đăng nhập</h3></a>
		                    <div id="nut-huy-bo"><h3 id="huy-bo-thong-bao">Hủy bỏ<h3></div>
		                </div>
		            </div>
		        </div>
		    </div> -->
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
	        <h2 class="than-website tieu-de-ban-chay-nhat">Bán chạy nhất</h2>
	        <div class="than-website ban-chay-nhat">
	        	<%List<SanPham> listSanPhamOderBySoLuongBan = (List<SanPham>) request.getAttribute("listSanPhamOderBySoLuongBan");%>
	        	<div class="dong-san-pham">
		        	<c:forEach items="<%=listSanPhamOderBySoLuongBan%>" var="sp" begin="0" end="3">
		        		<div class="san-pham" title="${sp.ten}">
		        			<a href="./chi-tiet-san-pham?id=${sp.id}" class="link-to-chi-tiet-san-pham"></a>
			        		<img alt="" src="${sp.anh}" class="anh-san-pham"/>
			        		<h3 class="ten-san-pham">${sp.ten}</h3>
			        		<div class="so-sao-va-so-luong-da-ban">${sp.soSaoVote}<i class="fa-solid fa-star" id="sao"></i> | Đã bán ${sp.soLuongBan}</div>
			        		<h3 class="gia-san-pham">${sp.tienTrenDonVi} VNĐ<p style="color: #3d464d; display: inline">/${sp.donVi}</p></h3>
				        	<div class="tuy-chon-san-pham">
				        		<form action="./them-vao-gio-hang?id=${sp.id}" method="post" style="display: flex; margin-left: auto; margin-right: auto;">
				        			<input type="hidden" name="so-luong" value="1">
				        			<button class="them-vao-gio-hang"><p style="margin: auto">Thêm vào giỏ</p></button>
				        		</form>
				        		<a class="mua-ngay" href="./mua-ngay"><p style="margin: auto">Mua ngay</p></a>
				        	</div>
		        		</div>
		        	</c:forEach>
	        	</div>
	        	<div class="dong-san-pham">
		        	<c:forEach items="<%=listSanPhamOderBySoLuongBan%>" var="sp" begin="4" end="7">
		        		<div class="san-pham" title="${sp.ten}">
		        			<a href="./chi-tiet-san-pham?id=${sp.id}" class="link-to-chi-tiet-san-pham"></a>
			        		<img alt="" src="${sp.anh}" class="anh-san-pham"/>
			        		<h3 class="ten-san-pham">${sp.ten}</h3>
			        		<div class="so-sao-va-so-luong-da-ban">${sp.soSaoVote}<i class="fa-solid fa-star" id="sao"></i> | Đã bán ${sp.soLuongBan}</div>
			        		<h3 class="gia-san-pham">${sp.tienTrenDonVi} VNĐ<p style="color: #3d464d; display: inline">/${sp.donVi}</p></h3>
			        		<div class="tuy-chon-san-pham">
				        		<form action="./them-vao-gio-hang?id=${sp.id}" method="post" style="display: flex; margin-left: auto; margin-right: auto;">
				        			<input type="hidden" name="so-luong" value="1">
				        			<button class="them-vao-gio-hang"><p style="margin: auto">Thêm vào giỏ</p></button>
				        		</form>
				        		<a class="mua-ngay" href="./mua-ngay"><p style="margin: auto">Mua ngay</p></a>
				        	</div>
		        		</div>
		        	</c:forEach>
	        	</div>
	        </div>
	        <h2 class="than-website tieu-de-co-the-ban-thich">Có thể bạn thích</h2>
	        <div class="than-website co-the-ban-thich">
	        	<%List<SanPham> listSanPhamOrderBySoSao = (List<SanPham>) request.getAttribute("listSanPhamOrderBySoSao");%>
	        	<div class="dong-san-pham">
		        	<c:forEach items="<%=listSanPhamOrderBySoSao%>" var="sp" begin="0" end="3">
		        		<div class="san-pham" title="${sp.ten}">
		        			<a href="./chi-tiet-san-pham?id=${sp.id}" class="link-to-chi-tiet-san-pham"></a>
			        		<img alt="" src="${sp.anh}" class="anh-san-pham"/>
			        		<h3 class="ten-san-pham">${sp.ten}</h3>
			        		<div class="so-sao-va-so-luong-da-ban">${sp.soSaoVote}<i class="fa-solid fa-star" id="sao"></i> | Đã bán ${sp.soLuongBan}</div>
			        		<h3 class="gia-san-pham">${sp.tienTrenDonVi} VNĐ<p style="color: #3d464d; display: inline">/${sp.donVi}</p></h3>
				        	<div class="tuy-chon-san-pham">
				        		<form action="./them-vao-gio-hang?id=${sp.id}" method="post" style="display: flex; margin-left: auto; margin-right: auto;">
				        			<input type="hidden" name="so-luong" value="1">
				        			<button class="them-vao-gio-hang"><p style="margin: auto">Thêm vào giỏ</p></button>
				        		</form>
				        		<a class="mua-ngay" href="./mua-ngay"><p style="margin: auto">Mua ngay</p></a>
				        	</div>
		        		</div>
		        	</c:forEach>
	        	</div>
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
    </div>
	<jsp:include page="footer.jsp"/>
    <script src="./js/common.js"></script>
    <script src="./js/home.js"></script>
    <script src="./js/san_pham.js"></script>
</body>
</html>