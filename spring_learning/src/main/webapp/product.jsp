<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품등록 페이지</title>
</head>
<body>
<!-- bbb.do -> 일반 void 메서드로 전송 -->
<form id="frm" method="post" action="./ccc.do">
상품명 : <input type="text" name="pdnm">
<button type="button" onclick="pdin()">등록</button>
</form>
</body>
<script>
function pdin() {
	frm.submit();
}
</script>
</html>