<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDate"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="../../css/common_admin.css">
<link rel="stylesheet" href="../../css/top_5_khach_hang.css">
<link rel="icon" type="image/x-icon" href="../img/shop_icon.ico">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<div class="tieu-de-chuc-nang">
	<span style="margin: auto;">Top 5 khách hàng</span>
</div>

<div class="chon-tieu-chi">
	<div class="truong-gia-tri">
		<div class="ten-truong"><span class="nam gia-tri-duoc-chon">-- Chọn tiêu chí --</span><i class="fa-solid fa-chevron-down chon-icon"></i></div>
		<div class="bang-gia-tri">
			<div class="gia-tri">Tổng chi tiêu</div>
			<div class="gia-tri">Số lượng đã mua</div>
		</div>
	</div>
</div>

<table class="bang-ket-qua">
	<thead>
		<tr>
			<th class="tieu-de-bang-ket-qua tieu-de-stt">STT</th>
			<th class="tieu-de-bang-ket-qua tieu-de-id">ID</th>
			<th class="tieu-de-bang-ket-qua tieu-de-hinh-anh">Hình ảnh</th>
			<th class="tieu-de-bang-ket-qua tieu-de-ten">Tên khách hàng</th>
			<th class="tieu-de-bang-ket-qua tieu-de-don-gia">Email</th>
			<th class="tieu-de-bang-ket-qua tieu-de-tieu-chi">Số lượng đã mua</th>
		</tr>
	</thead>
	
	<tbody></tbody>
</table>

<div id="csrf-token" style="display: none;"><%=session.getAttribute("csrfToken")%></div>

<script src="../../js/top_5_khach_hang.js"></script>