<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>formEnctype</title>
	</head>
	<body>
		<!-- action으로 문자열을 넘김 -->
		<form action="./action.jsp"
			 enctype="application/x-www-form-urlencoded" method="post"><!-- get/post 상관없음 -->
			<input type="text" name="urlId">
			<input type="file" name="urlFile">
			<button type="submit">application/x-www-form-urlencoded</button>
		</form>
		<br>
		<!-- multipart는 기계어 ,바이너리(이진수)를 보낸다. request.getParameter로 못받는다. request.getInputStream()이걸로 받아야한다. -->
		<form action="./action.jsp"
			 enctype="multipart/form-data" method="post">
			<input type="text" name="multiId">
			<input type="file" name="multiFile">
			<button type="submit">multipart/form-data</button>
		</form>
	</body>
</html>