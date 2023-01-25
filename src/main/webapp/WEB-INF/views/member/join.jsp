<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BS5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- BSICON -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<!-- JQ -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

<style>
.container{width : 500px; margin:100px auto; text-align:center; position:relative;}
.container *{margin-bottom:10px;}
.container .msg{ position:absolute;left:0px;right:0px;top:-15px; margin:auto; font-size:0.5rem; color:red; padding-left:15px;}
</style>


</head>
<body>
<section class="container">
	<div class="msg">${param.msg}</div>
	<h1>MEMBER JOIN</h1>
	<form id="joinfrm" name="joinfrm" action="${pageContext.request.contextPath}/member/save" method="post" onsubmit="return false">
		<input type="text" name="email" placeholder="example@example.com" class="form-control" />
		<input type="password" name="pwd"  placeholder="Insert Password" class="form-control" />
		<input type="text" name="birth"  placeholder="Insert BirthDay" class="form-control" />
		<div class="row" style="margin-bottom:0px;" id="SMSAuth">
			<div class="col-8">
				<input type="text" name="phone"  placeholder="-없이 입력하세요" class="form-control" />
			</div>
			<div class="col" style="text-align:right">
				<button class="btn btn-secondary" onclick="ReqSMS1('${pageContext.request.contextPath}')">문자인증</button>	
			</div>	
		</div>
		
		
		<div class="row" style="margin-bottom:0px;">
			<div class="col-8">
				<input type="text" name="zipcode"  placeholder="우편번호를 입력하세요" class="form-control" />
			</div>
			<div class="col" style="text-align:right">
				<button class="btn btn-secondary" onclick="searchZip()">우편번호 검색</button>	
			</div>	
		</div>
		
		<input type="text" name="addr1"  placeholder="기본주소 입력"  class="form-control" />
		<input type="text" name="addr2"  placeholder="상세주소 입력" class="form-control" />
		<button class="btn btn-secondary" onclick="isValid()">회원가입</button>
		<input type="reset" value="초기화" class="btn btn-danger" />
		<a href="${pageContext.request.contextPath}/auth/login.do" class="btn btn-warning">이전으로</a>
	</form>
</section>


<script defer>
	const isValid=function(){
		const joinfrm = document.joinfrm;
		alert("[JS] func isValid");
		//email 공백여부 등 Validation Check
		
		joinfrm.submit();
	}
</script>

<!-- 우편번호 검색 -->
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer>
	
	
	const searchZip=function()
	{
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
	            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
	            let form = document.joinfrm;
	            var addr='';

	            //사용자가 도로명 주소 선택
	            if(data.userSelectedType==='R')
	            {
	            	addr=data.roadAddress;
	            }
	            else //사용자가 지번 주소 선택 'J'
	            {
	            	addr=data.jibunAddress;
	            }  
	            form.zipcode.value=data.zonecode;
	            form.addr1.value=addr;
	            
	        }
	    }).open();
	}
</script>
<!-- 우편번호 검색 -->


<!-- 문자인증 요청 -->
	<script defer>
		
		
		//--------------------
		const ReqSMS1 = function(path) 
		{
			const joinfrm = document.joinfrm;
			
			if(joinfrm.phone.value.trim()!="")
			{
				$.ajax({
					url : path + "/auth/sms.do", // 읽어올 문서 
					type : 'POST', // 방식 
					data : {"phone" : joinfrm.phone.value, "step" : "1"}, //파라미터 s
					success : function(result) 
					{
						alert("SUCCESS"); 
						let code = "<div>";
						code +="<form action="+path+"/auth/sms.do method='post'  name='confirmform' style=display:flex; onsubmit='return false'>"
						code += "<input type=text name=code placeholder='인증코드입력(6자리)' class='form-control me-2' />";
						code += "<input type=hidden name=step value=2 />";
						code += "<button class='btn btn-secondary w-25' style='font-size:0.8rem' onclick=ReqSMS2('"+path+"')>인증요청</button>";
						code += "</form>"
						code += "</div>";
						$('#SMSAuth').append(code);
						
					},
					error : function(request, status, error) {
						alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
					}
				});
				
			}
			else{
				alert("전화번호를 입력하세요");	
				joinfrm.phone.focus();
			}
			
	
		}
		
		const ReqSMS2 = function(path) 
		{	
			const confirmform = document.confirmform;
		 
			if(confirmform.code.value.trim()!="")
			{
				$.ajax({
					url : path + "/auth/sms.do", // 읽어올 문서 
					type : 'POST', // 방식 
					data : {"code" : confirmform.code.value, "step" : "2"}, //파라미터 s
					success : function(result) 
					{
					
						$(document.confirmform).remove();
						$('#joinfrm').append(result);
						
					},
					error : function(request, status, error) {
						alert("code:" + request.status + "\n" + "message:"
								+ request.responseText + "\n" + "error:"
								+ error);
					}
				});
				
			}
			else
			{
				alert("Code를 입력하세요");	
				confirmform.phone.focus();
			}
			
	
		}
	</script>

</body>
</html>