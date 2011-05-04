<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="post">
	<h2 class="title">
		<a href="#">${title}</a>
	</h2>
	<p class="meta">S'authentifier</p>
	<div class="entry">
		<form id="loginForm" action="<c:url value='j_spring_security_check'/>" method="POST">

			<div>
				<p>
					Login : <br /> <input type="text" name="j_username" id="j_username" />
				</p>
				<p>
					Mot de passe : <br /> <input type="password" name="j_password" id="j_password" />
				</p>
				<p>
					<input type="checkbox" name="_spring_security_remember_me" /> Se souvenir de moi
				</p>
				<p>
					<input type="submit" value="Valider" /> <input type="reset" />
				</p>
			</div>
		</form>

		<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message}">
			<font color="red"> Erreur d'authentification : <br /> <br /> <c:out
					value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />. </font>
			<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
		</c:if>

	</div>
</div>
<div style="clear: both;">&nbsp;</div>