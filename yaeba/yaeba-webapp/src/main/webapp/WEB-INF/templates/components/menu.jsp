<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<div id="menu">
	<ul>
		<sec:authorize access="isAuthenticated()">
			<li <c:if test="${buttonkey eq 'button_welcome'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath }/welcome.html"><spring:message code="menu.home" /> </a></li>
			<li <c:if test="${buttonkey eq 'button_accounts'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath}/user/comptes/comptes.html"><spring:message code="menu.accounts" /> </a></li>
			<li <c:if test="${buttonkey eq 'button_transfers'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath}/user/virements/virements.html"><spring:message code="menu.transfers" />
			</a></li>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li <c:if test="${buttonkey eq 'button_admin'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath}/admin/admin.html"><spring:message code="menu.admin" /> </a></li>
		</sec:authorize>

	</ul>
</div>