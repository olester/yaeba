<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="details.title" /> </a>
	</h2>
	<p class="meta">
		<spring:message code="details.subtitle" />
	</p>
	<div class="entry">
		<p>
			<spring:message code="details.text" />
		</p>
		<c:choose>
			<c:when test="${not empty compte.operations}">
				<form action="${pageContext.request.contextPath}/user/comptes/${compte.numeroCompte}/choix.html" method="POST">
					<spring:message code="details.filter.txt" />
					<select name="mois" onchange="submit()">
						<c:forEach items="1,2,3,4,5,6,7,8,9,10,11,12" var="numMois">
							<option <c:if test="${numMois==mois}">selected</c:if> value="${numMois}">
								<spring:message code="details.month.${numMois}" />
							</option>
						</c:forEach>
					</select> <select name="annee" onchange="submit()">
						<c:forEach items="2010,2011" var="numAnnee">
							<option <c:if test="${numAnnee==annee}">selected</c:if> value="${numAnnee}">${numAnnee}</option>
						</c:forEach>
					</select>
					<noscript>
						<input type="submit" />
					</noscript>
				</form>
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="details.alt" />
				</p>
			</c:otherwise>
		</c:choose>
		<br />
		<c:choose>
			<c:when test="${not empty compte.getOperationsByDate(annee, mois)}">
				<p>
					<spring:message code="details.text2" />
					<spring:message code="details.month.${mois}" />
					${annee} :
				</p>
				<table>
					<tr class="libelle">
						<td><spring:message code="details.date" /></td>
						<td><spring:message code="details.label" /></td>
						<td><spring:message code="details.amount" /></td>
					</tr>

					<c:set var="compteur" value="0" />
					<c:forEach var="operation" items="${compte.getOperationsByDate(annee, mois)}" begin="0" end="9">
						<tr class="ligne_${compteur}">
							<td><fmt:formatDate value="${operation.dateCreation}" pattern="dd/MM/yyyy" />
							</td>
							<td>${operation.libelle}</td>
							<td><fmt:formatNumber value="${operation.montant}" pattern="#0.00 €" />
							</td>
						</tr>
						<c:set var="compteur" value="${compteur+1}" />
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="details.alt2" />
				</p>
			</c:otherwise>
		</c:choose>

		<br /> <br />
		<p>
			<a href="${pageContext.request.contextPath}/user/comptes.html"><spring:message code="details.back" /> </a>
		</p>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>