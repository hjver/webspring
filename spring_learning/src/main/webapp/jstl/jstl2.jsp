<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 기초2(if문)</title>
</head>
<body>
<!-- jstl if문 -->
<cr:if test="${5 < 10}" var="result"> <!-- result : true, false 결과 -->
</cr:if>
${result}<br>

<cr:set var="a" value="30"/>
<fmt:parseNumber value="${a}" type="number" var="aa" />
${aa}
<cr:set var="b" value="200"/>
<fmt:parseNumber value="${b}" type="number" var="bb" />
${bb}
<cr:if test="${aa > bb}">
a값이 큽니다.
</cr:if>
<cr:if test="${aa < bb}">
b값이 큽니다.
</cr:if>
</body>
</html>
<br><br><br>
<!--  eq(==), ne(!=), lt(<), gt(>), le(<=), ge(>=), or(||), and(&&) -->
<%-- <cr:set var="product" value="그래픽카드"/> --%>
<cr:set var="product" value="그래픽카드"/>
<cr:set var="product2" value="모니터"/>
<cr:if test="${product == '그래픽카드' || product2 == '모니터'}">
가격미정
</cr:if>
<br><br><br>
<!-- choose, when, otherwise -->
<cr:set var="agree" value="Y"/>
<!-- 조건문을 설정하는 choose 속성 -->
<cr:choose>
<cr:when test="${agree == 'Y'}"> <!-- if -->
약관에 동의 하였음
</cr:when>
<cr:when test="${agree == 'N'}"> <!-- else if -->
약관에 동의안함
</cr:when>
<cr:otherwise> <!-- else -->
해당 약관정보를 확인 못함
</cr:otherwise>
</cr:choose>

