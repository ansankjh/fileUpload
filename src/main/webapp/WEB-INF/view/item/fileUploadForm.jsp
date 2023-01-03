<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>fileUpload</title>
	</head>
	<body>
		<h1>상품등록</h1>
		<form action = "${pageContext.request.contextPath}/item/addItem" enctype = "multipart/form-data" method="post">
			<div>
				상품이름 : <input type="text" name="itemName">
			</div>
			<div>
				상품 이미지 : <input type="file" name="filename">
			</div>
			<div>
				<button type="submit">상품등록</button>
			</div>
		</form>
	</body>
</html>