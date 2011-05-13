<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="transfers.title" /></a>
	</h2>
	<p class="meta"><spring:message code="inprogress.work" /></p>
	<div class="entry">
		<p>
			<spring:message code="inprogress.unavailable" />
		</p>

		<p>
			<a href="${pageContext.request.contextPath}/user/virements/historique.html"><spring:message code="histo.link" /> </a>
		</p>
		
	</div>
</div>
<div style="clear: both;">&nbsp;</div>