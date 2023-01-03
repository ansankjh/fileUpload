<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "java.io.*" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>action</title>
	</head>
	<body>
		<h1>application/x-www-form-urlencoded</h1>
		<div>urlId : <%=request.getParameter("urlId")%></div>
		<div>urlId : <%=request.getParameter("urlFile")%></div>
		
		
		
		
		<h1>multipart/form-data</h1>
		<div>request inputStream : <%=request.getInputStream()%></div>
		<%
			InputStream is = request.getInputStream();
			int i = 0;
			while((i = is.read()) != -1) {
				System.out.print((char)i);
			}
		%>
	</body>
</html>