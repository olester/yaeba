<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<table>
			<tr class="libelle">
				<td><spring:message code="accounts.account" /></td>
				<td><spring:message code="accounts.balance" /></td>
			</tr>
			<c:forEach var="compte" items="${comptes}" begin="0" end="9">
				<tr>
					<td class="numero"><a href="${pageContext.request.contextPath }/user/detailCompte.jsp?c=${compte.numeroCompte}">${compte.numeroCompte}</a>
					</td>
					<td>... €</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>