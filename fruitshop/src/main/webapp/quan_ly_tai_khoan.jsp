<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link rel="stylesheet" href="../../css/common_admin.css">
<link rel="stylesheet" href="../../css/quan_ly_tai_khoan.css">
<link rel="icon" type="image/x-icon" href="../img/shop_icon.ico">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />

<div class="tieu-de-chuc-nang">
	<span style="margin: auto;">Quản lý tài khoản</span>
</div>

<div class="tim-kiem-user">
	<input type="text" class="truong-gia-tri" name="query-text" id="query-text" placeholder="Nhập email hoặc username" value="" tabindex="0"/>
	<div class="nut-tim-kiem-user"><i class="fa-solid fa-magnifying-glass" style="margin: auto 10px auto auto;"></i><span style="margin: auto auto auto 0px;">Tìm kiếm</span></div>
	<div class="nut-them-user"><i class="fa-solid fa-square-plus" style="margin: auto 10px auto auto;"></i><span style="margin: auto auto auto 0px;">Thêm user</span></div>
</div>

<div class="thong-tin-thong-ke">
	<span class="tong-so-trang" style="margin-bottom: 10px;">Tổng số trang: <span>1000</span></span>
	<span class="tong-so-ban-ghi">Tổng số bản ghi: <span>1000</span></span>
</div>

<table class="bang-ket-qua">
	<thead>
		<tr>
			<th class="tieu-de-bang-ket-qua tieu-de-stt">STT</th>
			<th class="tieu-de-bang-ket-qua tieu-de-id">ID</th>
			<th class="tieu-de-bang-ket-qua tieu-de-hinh-anh">Hình ảnh</th>
			<th class="tieu-de-bang-ket-qua tieu-de-ten">Tên khách hàng</th>
			<th class="tieu-de-bang-ket-qua tieu-de-don-gia">Email</th>
			<th class="tieu-de-bang-ket-qua tieu-de-thao-tac">Thao tác</th>
		</tr>
	</thead>
	
	<tbody></tbody>
</table>

<div class="wapper-phan-trang">
	<div class="phan-trang">
		<div class="nut-phan-trang nut-prev"><i class="fa-solid fa-chevron-left" style="margin: auto"></i><span style="display: none;"></span></div>
		<div class="nut-phan-trang none-active"><span>1</span></div>
		<div class="nut-phan-trang active"><span>2</span></div>
		<div class="nut-phan-trang none-active"><span>3</span></div>
		<div class="nut-phan-trang none-active"><span>4</span></div>
		<div class="nut-phan-trang none-active"><span>5</span></div>
		<div class="nut-phan-trang nut-next"><i class="fa-solid fa-chevron-right" style="margin: auto"></i><span style="display: none;"></span></div>
	</div>
</div>

<div id="csrf-token" style="display: none;"><%=session.getAttribute("csrfToken")%></div>

<script src="../../js/utils.js"></script>