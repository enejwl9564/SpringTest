<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- JQ -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

</head>
<body>
<h1>Show File</h1>

<h2>UploadDir : ${uploadDir} </h2>
	<c:forEach var="dir" items="${uploadDir.listFiles() }"> 
		<hr>	
		[Folder] ${dir.getPath() }<br>
		 
		<hr>
		<c:forEach var="file" items="${dir.listFiles()}">
			<a href="javascript:void(0)" data-dir="${dir.getPath()}" data-file="${file.getName()}">${file.getName() }</a> <br>
		</c:forEach>
		
	</c:forEach>
</body>
</html>