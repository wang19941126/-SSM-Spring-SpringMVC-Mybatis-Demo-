<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	//使用script本身的函数eval将JSON串解析成对象  
	var e = eval('({' + 'employee : ' + '{' + 'firstName: "John",'
			+ 'lastName : "Doe",' + 'employeeNumber : 123,'
			+ 'title : "Accountant"' + '}' + '})');
	alert(e.employee.firstName);
	alert(e.employee.lastName);
	alert(e.employee.employeeNumber);
	alert(e.employee.title);
</script>
</head>
<body>

</body>
</html>