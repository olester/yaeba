<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="login.title" /></a>
	</h2>
	<div class="entry">
		<form id="loginForm" action="<c:url value='j_spring_security_check'/>"
			method="POST">

			<div>
				<p>
					<spring:message code="login.login" /> : <br /> <input type="text" name="j_username"
						id="j_username" />
				</p>
				<p>
					<spring:message code="login.password" /> : <br /> <input type="password" name="j_password"
						id="j_password" />
				</p>
				<p>
					<input type="submit" value="<spring:message code="login.submit" />" /> <input type="reset" value="<spring:message code="login.reset" />" />
				</p>
			</div>
		</form>

		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
			<font color="red"> <spring:message code="login.error" /> </font>
<!-- 			<br /> <br /> -->
<%-- 				<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. --%>
			<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
		</c:if>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>