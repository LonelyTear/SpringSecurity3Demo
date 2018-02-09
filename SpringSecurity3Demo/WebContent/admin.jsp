<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPEHTMLPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>admin.jsp</title>
</head>
<body>
	<h3>当前为admin.jsp页面</h3>
	欢迎来到管理员页面.
	<br>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<button type="submit">退出</button>
	</form>
</body>
</html>
