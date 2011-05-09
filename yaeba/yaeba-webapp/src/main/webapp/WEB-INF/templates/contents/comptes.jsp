<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="accounts.title" /> </a>
	</h2>
	<p class="meta">
		<spring:message code="accounts.subtitle" />
	</p>
	<div class="entry">
		<p>
			<spring:message code="accounts.text" />
		</p>

		<c:choose>
			<c:when test="${not empty utilisateur.comptes}">
				<table>
					<tr class="libelle">
						<td><spring:message code="accounts.account" /></td>
						<td><spring:message code="accounts.label" /></td>
						<td><spring:message code="accounts.balance" /></td>
					</tr>

					<c:set var="compteur" value="0" />
					<c:forEach var="compte" items="${utilisateur.comptes}" begin="0" end="9">
						<tr class="ligne_${compteur}">
							<td class="numero"><a
								href="${pageContext.request.contextPath}/user/comptes/${compte.numeroCompte}/${annee}/${mois}/details.html">${compte.numeroCompte}</a>
							</td>
							<td class="numero">${compte.libelle}</td>
							<td><fmt:formatNumber value="${compte.soldeCourant}" pattern="#0.00 €" /></td>
						</tr>
						<c:set var="compteur" value="${compteur+1}" />
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="accounts.alt" />
				</p>
			</c:otherwise>
		</c:choose>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>