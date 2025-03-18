function wordck(){
	//var w = "010123-45678";
	var w = "01012345678";
	
	//let ck = /[0-9]/;  //0~9
	//let ck = /[a-zA~Z]/;  //a~z(소문자), A~Z(대문자)
	//console.log(ck.test(w));  //true, false
	
	//let ck = /[0-9]/;  //0~9숫자 외의 단어
	//console.log(w.match(ck));  //배열형태의 값을 출력
	
	let ck1 = /^[0-9]/g;
	let ck2 = /^\d{2,3}-\d{3,4}-\d{4}$/;
	console.log(ck1.test(w));
	console.log(ck2.test(w));
}


function eventok(){
	if(f.ename.value==""){
		alert("고객명을 입력하세요.");
	}
	else if(f.etel.value==""){
		alert("연락처를 입력하세요.");
	}
	else if(f.email.value==""){
		alert("이메일를 입력하세요.");
	}
	else if(f.ememo.value==""){
		alert("이벤트 참여이유를 입력하세요.");
	}
	else if(f.info1.checked==false){
		alert("개인벙보활용에 동의 하셔야만 이벤트에 참여가 됩니다.");
	}
	else if(f.info2.checked==false){
		alert("제3자 정보제공에 동의 하셔야만 이벤트에 참여가 됩니다.");
	}
	else{
		//정규식 코드 사용 (연락처 확인)
		//^:문자열의 시작, \d{10,11}:숫자(0-9)가 10~11개 등장, $:문자열의 끝
		let ck = /^010\d{8}$/; 
		if(ck.test(f.etel.value) == false){
			alert("전화번호를 정상적으로 입력하세요.")
		}
		else{
			f.submit();
		}
	}
}