<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="cr" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>과정 개설 리스트</title>
</head>
<body>
<p>개설된 과목 갯수 : ${ea}</p>
<!-- DTO에 있는 변수명으로 JSTL로 출력하는 형태 -->
<cr:forEach var="cdata" items="${classList}">
과정명 : ${cdata.getClass_name()}, 
강사명 : ${cdata.getClass_teacher()}, 
수강료 : ${cdata.getClass_price()}
<input type="button" value="수정" onclick="macbook_modify('${cdata.getMidx()}')">
<input type="button" value="삭제" onclick="macbook_del('${cdata.getMidx()}')">
<br>
</cr:forEach>
<!--
<form id="frm" method="post">
<input type="hidden" name="midx" value="">
</form>
-->
<div id="msg">
</div>
</body>
<script>
function macbook_modify(n){
	var m = document.getElementById("msg");
	m.innerHTML= `<form id="frm" method="post" action="./macbook_modity.do">
		          <input type="hidden" name="midx" value="`+n+`">
	              </form>`;
	frm.submit();
	
	//POST(form) : 해킹에 취약
	//frm.midx.value = n;
	//frm.action = "./macbook_modity.do";
	//frm.submit();
	
	//GET : 해킹에 취약 (GET은 2가지 방법 : location.href, form)
	//location.href='./macbook_modify.do?midx='+n;
}

function macbook_del(n){
	if(confirm('해당 과정을 삭제 하시겠습니까? \n 삭제시 데이터 복구되지 않습니다.')){
		var m = document.getElementById("msg");
		m.innerHTML= `<form id="frm" method="post" action="./macbook_delete.do">
			          <input type="hidden" name="midx" value="`+n+`">
		              </form>`;
		frm.submit();	
	
		//frm.midx.value = n'
		//frm.action = "./macbook_delete.do";
		//frm.submit();
	}
}
</script>
</html>