<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- JQ -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

</head>
<body>
<h1>UploadPage</h1>

<form action="${pageContext.request.contextPath}/upload/reqUpload" method="post" enctype="multipart/form-data" onsubmit="return false">
	<input type="file" name="files" multiple>
	<input type="submit" value="전송">
</form>

<script defer>
	//submit버튼 이벤트 처리
	$("input[type='submit']").on("click",function(e){
		const formdata = new FormData();				// 새로운 form태그 생성
		const inputfiles = $("input[name='files']")		// name=files 검색 inputfiles 연결
		const files = inputfiles[0].files;
		console.log(inputfiles[0].files);
		
		//formdata에 file을 저장
		for(i in files){
			
			if(!isValid(files[i])){
				return ;
			}
			formdata.append("files",files[i]);
			console.log(files[i]); //파일정보 
		}
		console.log("-----------------------------------");
		//formdata에 저장된 file들 확인
		for(let value of formdata.values()){
			console.log(value);
		}
		
		$.ajax({
			url : "${pageContext.request.contextPath}/upload/reqUpload",
			type : "POST",
			data : formdata,
			contentType : false,		//false 로 선언 시 content-type 헤더가 multipart/form-data로 전송되게 함
			processData : false,		//기본값 true(파라미터를 K:V 형태의 값을 Query String으로 전달) 
										//false Query String으로 전달하지 않음. 보통 파일전송시사용
			success : function(result){ alert("파일업로드 성공!");},
			 error:function(request,status,error){
			        //alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     }
		});
		
	})

	//유효성 체크 함수 
	const isValid=function(data){
		const regex = new RegExp("(.*?\.(exe|sh|zip|alz)$)");
		//(.*?\.(exe|sh|zip|alz)$)
		// .*?				- 	파일명형태 (.(애니):문자or숫자or공백중 한문자  ,*:0-n개문자 ? : 없거나 1개인 경우)
		// \.				- . 기호그대로표시
		//(exe|sh|zip|alz)$	- 확장자명	 ($ : 끝나는 문자열을 찾을때사용  | : or연산)
		
				
		//확장자 exe,sh,zip,alz 는 거부 
		if(regex.test(data.name)){
			alert('업로드가 불가능한 파일입니다.');
			return false;
		}
		//파일 사이즈 20Mb 미만
		const maxSize = 1024*1024*20;
		if(data.size>=maxSize){
			alert('20MB 이상인 파일은 업로드할 수 없습니다.');
			return false;
		}
		return true;
	}
	

</script>



</body>
</html>