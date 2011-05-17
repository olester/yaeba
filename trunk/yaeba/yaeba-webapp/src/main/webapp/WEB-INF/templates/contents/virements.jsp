<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="transfers.title" /> </a>
	</h2>
	<p class="meta">
		<spring:message code="transfers.subtitle" />
	</p>
	<div class="entry">

		<form:form action="validateur.html" commandName="virements">
			<p>
				<spring:message code="transfers.txt" />
				<br />
				<form:select path="compteEmetteur">
					<form:options items="${comptes}" itemValue="id" itemLabel="libelle" />
				</form:select>
				<br />
				<br />
				<spring:message code="transfers.txt2" />
				<br />
				<form:select path="compteRecepteur">
					<form:options items="${comptes}" itemValue="id" itemLabel="libelle" />
				</form:select>
				<br />
				<br />
				<spring:message code="transfers.montant" />
				<br />
				<form:input path="montant" />
				<br /> <br /> <input type="submit" value="<spring:message code='transfers.submit' />" /> <br />
				<c:if test="${not empty message}">
					<div style="color: red;">
						<spring:message code="${message}" />
					</div>
					<br />
				</c:if>
			</p>
		</form:form>
		<p>
			<a href="${pageContext.request.contextPath}/user/virements/historique.html"><spring:message code="histo.link" />
			</a>
		</p>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>