<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/* 
	현재 코드는 "동작"은 되지만, 대형 프로젝트나 실무에서는 절대 권장하지 않는 방식
	추후 DB 커넥션 누수 / 서버 과부하 / 유지보수 어려움 발생 가능성 큼
	*/
	PreparedStatement ps = (PreparedStatement)request.getAttribute("ps");
	//ResultSet rs = (ResultSet)request.getAttribute("rs");
	ResultSet rs = ps.executeQuery();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이벤트 등록 사용자 리스트 페이지</title>
</head>
<body>
<%
	while(rs.next()){
%>
이벤트 참가자명^^ : <%=rs.getString("ename")%><br>
<%
	}
	rs.close();
	ps.close();
%>
</body>
</html>