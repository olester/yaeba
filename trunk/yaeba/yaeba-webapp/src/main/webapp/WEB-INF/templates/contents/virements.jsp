<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.6.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/refreshSelectVirements.js"></script>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="transfers.title" /> </a>
	</h2>
	<p class="meta">
		<spring:message code="transfers.subtitle" />
	</p>
	<div class="entry">
		<c:if test="${not empty messageTransferError}">
			<div style="color: red;">
				<spring:message code="${messageTransferError}" />
			</div>
			<br />
		</c:if>
		<c:if test="${not empty messageAccounts}">
			<div style="color: red;">
				<spring:message code="${messageAccounts}" />
			</div>
			<br />
		</c:if>
		<c:if test="${not empty messageAmount}">
			<div style="color: red;">
				<spring:message code="${messageAmount}" />
			</div>
			<br />
		</c:if>
		<c:if test="${not empty messageInsufficient}">
			<div style="color: red;">
				<spring:message code="${messageInsufficient}" />
			</div>
			<br />
		</c:if>

		<spring:hasBindErrors name="virementCommand">
			<c:forEach items="${errors.allErrors}" var="error">
				<div style="color: red;">
					<spring:message code="${error.defaultMessage}" />
				</div>
				<br />
			</c:forEach>
		</spring:hasBindErrors>

		<form:form commandName="virementCommand">
			<p>
				<spring:message code="transfers.txt" />
				<br />
				<form:select path="compteEmetteur" id="compteEmetteur">
					<form:option value="">
						<spring:message code="transfers.labelcomptevide" />
					</form:option>
					<form:options items="${comptes}" itemValue="id" itemLabel="libelle" />
				</form:select>
				<br /> <br />
				<spring:message code="transfers.txt2" />
				<br />
				<form:select path="compteRecepteur" id="compteRecepteur">
					<form:option value="">
						<spring:message code="transfers.labelcomptevide" />
					</form:option>
					<form:options items="${comptes}" itemValue="id" itemLabel="libelle" />
				</form:select>
				<br /> <br />
				<spring:message code="transfers.montant" />
				<br />
				<form:input path="stringMontant" value="0.0" />
				<br /> <br /> <input type="submit" value="<spring:message code='transfers.submit' />" /> <br />
			</p>
		</form:form>
		<p>
			<a href="${pageContext.request.contextPath}/user/virements/historique.html"><spring:message code="histo.link" />
			</a>
		</p>

	</div>
</div>