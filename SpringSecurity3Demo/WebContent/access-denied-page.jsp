<%@page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPEHTMLPUBLIC"-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>accessDenied.jsp</title>
<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
</script>
</head>
<body>
	<h3>当前为accessDenied.jsp 页面!</h3>
	sorry 访问被拒绝 .
	<br />
	
	<c:url value="/j_spring_security_logout" var="logoutUrl" />
	${logoutUrl }
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<button type="submit" >退出</button>
		<!-- onclick="javascript:formSubmit()" -->
	</form>
</body>
</html>