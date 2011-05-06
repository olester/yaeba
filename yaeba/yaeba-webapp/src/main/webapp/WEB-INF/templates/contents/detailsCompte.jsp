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
				<table>
					<tr class="libelle">
						<td><spring:message code="details.date" /></td>
						<td><spring:message code="details.label" /></td>
						<td><spring:message code="details.amount" /></td>
					</tr>

					<c:forEach var="operation" items="${compte.operations}" begin="0" end="9">
						<tr>
							<td><fmt:formatDate value="${operation.dateCreation}" pattern="dd/MM/yyyy" />
							</td>
							<td>${operation.libelle}</td>
							<td><fmt:formatNumber value="${operation.montant}" pattern="#0.00 €" />
							</td>
						</tr>
					</c:forEach>
				</table>
			</c:when>
			<c:otherwise>
				<p>
					<spring:message code="details.alt" />
				</p>
			</c:otherwise>
		</c:choose>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>