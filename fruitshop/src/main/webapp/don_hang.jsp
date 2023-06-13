<%@page import="java.util.Date"%>
<%@page import="fruitshop.utils.Pair"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="fruitshop.model.DanhSachChuyenKhoan"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fruitshop.model.DanhSachDonHang"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đơn hàng | Fruit Shop</title>
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/don_hang.css">
    <link rel="stylesheet" href="./css/phan_trang.css">
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
		int choose = (int) request.getAttribute("choose");
	%>

    <jsp:include page="./header.jsp"/>
    <jsp:include page="./load_page.jsp"/>
    
    <c:if test="${huyDonHangStatus == 1 || huyDonHangStatus == 2}">	
	   	<jsp:include page="thong_bao_mini.jsp"/>
	</c:if>
    
    <!-- Than website -->
    <div class="than-website">
        <div class="dieu-huong-va-tieu-de">
            <div class="dieu-huong-don-hang"><a href="./home" id="link-to-trang-chu">Trang chủ</a><i class="fa-solid fa-chevron-right" id="sang-phai" style="font-size: 12px; padding-left: 10px; padding-right: 10px;"></i><div style="color: #a1a1a1; display: inline-block;">Đơn hàng</div></div>
            <div class="tieu-de-cua-hang"><h4>Đơn hàng</h4></div>
        </div>
        
		<style>
			#choose<%=choose%> {
				border: 1px solid #1a94ff;
				color: #0b74e5;
				background-color: #f0f8ff;
			}
			
			#choose<%=choose%> {
				border: 1px solid #1a94ff;
			}
		</style>
		
        <div class="thanh-phan-loai-don-hang">
	    	<a href="./don-hang?choose=0" id="choose0" class="phan-loai-don-hang"><span style="margin: auto;">Tất cả</span></a>
	    	<a href="./don-hang?choose=1" id="choose1" class="phan-loai-don-hang"><span style="margin: auto;">Đang xử lý</span></a>
	    	<a href="./don-hang?choose=2" id="choose2" class="phan-loai-don-hang"><span style="margin: auto;">Đã gửi</span></a>
	    	<a href="./don-hang?choose=3" id="choose3" class="phan-loai-don-hang"><span style="margin: auto;">Đã nhận</span></a>
	    	<a href="./don-hang?choose=4" id="choose4" class="phan-loai-don-hang"><span style="margin: auto;">Đã hủy</span></a>
		</div>		
        
        <c:if test="${soLuongDanhSachDonHang == 0}">
        	<div style="display: flex; flex-direction: column; padding: 18px 0px 60px;">        	
	            <div class="tieu-de-don-hang">Không tìm thấy đơn hàng nào!</div>
	            <div class="thong-tin-chi-tiet-don-hang">
	            	<a href="./home" class="nut-quay-lai-trang-chu_don-hang">            	
		                <span style="font-weight: 600; margin: auto; font-size: 14px;">Trang chủ</span>
	            	</a>
	            </div>
        	</div>    	
        </c:if>
        
        <c:if test="${soLuongDanhSachDonHang > 0}">
		    <%
		    	List<Pair> listPair = (ArrayList<Pair>) request.getAttribute("listPair");
		    	int soLuongDanhSachDonHang = (int) request.getAttribute("soLuongDanhSachDonHang");
			    int soLuongPage = (soLuongDanhSachDonHang % 5 == 0) ? soLuongDanhSachDonHang / 5 : soLuongDanhSachDonHang / 5 + 1;
				int trang = (Integer) request.getAttribute("page");
				
				int pageBegin = trang - (trang - 1) % 5;
				int pageEnd = (pageBegin + 4 > soLuongPage) ? soLuongPage : (pageBegin + 4);
				int prePage = trang - 1;
				int nextPage = trang + 1;
		    %>
		    
		    <div style="width: 85%; margin-left: auto; margin-right: auto;"><div style="margin-bottom: 30px; font-size: 14px; font-weight: 600"><i><span style="color: #f61d1d;">(*)</span> Lưu ý: Bạn chỉ có thể hủy đơn hàng trong lúc đơn hàng đang được nhân viên xử lý</i></div></div>
		    
	        <c:forEach items="<%=listPair%>" var="item">
		        <c:set var="null" value="<%=null%>"/>
		        <div class="dong-don-hang">
		        	<div class="thong-tin-don-hang">        	
				        <div class="ma-don-hang__don-hang tieu-de-bang-don-hang">Mã đơn hàng: <span style="font-size: 16px; font-weight: 300; color: #ff2626; font-weight: 600;">FSDH<span class="ma-don-hang"><c:out value="${item.first.id}"/></span></span></div>
				        <div class="ten-nguoi-nhan__don-hang tieu-de-bang-don-hang">Tên người nhận: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${item.first.tenNguoiNhan}"/></span></div>
				        <div class="dia-chi-nguoi-nhan__don-hang tieu-de-bang-don-hang">Địa chỉ người nhận: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${item.first.diaChiNguoiNhan}"/></span></div>
				        <div class="so-dien-thoai-nguoi-nhan__don-hang tieu-de-bang-don-hang">Số điện người nhận: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${item.first.soDienThoaiNguoiNhan}"/></span></div>
				        <div class="email__don-hang tieu-de-bang-don-hang">Email: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${currentUser.email}"/></span></div>
				        <div class="so-luong-san-pham__don-hang tieu-de-bang-don-hang">Số lượng sản phẩm: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${item.first.soLuongSanPham}"/></span></div>
				        <div class="hinh-thuc-thanh-toan tieu-de-bang-don-hang">Hình thức thanh toán: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${item.first.thanhToan}"/></span></div>
				        
				        <c:set var="tienMat" value='<%="Tiền mặt"%>'/>
				        <c:if test="${item.first.thanhToan.equals(tienMat)}">
					        <div class="so-tai-khoan-nguoi-chuyen__don-hang tieu-de-bang-don-hang">Số tài khoản người chuyển: <span class="thong-tin-invalid">Invalid</span></div>
					        <div class="ngan-hang-nguoi-chuyen__don-hang tieu-de-bang-don-hang">Ngân hàng người chuyển: <span class="thong-tin-invalid">Invalid</span></div>
					        <div class="ngay-chuyen-khoan__don-hang tieu-de-bang-don-hang">Ngày chuyển khoản: <span class="thong-tin-invalid">Invalid</span></div>
				        </c:if>
				        
				        <c:if test="${item.first.thanhToan.equals(tienMat) == false}">
					        <div class="so-tai-khoan-nguoi-chuyen__don-hang tieu-de-bang-don-hang">Số tài khoản người chuyển: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${item.second.soTaiKhoanNguoiChuyen}"/></span></div>
					        <div class="ngan-hang-nguoi-chuyen__don-hang tieu-de-bang-don-hang">Ngân hàng người chuyển: <span class="thong-tin-chi-tiet-tung-muc"><c:out value="${item.second.tenNganHangNguoiChuyen}"/></span></div>
					        <div class="ngay-chuyen-khoan__don-hang tieu-de-bang-don-hang">Ngày chuyển khoản: <span class="thong-tin-chi-tiet-tung-muc"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.second.ngayChuyenKhoan}"/></span></div>
				        </c:if>
				        
				        <div class="ngay-dat__don-hang tieu-de-bang-don-hang">Ngày đặt: <span class="thong-tin-chi-tiet-tung-muc"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.first.ngayXuat}"/></span></div>
				        
				        <c:if test="${item.first.ngayGui == null}">		        
					        <div class="ngay-gui__don-hang tieu-de-bang-don-hang">Ngày gửi: <span class="thong-tin-invalid">Invalid</span></div>
					        <div class="ngay-nhan__don-hang tieu-de-bang-don-hang">Ngày nhận: <span class="thong-tin-invalid">Invalid</span></div>
				        </c:if>
				        
				         <c:if test="${item.first.ngayGui != null && item.first.ngayNhan == null}">			        
					        <div class="ngay-gui__don-hang tieu-de-bang-don-hang">Ngày gửi: <span class="thong-tin-chi-tiet-tung-muc"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.first.ngayGui}"/></span></div>
				        	<div class="ngay-nhan__don-hang tieu-de-bang-don-hang">Ngày nhận: <span class="thong-tin-invalid">Invalid</span></div>
				        </c:if>
				        
				        <c:if test="${item.first.ngayGui != null && item.first.ngayNhan != null}">
				        	<div class="ngay-gui__don-hang tieu-de-bang-don-hang">Ngày gửi: <span class="thong-tin-chi-tiet-tung-muc"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.first.ngayGui}"/></span></div>
					        <div class="ngay-nhan__don-hang tieu-de-bang-don-hang">Ngày nhận: <span class="thong-tin-chi-tiet-tung-muc"><fmt:formatDate pattern="dd/MM/yyyy HH:mm:ss" value="${item.first.ngayNhan}"/></span></div>
				        </c:if>
				        
				        <div class="tong-tien__don-hang tieu-de-bang-don-hang">Tổng tiền: <span style="color: #ff2626;" class="tien-format">${item.first.tongTien}</span> VNĐ</div>
				        
				        <c:if test="${item.first.ngayGui == null && item.first.huy == 0}">			        
					        <div class="trang-thai__don-hang tieu-de-bang-don-hang">Trạng thái: <span style="color: #28a745;">Đang chờ xử lý</span></div>
				        </c:if>
				        
				        <c:if test="${item.first.ngayGui != null && item.first.ngayNhan == null}">			        
					        <div class="trang-thai__don-hang tieu-de-bang-don-hang">Trạng thái: <span style="color: #28a745;">Đang giao hàng</span></div>
				        </c:if>
				        
				        <c:if test="${item.first.ngayNhan != null}">			        
					        <div class="trang-thai__don-hang tieu-de-bang-don-hang">Trạng thái: <span style="color: #28a745;">Đã giao hàng thành công</span></div>
				        </c:if>
				        
				        <c:if test="${item.first.huy == 1}">
					        <div class="trang-thai__don-hang tieu-de-bang-don-hang">Trạng thái: <span style="color: #28a745;">Đã hủy đơn hàng</span></div>
				        </c:if>
		        	</div>
		        	
		        	<div class="user-don-hang">
			        	<div style="margin: auto;">
			        		<img alt="" src="${currentUser.anh}" class="anh-user"/>			    
						    <a class="chi-tiet-don-hang__don-hang" href="./chi-tiet-don-hang?id=${item.first.id}"><span style="margin: auto; font-weight: 600;">Chi tiết đơn hàng</span></a>
			        		
					        <c:if test="${item.first.ngayGui == null && item.first.huy == 0}">
						        <a class="huy-don-hang__don-hang" href="./huy-don-hang"><span style="margin: auto; font-weight: 600;">Hủy đơn hàng</span></a>					        	
					        </c:if>
					        
					        <c:if test="${item.first.huy == 1}">
						        <div class="huy-don-hang__don-hang vo-hieu-hoa"><span style="margin: auto; font-weight: 600;">Đã hủy đơn hàng</span></div>					        	
					        </c:if>
					        
					        <c:if test="${item.first.ngayGui != null && item.first.huy == 0}">
						        <div class="huy-don-hang__don-hang vo-hieu-hoa"><span style="margin: auto; font-weight: 600;">Hủy đơn hàng</span></div>
					        </c:if>
					        
			        	</div>
		        	</div>
		        </div>
		    </c:forEach>
		    
			<!-- phan trang -->
			<c:if test="<%=soLuongDanhSachDonHang > 0%>">
			    <c:if test="<%=soLuongPage > 1%>">
			        <div class="boc-ben-ngoai-phan-trang" style="width: 85%; margin-left: auto; margin-right: auto; margin-top: 40px;">
			            <div class="phan-trang">
			                <c:if test="<%=prePage > 0%>">
			                    <div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
			                        <a href="./don-hang?page=<%=prePage%>&choose=<%=choose%>"></a>
			                        <i class="fa-solid fa-chevron-left" style="margin: auto"></i>
			                    </div>
			                </c:if>
			                <c:forEach begin="<%=pageBegin%>" end="<%=pageEnd%>" var="index">
			                    <c:if test="${index == page}">
			                        <div class="trang-hien-tai thanh-phan-phan-trang">
			                            <a href="./don-hang?page=${index}&choose=<%=choose%>"></a>
			                            <p style="margin: auto; font-weight: 600">${index}</p>
			                        </div>
			                    </c:if>
			                    <c:if test="${index != page}">
			                        <div class="khong-phai-trang-hien-tai thanh-phan-phan-trang">
			                            <a href="./don-hang?page=${index}&choose=<%=choose%>"></a>
			                            <p style="margin: auto; font-weight: 600">${index}</p>
			                        </div>
			                    </c:if>
			                </c:forEach>
			                <c:if test="<%=nextPage <= soLuongPage%>">
			                    <div class="nut-thao-tac-voi-trang thanh-phan-phan-trang">
			                        <a href="./don-hang?page=<%=nextPage%>&choose=<%=choose%>"></a>
			                        <i class="fa-solid fa-chevron-right" style="margin: auto"></i>
			                    </div>
			                </c:if>
			            </div>
			        </div>
			    </c:if>
			</c:if>
        </c:if>
	
    </div>

    <jsp:include page="./footer.jsp"/>
    <script src="./js/common.js"></script>
    <script src="./js/utils.js"></script>
    
    <c:if test="${soLuongDanhSachDonHang > 0}">
    	<script src="./js/don_hang.js"></script>
    </c:if>
    
    <script type="text/javascript">
    	setTimeout(() => {
			document.querySelector('.load-truoc-khi-vao-trang').remove();
			document.querySelector('.than-website').style.display = 'flex';
		}, 1200);
    </script>
    
    <c:if test="${soLuongDanhSachDonHang > 0}">
	    <script type="text/javascript">
		    document.querySelectorAll('.thanh-phan-phan-trang').forEach(item => {
		    	item.onclick = () => {
		    		item.firstElementChild.click();
		    	}
		    });
	    </script>
    </c:if>
    
    <script type="text/javascript">
    	var flag = layCookie('flag', 0);
    	var url = window.location.href;
    </script>
    
    <c:if test="${huyDonHangStatus == 1 || huyDonHangStatus == 2}">
    	<script type="text/javascript">
    		setTimeout(() => {
		    	window.scrollTo(<%=session.getAttribute("x")%>, <%=session.getAttribute("y")%>);
	    	}, 1200);
    	</script>
    </c:if>
    
    <c:if test="${huyDonHangStatus == 1}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.thong-bao-mini').style.borderLeft = '5px solid #f00';
    			document.querySelector('.icon-thong-bao-mini').innerHTML = '<i class="fa-sharp fa-solid fa-circle-xmark" id="infomini-icon"></i>';
    			document.querySelector('#infomini-icon').style.color = '#f00';
    			document.querySelector('.icon-thong-bao-mini').style.color = '#f00';
    			document.querySelector('.noi-dung-thong-bao-mini-1').innerText = 'Thất bại';
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Bạn không thể hủy đơn hàng vì đơn hàng đã được gửi';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${huyDonHangStatus == 2}">
 		<script type="text/javascript">
    		setTimeout(() => {
    			document.querySelector('.noi-dung-thong-bao-mini-2').innerText = 'Bạn đã hủy đơn hàng FSDH${idDanhSachDonHang} thành công';
    		}, 1200);
    	</script>
 	</c:if>
 	
 	<c:if test="${huyDonHangStatus == 1 || huyDonHangStatus == 2}">
    	<script src="./js/thong_bao_mini.js"></script>
    </c:if>
    
    <c:if test='<%=session.getAttribute("huyDonHangStatus") == null%>'>
	    <script type="text/javascript">
	    	setTimeout(() => {
	    		window.scrollTo(0, 0);
	    	}, 1200);
    	</script>
    </c:if>
    
    <%
    	session.removeAttribute("huyDonHangStatus");
    	session.removeAttribute("idDanhSachDonHang");
    %>
    
</body>
</html>