<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div class="post">
	<h2 class="title">
		<tiles:useAttribute id="key" name="title" />
		<a href="/"><tiles:insertAttribute name="errorcode" /> - ${key}</a>
	</h2>
	<p class="meta">
		<spring:message code="${key}" />
	</p>
	<div class="entry">
		<p>
			<strong><a href="${pageContext.request.contextPath }/welcome.html"><spring:message code="error.back" />
			</a> </strong>
		</p>
	</div>
</div>
<div style="clear: both;">&nbsp;</div>