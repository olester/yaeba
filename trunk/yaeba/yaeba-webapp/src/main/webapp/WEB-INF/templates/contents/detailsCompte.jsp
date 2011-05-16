<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>

<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery-1.6.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jqueryRotate-2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/toggle.js"></script>

<div class="post">
	<h2 class="title">
		<a href="#"><spring:message code="details.title" /> </a>
	</h2>
	<p class="meta">${libelle} | ${page} | ${nbPages}</p>
	<div class="entry">
		<c:choose>
			<c:when test="${!compteEstVide}">
				<form action="${pageContext.request.contextPath}/user/comptes/${numero}/choix.html" method="POST">
					<spring:message code="details.filter.txt" />
					<select name="annee" onchange="submit()">
						<c:forEach items="${anneesDispo}" var="numAnnee">
							<option <c:if test="${numAnnee==dateBean.annee}">selected</c:if> value="${numAnnee}">${numAnnee}</option>
						</c:forEach>
					</select> <select name="mois" onchange="submit()">
						<c:forEach items="${moisDispo}" var="numMois">
							<option <c:if test="${numMois==dateBean.mois}">selected</c:if> value="${numMois}">
								<spring:message code="details.month.${numMois}" />
							</option>
						</c:forEach>
					</select> <input type="hidden" name="anneeEx" value="${dateBean.annee}" />
					<noscript>
						<input type="submit" value="<spring:message code='details.submit' />" />
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
			<c:when test="${not empty listeOperations || not empty listeOperationsCB}">
				<div style="float: right; margin-top: -46px; margin-right: 40px;">
					<p>
						<a
							href="${pageContext.request.contextPath}/user/comptes/${numero}/${dateBean.annee}/${dateBean.mois}/${page}/details.html?excel=true"><img
							style="vertical-align: middle;" src="${pageContext.request.contextPath}/images/excel.png" /> </a> <a
							href="${pageContext.request.contextPath}/user/comptes/${numero}/${dateBean.annee}/${dateBean.mois}/${page}/details.html?excel=true"><spring:message
								code="details.export" /> </a>
					</p>
				</div>
			</c:when>
		</c:choose>
		<c:choose>
			<c:when test="${page > nbPages && nbPages > 0}">
				<p>
					<spring:message code="details.alt3" />
				</p>
			</c:when>
			<c:when test="${not empty listeOperations}">
				<p>
					<spring:message code="details.text" />
					<spring:message code="details.month.${dateBean.mois}" />
					${dateBean.annee} :
				</p>
				<table>
					<tr class="libelle">
						<td><spring:message code="details.date" /></td>
						<td><spring:message code="details.label" /></td>
						<td style="width: 160px;"><spring:message code="details.amount" /></td>
					</tr>

					<c:set var="compteur" value="0" />
					<c:forEach var="operation" items="${listeOperations}">
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
							<td>
								<%-- 							<c:if test="${operation.class.name='OperationCheque'}"> --%> <%-- 							<spring:message code="details.OPERATIONCHEQUE" /> ${operation.numeroCheque} --%>
								<%-- 							</c:if> --%> ${operation.libelle}</td>
							<td <c:if test="${operation.montant<0}">style="text-align:left;"</c:if>><fmt:formatNumber
									value="${operation.montant}" pattern="#0.00 EUR" /></td>
						</tr>
						<c:set var="compteur" value="${compteur+1}" />
					</c:forEach>
				</table>
				<br />
				<div class="previous">
					<c:if test="${page>1}">
						<a
							href="${pageContext.request.contextPath}/user/comptes/${numero}/${dateBean.annee}/${dateBean.mois}/${page-1}/details.html">&lt;
							<spring:message code="details.previous" /> </a>
					</c:if>
				</div>
				<div class="next">
					<c:if test="${page<nbPages}">
						<a
							href="${pageContext.request.contextPath}/user/comptes/${numero}/${dateBean.annee}/${dateBean.mois}/${page+1}/details.html"><spring:message
								code="details.next" /> &gt;</a>

					</c:if>
				</div>
				<br />
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="details.alt2" />
				</p>
			</c:otherwise>
		</c:choose>

		<c:choose>
			<c:when test="${nbCB>0}">
				<br />
				<table>
					<tr>
						<td><a id="action2" style="cursor: pointer;" title="<spring:message code='details.cc.link' />"><img
								style="margin-right: 15px; vertical-align: middle;" id="arrow"
								src="${pageContext.request.contextPath}/images/arrow.png" /> </a> <a id="action" style="cursor: pointer;"
							title="<spring:message code='details.cc.link' />"><spring:message code="details.sum" /> </a></td>
						<td><fmt:formatNumber value="${sommeCB}" pattern="#0.00 €" />
						</td>
					</tr>
				</table>
				<div id="deroulant" style="display: none;">
					<table>
						<tr class="libelle">
							<td><spring:message code="details.cc.effective.date" />
							</td>
							<td><spring:message code="details.label" />
							</td>
							<td style="width: 160px;"><spring:message code="details.amount" />
							</td>
							<td><spring:message code="details.cc.creation.date" />
							</td>
						</tr>

						<c:set var="compteur" value="0" />
						<c:forEach var="operation" items="${listeOperationsCB}">
							<tr class="ligne_${compteur%2}">
								<td><c:choose>
										<c:when test="${locale=='en'}">
											<joda:format value="${operation.dateEffective}" pattern="MM/dd/yyyy" />
										</c:when>
										<c:otherwise>
											<joda:format value="${operation.dateEffective}" pattern="dd/MM/yyyy" />
										</c:otherwise>
									</c:choose>
								</td>
								<td><spring:message code="details.OPERATIONCARTEBANCAIRE" /> ${operation.libelle}</td>
								<td><fmt:formatNumber value="${operation.montant}" pattern="#0.00 €" /></td>
								<td><c:choose>
										<c:when test="${locale=='en'}">
											<joda:format value="${operation.dateCreation}" pattern="MM/dd/yyyy" />
										</c:when>
										<c:otherwise>
											<joda:format value="${operation.dateCreation}" pattern="dd/MM/yyyy" />
										</c:otherwise>
									</c:choose></td>
							</tr>
							<c:set var="compteur" value="${compteur+1}" />
						</c:forEach>
					</table>
					<p align="center">
						<a id="close" style="cursor: pointer;"><spring:message code="details.cc.close" /> </a>
					</p>
				</div>
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="details.cc.alt" />
				</p>
			</c:otherwise>
		</c:choose>


		<br /> <br />
		<p>
			<a href="${pageContext.request.contextPath}/user/comptes/comptes.html"><spring:message code="details.back" /> </a>
		</p>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>