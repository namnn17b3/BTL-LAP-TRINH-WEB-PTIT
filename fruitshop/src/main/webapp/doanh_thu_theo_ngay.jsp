<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="../css/common_admin.css">
<link rel="stylesheet" href="../css/doanh_thu_theo_ngay.css">
<link rel="icon" type="image/x-icon" href="../img/shop_icon.ico">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<div class="tieu-de-chuc-nang">
	<span style="margin: auto;">Doanh thu theo ngày</span>
</div>

<div class="chon-thang-nam">
	<div class="truong-gia-tri">
		<div class="ten-truong"><span class="nam gia-tri-duoc-chon">Năm</span><i class="fa-solid fa-chevron-down chon-icon"></i></div>
		<div class="bang-gia-tri">
			<c:forEach begin="2018" end="<%=LocalDate.now().getYear()%>" var="index">
				<div class="gia-tri">${index}</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="truong-gia-tri">
		<div class="ten-truong"><span class="thang gia-tri-duoc-chon">Tháng</span><i class="fa-solid fa-chevron-down chon-icon"></i></div>
		<div class="bang-gia-tri">
			<c:forEach begin="1" end="12" var="index">
				<div class="gia-tri">${index}</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="nut-tao-bieu-do"><span style="margin: auto;">Tạo biểu đồ</span></div>
</div>

<canvas></canvas>

<script src="../js/chart.js"></script>
<script src="../js/doanh_thu_theo_ngay.js"></script>