<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="com.fasterxml.jackson.databind.ObjectWriter"%>
<%@page import="fruitshop.dto.ErrorResponseDto"%>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	ErrorResponseDto jsonObject = (ErrorResponseDto) request.getAttribute("jsonObject");
	ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	String json = ow.writeValueAsString(jsonObject);
%>
<%=json%>