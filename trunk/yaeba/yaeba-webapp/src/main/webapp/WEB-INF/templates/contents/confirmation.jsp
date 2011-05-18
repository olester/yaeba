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
				'${virementCommand.compteEmetteurLibelle}'
				<spring:message code="transfers.confirmation.txt2" />
				'${virementCommand.compteRecepteurLibelle}'
				<spring:message code="transfers.confirmation.txt3" />
				<fmt:formatNumber value="${virementCommand.montant}" pattern="#0.00 EUR" />
				. <br>
			</p>
			<input type="submit" value="<spring:message code='transfers.submit'/>" /> &nbsp; <a href="virements.html"
				style="text-decoration: none;"><input type="button" value="<spring:message code='transfers.cancel'/>" /> </a>
		</form>
	</div>
</div>