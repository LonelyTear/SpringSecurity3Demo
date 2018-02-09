<%@page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPEhtmlPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>logout.jsp</title>
</head>
<body>
	<h3>当前为logout.jsp页面</h3>
	<form action="j_spring_security_check" method="POST">
		<table>
			<tr>
				<td>用户:</td>
				<td><input type='text' name='j_username'></td>
			</tr>
			<tr>
				<td>密码:</td>
				<td><input type='password' name='j_password'></td>
			</tr>
			<tr>
				<td><input name="reset" type="reset"></td>
				<td><input name="submit" type="submit"></td>
			</tr>
		</table>
	</form>


	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<button type="submit">退出</button>
	</form>
</body>
</html>