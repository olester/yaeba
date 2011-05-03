<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%-- <%@ page isELIgnored ="false" %> --%>

<html>
<head>
<title>Login</title>
</head>

<body>
	<h1>Login</h1>

<%-- 	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}"> --%>
<!-- 		<font color="red"> Your login attempt was not successful, try -->
<!-- 			again.<br /> -->
<%-- 		<br /> Reason: <c:out --%>
<%--   				value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. </font> --%>
<%--   				<c:remove var = "SPRING_SECURITY_LAST_EXCEPTION" scope = "session" /> --%>
<%--   	</c:if> --%>

	    <form id="loginForm" action="<c:url value='j_spring_security_check'/>" method="POST">
		<table>
			<tr>
				<td>User:</td>
				<td><input type="text" name="j_username" id="j_username" />
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="j_password" id="j_password" />
				</td>
			</tr>
			<tr>
				<td><input type="checkbox" name="_spring_security_remember_me" />
				</td>
				<td>Don't ask for my password for two weeks!</td>
			</tr>

			<tr>
				<td colspan='2'><input name="submit" type="submit">
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="reset" type="reset">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>