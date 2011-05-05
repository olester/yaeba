<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="post">
	<h2 class="title">
		<a href="/"><spring:message code="error.text" /> - ${error_code}</a>
	</h2>
	<p class="meta"><spring:message code="error-${error_code}.text" /></p>
	<div class="entry">
		<p>
			<strong><a href="${pageContext.request.contextPath }/welcome.html"><spring:message code="error.back" /></a>
			</strong>
		</p>
	</div>
</div>
<div style="clear: both;">&nbsp;</div>