<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- link -->
<%@include file="/resources/module/link.jsp" %>
</head>
<body>
<h1>ERROR PAGE</h1>

발생예외 : ${ex}<br>
예외메시지 : ${ex.message }<br>
<c:forEach items="${ex.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</body>
</html>