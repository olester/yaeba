<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="transfers.confirmation.title" /></a>
	</h2>
	<div class="entry">
		<p>
		<form action="save.html">
			${virementBean.compteEmetteur}<br>
			${virementBean.compteRecepteur}<br>
			${virementBean.montant}<br>
			<input type="submit" value="<spring:message code='transfers.submit'/>">
			<input type="button" value="<spring:message code='transfers.cancel'/>">
		</form>
		</p>
	</div>
</div>
<div style="clear: both;">&nbsp;</div>