<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="menu">
	<ul>

		<li <c:if test="${bouton eq 'bouton_welcome'}"> class="current_page_item"</c:if>><a href="welcome.html">Accueil</a>
		</li>

		<li <c:if test="${bouton eq 'bouton_login'}"> class="current_page_item"</c:if>><a href="login.html">Login</a>
		</li>
	</ul>
</div>