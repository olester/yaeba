<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="sidebar">
	<ul>
		<li><p>
				<sec:authorize access="isAuthenticated()">
					<spring:message code="sidebar.login" /> ${utilisateur.prenom} ${utilisateur.nom}
		<br />
					<a href='<c:url value="/j_spring_security_logout" />'><spring:message
							code="sidebar.logout" /> </a>
				</sec:authorize>
			</p></li>
	</ul>

</div>