<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- link -->
<%@include file="/resources/module/link.jsp" %>
<!-- JQ -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
</head>
<body>
<h1>Rest Test Form</h1>

<button onclick=test()>TEST</button>

<script>
	function test(){
		let param = {"id" : "1234","name" : "홍길동"};
		$.ajax({
			type : "POST",												//Method
			url : "${pageContext.request.contextPath}/Rest/PostReq",	//요청 URL
			async : true,												//비동기 유무
			data : JSON.stringify(param),								//파라미터
			dataType : "json",											//파라미터 데이터 타입
			contentType : "application/json;charset=UTF-8",				//요청시 헤더 정보
			success : function(result){
				console.log(result);
			},	//성공시에 처리할 로직
			error : function(error){}		//실패시에 처리할 로직
		});
	}
</script>

</body>
</html>


