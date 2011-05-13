<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="histo.title" /> </a>
	</h2>
	<div class="entry">
		<c:choose>
			<c:when test="${not empty listeVirements && ((page-1)*10)>listeVirements.size()}">
				<p>
					<spring:message code="details.alt3" />
				</p>
			</c:when>
			<c:when test="${not empty listeVirements && ((page-1)*10)<listeVirements.size()}">
				<p>
					<spring:message code="histo.text" />
				</p>
				<table>
					<tr class="libelle">
						<td><spring:message code="details.date" /></td>
						<td><spring:message code="details.label" /></td>
						<td><spring:message code="histo.transmitter" /></td>
						<td><spring:message code="histo.receiver" /></td>
						<td><spring:message code="details.amount" /></td>
					</tr>

					<c:set var="compteur" value="0" />
					<c:forEach var="virement" items="${listeVirements}">
						<tr class="ligne_${compteur%2}">
							<td><c:choose>
									<c:when test="${locale=='en'}">
										<joda:format value="${virement.dateCreation}" pattern="MM/dd/yyyy" />
									</c:when>
									<c:otherwise>
										<joda:format value="${virement.dateCreation}" pattern="dd/MM/yyyy" />
									</c:otherwise>
								</c:choose>
							</td>
							<td>
								<%-- 							<c:if test="${operation.class.name='OperationCheque'}"> --%> <%-- 							<spring:message code="details.OPERATIONCHEQUE" /> ${operation.numeroCheque} --%>
								<%-- 							</c:if> --%> ${virement.libelle}</td>
							<td>${virement.compte.libelle} (${virement.compte.numeroCompte})</td>
							<td>${virement.compteDistant.libelle} (${virement.compteDistant.numeroCompte})</td>
							<td><fmt:formatNumber value="${-virement.montant}" pattern="#0.00 EUR" /></td>
						</tr>
						<c:set var="compteur" value="${compteur+1}" />
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="histo.alt" />
				</p>
			</c:otherwise>
		</c:choose>

		<br />
		<!-- 		<p> -->
		<%-- 			<a href="${pageContext.request.contextPath}/user/virements/virements.html"><spring:message code="transfers.back" /> </a> --%>
		<!-- 		</p> -->

	</div>
</div>
<div style="clear: both;">&nbsp;</div>