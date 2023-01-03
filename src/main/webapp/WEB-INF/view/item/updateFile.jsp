<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>updateFile</title>
	</head>
	<body>
		<h1>updateFile</h1>
		<!--
			<form action = "${pageContext.request.contextPath}/item/updateFile" enctype = "multipart/form-data" method="post">
		  -->
		<form action="${pageContext.request.contextPath}/item/updateFile" enctype="multipart/form-data" method="post">
			<input type="hidden" name="">
			<table border="1">
				<tr>
					<th>itemNo</th>
					<th>itemName</th>
					<th>fileName</th>
				</tr>
				<c:forEach var="m" items="${m}" varStatus="s">
					<tr>
						<td>
							<input type="text" name="itemName" value="${m.itemName}"> 
						</td>
						<td>
							<img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="200" height="200">
							<input type="file" name="fileName" value="${m.fileName}">
						</td>
					</tr>
				</c:forEach>
			</table>
			<div>
				<button type="submit">수정</button>
			</div>		
		</form>
	</body>
</html>