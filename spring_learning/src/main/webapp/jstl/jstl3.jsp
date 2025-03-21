<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//String data[] = new String[]{"홍길동","강감찬","이순신"};
	//request.setAttribute("data", data);
	
	ArrayList<String> data = new ArrayList();
	data.add("hong1");
	data.add("kim2");
	data.add("park3");
	request.setAttribute("data", data);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초3 (반복문 - foreach)</title>
</head>
<body>
<table border="1">
<tr>
<!-- forEach : 반복문
 
begin : 시작값
end : 종료값
 -->
<%-- forEach 기본형태 구조
<cr:forEach var="z" begin="1" end="5">
<td>${z}</td>
</cr:forEach>
--%>
<%-- 
 items : 배열값을 받는 속성 => ${data} : setAttribute의 값
 <%=data%> : jsp 변수명
 
 배열값을 사용하여 출력하는 역할 bebin="0" 으로 시작해야 함 (배열번호 0~)
--%>
<cr:forEach var="z" items="${data}" begin="0" end="2">
<cr:if test="${z != 'kim2'}">
<td>${z}</td>
</cr:if>
</cr:forEach>
</tr>
</table>
</body>
</html>