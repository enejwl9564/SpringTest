<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- link -->
<%@include file="/resources/module/link.jsp" %>
<!-- css -->
<link rel="stylesheet" type="text/css" href="resources/css/login.css">
 
</head>
<body>
<section class="container">
	<div class="msg">${param.msg}</div>
	<h1>LOGIN</h1>
	<form:form modelAttribute="loginDto" name="loginfrm" action="${pageContext.request.contextPath}/login"  method="post">
	 
		<div style="font-size:0.5rem;color:red;text-align:left;margin-bottom:0px;"><form:errors path="email" /></div>
		<input type="text" name="email" placeholder="example@example.com"  value="${cookie.email.value}" class="form-control" />
		<div style="font-size:0.5rem;color:red;text-align:left;margin-bottom:0px;"><form:errors path="pwd" /></div>
		<input type="password" name="pwd"  placeholder="Insert Password"  class="form-control" />
		<div style="text-align:left;margin:0px;">
			<input type="checkbox" name="rememberId"  class="form-check-input" id="chk" /> <label for="chk">Remember ID</label>
		</div>
		<button class="btn btn-primary w-100">로그인</button>
		<a class="w-100 btn btn-secondary" href="javascript:kakaoLogin()">카카오로그인</a>
		<a href=<c:url value="/member/join" /> >회원가입</a>
		<a href="javascript:void(0)">아이디분실</a>
		<a href="${pageContext.request.contextPath}/auth/resetpwd.do">패스워드분실</a>
	</form:form>
</section>


<!-- 카카오 스크립트 -->
<script  src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script defer>
Kakao.init('ca12c0d4f6ea096e99d25d2d2c9b7c77'); //발급받은 키 중 javascript키를 사용해준다.
console.log(Kakao.isInitialized()); // sdk초기화여부판단
//카카오로그인
function kakaoLogin() {
    Kakao.Auth.login({
		scope:'profile_nickname,profile_image, account_email, gender',
		success: function (response) {
        //console.log(response);
    	Kakao.API.request({
        url: '/v2/user/me',
        success: function (resp) {
        	
        	const kakao_account = resp.kakao_account;
        		console.log(resp);
  	 
        		
        	  //	location.href="main.jsp?email="+kakao_account.email;
        	  	
        	  let params="email="+kakao_account.email+"&";
        	  	params+= "gender="+kakao_account.gender+"&";
        	  	params+="profile_image="+kakao_account.profile.profile_image_url;
  			  	alert("${pageContext.request.contextPath}/auth/kakaologin.do?"+params);
        	 	location.href="${pageContext.request.contextPath}/auth/kakaologin.do?"+params;
        	  	
          },
          fail: function (error) {
            console.log(error)
          },
        })
      },
      fail: function (error) {
        console.log(error)
      },
    })
  }
</script>

</body>
</html>