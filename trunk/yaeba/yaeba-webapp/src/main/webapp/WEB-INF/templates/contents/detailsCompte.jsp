<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<c:if test="${not empty compte.operations}">
			<table>
				<tr class="libelle">
					<td><spring:message code="details.date" />
					</td>
					<td><spring:message code="details.label" />
					</td>
					<td><spring:message code="details.amount" />
					</td>
				</tr>

				<c:forEach var="operation" items="${compte.operations}" begin="0" end="9">
					<tr>
						<td>...</td>
						<td>${operation.libelle}</td>
						<td>${operation.montant} €</td>
					</tr>
				</c:forEach>

			</table>
		</c:if>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>