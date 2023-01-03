<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>itemList</title>
	</head>
	<body>
		<h1>itemList</h1>
		<table border="1">
			<tr>
				<!-- 
					varStatus 변수속성
					${s.inext} 0부터의 순서
				 -->
				<c:forEach var="m" items="${list}" varStatus="s">
					 <c:if test="${s.index != 0 && s.index % 5 == 0}">
					 	</tr><tr>
					 </c:if>
					<td>
						<div><img src="${pageContext.request.contextPath}/upload/${m.fileName}" width="200" height="200"></div>
						<div>${m.itemName}</div>
						<div>
							<a href="${pageContext.request.contextPath}/item/updateFile?itemNo=${m.itemNo}">수정</a>
						</div>
						<div>
							<a href="${pageContext.request.contextPath}/item/deleteFile?itemNo=${m.itemNo}">삭제</a>
						</div>
					</td>
				</c:forEach>
			</tr>		
		</table>
	</body>
</html>