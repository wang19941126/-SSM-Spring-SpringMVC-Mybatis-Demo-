<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" language="JavaScript"
	src="jquery-1.11.0.js">
	
</script>
</head>
<body onload="load()">
	<form action="getUserById.action" method="post">
		id<input type="text" name="id"> <input type="submit"
			onclick="load()">
	</form>

	<form action="getAllUser.action" method="post">
		<input type="submit">
	</form>
	
	



</body>
</html>