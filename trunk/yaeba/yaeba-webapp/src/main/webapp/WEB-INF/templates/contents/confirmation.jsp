<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="transfers.title" /> </a>
	</h2>
	<p class="meta">
		<spring:message code="transfers.confirmation.title" />
	</p>
	<div class="entry">
		<form action="save.html">
			<p>
				<spring:message code="transfers.confirmation.txt1" />
				'${em}'
				<spring:message code="transfers.confirmation.txt2" />
				'${rcpt}'
				<spring:message code="transfers.confirmation.txt3" />
				<fmt:formatNumber value="${montant}" pattern="#0.00 EUR" />
				. <br>
			</p>
			<input type="submit" value="<spring:message code='transfers.submit'/>"> <input type="button"
				value="<spring:message code='transfers.cancel'/>">
		</form>
	</div>
</div>
<div style="clear: both;">&nbsp;</div>