<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<header>
		<div class="top-header">
			<ul>
				<li>${authdto.email} 님환영합니다.</li>
				<li><a href="javascript:void(0)">나의정보</a></li>
				<li><a href="${pageContext.request.contextPath}/logout">로그아웃</a></li>
				<li><a href="javascript:kakaoLogout()">카카오로그아웃</a></li>
			</ul>
		</div>
		<nav>
			<ul>
				<li><a href="javascript:void(0)">회사소개</a></li>
				<li><a href="${pageContext.request.contextPath}/notice/list.do">공지사항</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list.do">자유게시판</a></li>
				<li><a href="javascript:void(0)">ETC</a></li>

			</ul>
		</nav>
	</header>