<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
	<c:forEach items="${user}" var="u">
	<tr><td>${u.id},${u.username},${u.password}</td></tr>
	
	</c:forEach>
		
	</table>
	<form action="exportExcel.action" method="post">
		<input type="submit" value="下载列表" onclick="alert(下载成功)">
	</form>

</body>
</html>