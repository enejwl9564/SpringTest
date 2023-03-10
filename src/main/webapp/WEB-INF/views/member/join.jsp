<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
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
	<%-- <div class="msg">${param.msg}</div> --%>
	<h1>MEMBER JOIN</h1>
	<form:form modelAttribute="memberDto" id="joinfrm" name="joinfrm" action="${pageContext.request.contextPath}/member/save" method="post" onsubmit="return false">
	
		<div style="font-size:0.5rem;color:red;text-align:left;margin-bottom:0px;"><form:errors path="email" /></div>
		<input type="text" name="email" placeholder="example@example.com" class="form-control" />
		<div style="font-size:0.5rem;color:red;text-align:left;margin-bottom:0px;"><form:errors path="pwd" /></div>
		<input type="password" name="pwd"  placeholder="Insert Password" class="form-control" />
		<input type="text" name="birth"  placeholder="Insert BirthDay" class="form-control" />
		<div class="row" style="margin-bottom:0px;" id="SMSAuth">
			<div class="col-8">
				<input type="text" name="phone"  placeholder="-?????? ???????????????" class="form-control" />
			</div>
			<div class="col" style="text-align:right">
				<button class="btn btn-secondary" onclick="ReqSMS1('${pageContext.request.contextPath}')">????????????</button>	
			</div>	
		</div>
		
		
		<div class="row" style="margin-bottom:0px;">
			<div class="col-8">
				<input type="text" name="zipcode"  placeholder="??????????????? ???????????????" class="form-control" />
			</div>
			<div class="col" style="text-align:right">
				<button class="btn btn-secondary" onclick="searchZip()">???????????? ??????</button>	
			</div>	
		</div>
		
		<input type="text" name="addr1"  placeholder="???????????? ??????"  class="form-control" />
		<input type="text" name="addr2"  placeholder="???????????? ??????" class="form-control" />
		<button class="btn btn-secondary" onclick="isValid()">????????????</button>
		<input type="reset" value="?????????" class="btn btn-danger" />
		<a href="${pageContext.request.contextPath}/login" class="btn btn-warning">????????????</a>
	</form:form>
</section>


<script defer>
	const isValid=function(){
		const joinfrm = document.joinfrm;
		alert("[JS] func isValid");
		//email ???????????? ??? Validation Check
		joinfrm.action="${pageContext.request.contextPath}/member/save";
		joinfrm.submit();
	}
</script>

<!-- ???????????? ?????? -->
<script defer src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script defer>
	
	
	const searchZip=function()
	{
	    new daum.Postcode({
	        oncomplete: function(data) {
	            // ???????????? ???????????? ????????? ??????????????? ????????? ????????? ???????????? ???????????????.
	            // ????????? ???????????? ????????? ???????????? ????????? ?????????.
	            let form = document.joinfrm;
	            var addr='';

	            //???????????? ????????? ?????? ??????
	            if(data.userSelectedType==='R')
	            {
	            	addr=data.roadAddress;
	            }
	            else //???????????? ?????? ?????? ?????? 'J'
	            {
	            	addr=data.jibunAddress;
	            }  
	            form.zipcode.value=data.zonecode;
	            form.addr1.value=addr;
	            
	        }
	    }).open();
	}
</script>
<!-- ???????????? ?????? -->


<!-- ???????????? ?????? -->
	<script defer>
		
		
		//--------------------
		const ReqSMS1 = function(path) 
		{
			const joinfrm = document.joinfrm;
			
			if(joinfrm.phone.value.trim()!="")
			{
				$.ajax({
					url : path + "/auth/sms.do", // ????????? ?????? 
					type : 'POST', // ?????? 
					data : {"phone" : joinfrm.phone.value, "step" : "1"}, //???????????? s
					success : function(result) 
					{
						alert("SUCCESS"); 
						let code = "<div>";
						code +="<form action="+path+"/auth/sms.do method='post'  name='confirmform' style=display:flex; onsubmit='return false'>"
						code += "<input type=text name=code placeholder='??????????????????(6??????)' class='form-control me-2' />";
						code += "<input type=hidden name=step value=2 />";
						code += "<button class='btn btn-secondary w-25' style='font-size:0.8rem' onclick=ReqSMS2('"+path+"')>????????????</button>";
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
				alert("??????????????? ???????????????");	
				joinfrm.phone.focus();
			}
			
	
		}
		
		const ReqSMS2 = function(path) 
		{	
			const confirmform = document.confirmform;
		 
			if(confirmform.code.value.trim()!="")
			{
				$.ajax({
					url : path + "/auth/sms.do", // ????????? ?????? 
					type : 'POST', // ?????? 
					data : {"code" : confirmform.code.value, "step" : "2"}, //???????????? s
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
				alert("Code??? ???????????????");	
				confirmform.phone.focus();
			}
			
	
		}
	</script>

</body>
</html>