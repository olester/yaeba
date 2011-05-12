<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:useAttribute id="key" name="title" /> <spring:message code="${key}" /></title>
<link href="${pageContext.request.contextPath}/style.css" rel="stylesheet" type="text/css" media="screen" />
</head>
<body>
	<div id="wrapper">
		<tiles:insertAttribute name="header" />
		<sec:authorize access="isAuthenticated()">
			<tiles:useAttribute id="buttonkey" name="button" scope="request" />
		</sec:authorize>
		<tiles:insertAttribute name="menu" />
		<div id="page">
			<div id="content">
				<tiles:insertAttribute name="content" />
			</div>
			<tiles:insertAttribute name="sidebar" />
			<div style="clear: both;">&nbsp;</div>
		</div>
	</div>
	<tiles:insertAttribute name="footer-content" />
	<div id="footer">
		<p>
			(c) 2011 Yaeba. Design by <a href="http://www.nodethirtythree.com">nodethirtythree</a> and <a
				href="http://www.freecsstemplates.org">Free CSS Templates</a>.
		</p>
	</div>
</body>
</html>