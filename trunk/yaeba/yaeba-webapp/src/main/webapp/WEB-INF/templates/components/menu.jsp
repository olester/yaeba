<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="menu">
	<ul>

		<sec:authorize access="isAuthenticated()">

			<li
				<c:if test="${bouton eq 'bouton_welcome'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath }/welcome.html">Accueil</a>
			</li>
			<li
				<c:if test="${bouton eq 'bouton_comptes'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath}/user/comptes.html">Comptes</a>
			</li>
			<li
				<c:if test="${bouton eq 'bouton_virements'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath}/user/virements.html">Virements</a>
			</li>
		</sec:authorize>

		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li
				<c:if test="${bouton eq 'bouton_admin'}"> class="current_page_item"</c:if>><a
				href="${pageContext.request.contextPath}/admin/panel.html">Admin</a>
			</li>
		</sec:authorize>

	</ul>
</div>