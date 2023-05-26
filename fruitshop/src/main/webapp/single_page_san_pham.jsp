<%@page import="java.util.ArrayList"%>
<%@page import="fruitshop.model.SanPham"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	List<SanPham> listSanPham = (ArrayList<SanPham>) pageContext.getAttribute("listSanPham", PageContext.REQUEST_SCOPE);
	int soLuongDongSanPham = (listSanPham.size() % 4 == 0) ? listSanPham.size() / 4 : listSanPham.size() / 4 + 1;
	int listSanPhamSize = listSanPham.size();
%>
<c:set var="listSanPhamSize" value="<%=listSanPham.size()%>"/>
<c:forEach begin="1" end="<%=soLuongDongSanPham%>" var="i">
	<div class="dong-san-pham">
       	<c:forEach items="<%=listSanPham%>" var="sp" begin="${(i - 1) * 4}" end="${((i - 1) * 4 + 3 <= listSanPhamSize - 1) ? (i - 1) * 4 + 3 : listSanPhamSize - 1}">
       		<div class="san-pham" title="${sp.ten}">
       			<a href="./chi-tiet-san-pham?id=${sp.id}" class="link-to-chi-tiet-san-pham"></a>
        		<img alt="" src="${sp.anh}" class="anh-san-pham"/>
        		<h3 class="ten-san-pham">${sp.ten}</h3>
        		<c:if test="${sp.soSaoVote < 0}">
	        		<div class="so-sao-va-so-luong-da-ban">Chưa có đánh giá | Đã bán ${sp.soLuongBan}</div>
        		</c:if>
        		<c:if test="${sp.soSaoVote >= 0}">
        			<div class="so-sao-va-so-luong-da-ban">${sp.soSaoVote}<i class="fa-solid fa-star" id="sao"></i> | Đã bán ${sp.soLuongBan}</div>
        		</c:if>
        		<h3 class="gia-san-pham">${sp.tienTrenDonVi} VNĐ<p style="color: #3d464d; display: inline">/${sp.donVi}</p></h3>
	        	<div class="tuy-chon-san-pham">
	        		<!-- <form action="./them-vao-gio-hang?id=${sp.id}" method="post" style="display: flex; margin-left: auto; margin-right: auto;">
	        			<input type="hidden" name="so-luong" value="1">
	        			<button class="them-vao-gio-hang"><p style="margin: auto">Thêm vào giỏ</p></button>
	        		</form>-->
	        		<a class="them-vao-gio-hang" href="./xu-ly-gio-hang?id=${sp.id}"><p style="margin: auto">Thêm vào giỏ</p></a>
	        		<a class="mua-ngay" href="./mua-ngay"><p style="margin: auto">Mua ngay</p></a>
	        	</div>
       		</div>
       	</c:forEach>
	</div>
</c:forEach>