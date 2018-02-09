<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPEHTMLPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>index.jsp</title>
</head>
<body>
	<h3>当前为index.jsp页面</h3>
	<br>

	<a href="admin.jsp">点击进入admin页面</a>
	<br />
	<a href="other.jsp">点击进入其它页面</a>

	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<button type="submit">退出</button>
	</form>
</body>
</html>
