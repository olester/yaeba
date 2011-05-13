<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="details.title" /> </a>
	</h2>
	<p class="meta">${compte.libelle} <spring:message code="details.cc.spec" /></p>
	<div class="entry">
		<p>
			<spring:message code="details.text" />
		</p>
		<c:choose>
			<c:when test="${not empty listeOperations && ((page-1)*10)>listeOperations.size()}">
				<p>
					<spring:message code="details.alt3" />
				</p>
			</c:when>
			<c:when test="${not empty listeOperations && ((page-1)*10)<listeOperations.size()}">
				<p>
					<spring:message code="details.cc.text2" />
					<spring:message code="details.month.${mois}" />
					${annee} :
				</p>
				<table>
					<tr class="libelle">
						<td><spring:message code="details.date" /></td>
						<td><spring:message code="details.label" /></td>
						<td style="width: 160px;"><spring:message code="details.amount" /></td>
					</tr>

					<c:set var="compteur" value="0" />
					<c:forEach var="operation" items="${listeOperations}" begin="${(page-1)*10}" end="${page*10-1}">
						<tr class="ligne_${compteur%2}">
							<td><c:choose>
									<c:when test="${locale=='en'}">
										<joda:format value="${operation.dateCreation}" pattern="MM/dd/yyyy" />
									</c:when>
									<c:otherwise>
										<joda:format value="${operation.dateCreation}" pattern="dd/MM/yyyy" />
									</c:otherwise>
								</c:choose>
							</td>
							<td><spring:message code="details.${operation.discriminator}" /> ${operation.libelle}</td>
							<td <c:if test="${operation.montant<0}">style="text-align:left;"</c:if>><fmt:formatNumber
									value="${operation.montant}" pattern="#0.00 €" />
							</td>
						</tr>
						<c:set var="compteur" value="${compteur+1}" />
					</c:forEach>
				</table>
				<br />
				<div class="previous">
					<c:if test="${page>1}">
						<a
							href="${pageContext.request.contextPath}/user/comptes/${compte.numeroCompte}/${annee}/${mois}/${page-1}/cc-details.html">&lt;
							<spring:message code="details.previous" /> </a>
					</c:if>
				</div>
				<div class="next">
					<c:if test="${page*10<listeOperations.size()}">
						<a
							href="${pageContext.request.contextPath}/user/comptes/${compte.numeroCompte}/${annee}/${mois}/${page+1}/cc-details.html"><spring:message
								code="details.next" /> &gt;</a>

					</c:if>
				</div>
				<br />
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="details.cc.alt2" />
				</p>
			</c:otherwise>
		</c:choose>

		<br /> <br />
		<p>
			<a href="${pageContext.request.contextPath}/user/comptes/${compte.numeroCompte}/${annee}/${mois}/1/details.html"><spring:message code="details.cc.back" /> </a>
		</p>
		<p>
			<a href="${pageContext.request.contextPath}/user/comptes.html"><spring:message code="details.back" /> </a>
		</p>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>