<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- link -->
<%@include file="/resources/module/link.jsp" %>
<!-- css -->
<link rel="stylesheet" type="text/css" href="resources/css/main.css">
<!-- js -->


</head>
<body>

	<!-- header -->
	<%@include file="/resources/module/header.jsp" %>
	
	
	<section class="container">
		<!-- 메시지 -->
		<div class="msg">${msg}</div>
		<!-- 페이지경로표시 -->
		<div>
			<a href="${pageContext.request.contextPath}/main.do"> <i
				class="bi bi-house-door"></i>
			</a> > 회사소개
		</div>

		<h1>오시는길</h1>
		 
			<button onclick="test()" class="btn btn-warning">주소검색API</button>
			<div id='map' style="width: 100%; height: 350px;"></div>
		 


	</section>
 

 


	<!-- 다음주소 API -->
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

	<script>
		var addr='';		//전역변수로 사용
		<!-- 다음 시작  -->
		function test(){
		new daum.Postcode({
		oncomplete : function(data) {
		 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
		 // 예제를 참고하여 다양한 활용법을 확인해 보세요.
		
		 //사용자가 도로명 주소 선택
		 if(data.userSelectedType==='R')
		 {
		 	addr=data.roadAddress;
		 	test2(addr);
		 }
		 else //사용자가 지번 주소 선택 'J'
		 {
		 	addr=data.jibunAddress;
		 	test2(addr);
		 }         
		}	
		}).open(); 
		}
	</script>

	<!-- 다음 끝  -->


	<!-- 카카오 주소로위치 찾기  -->
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=ca12c0d4f6ea096e99d25d2d2c9b7c77&libraries=services"></script>


	<script>
	//test 시작
	function test2(addr)
	{
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
		 center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		 level: 3 // 지도의 확대 레벨
		};  

		//지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
	
		//주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
	
		//주소로 좌표를 검색합니다
		geocoder.addressSearch(addr, function(result, status) {
	
		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {
	
			 var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			
			 // 결과값으로 받은 위치를 마커로 표시합니다
			 var marker = new kakao.maps.Marker({
					     map: map,
					     position: coords
					 });
			
					 // 인포윈도우로 장소에 대한 설명을 표시합니다
					 var infowindow = new kakao.maps.InfoWindow({
					     content: '<div style="width:150px;text-align:center;padding:6px 0;">'+addr+'</div>'
					 });
					 infowindow.open(map, marker);
			
					 // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
					 map.setCenter(coords);
				} 
			});    

	//test 끝
	}
</script>


<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
 <script>
		Kakao.init('ca12c0d4f6ea096e99d25d2d2c9b7c77'); //발급받은 키 중 javascript키를 사용해준다.
		console.log(Kakao.isInitialized()); // sdk초기화여부판단

		//카카오로그아웃  
		function kakaoLogout(path) {
			if (Kakao.Auth.getAccessToken()) {
				Kakao.API.request({
					url : '/v1/user/unlink',
					success : function(response) {
						console.log("RESPONSE : " + response)
						location.href = path+"/auth/logout.do"
					},
					fail : function(error) {
						console.log(error)

					},
				})
				Kakao.Auth.setAccessToken(undefined)
			}
		}
 </script>


</body>
</html>