<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="welcome.title" /></a>
	</h2>
	<p class="meta"><spring:message code="welcome.subtitle" /></p>
	<div class="entry">
		<p>
			<spring:message code="welcome.text" />
		</p>
	</div>
</div>
<div style="clear: both;">&nbsp;</div>