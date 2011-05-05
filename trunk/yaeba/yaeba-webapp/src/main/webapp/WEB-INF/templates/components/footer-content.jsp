<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="footer-content">
	<div class="column1">
		<h2>Yaeba</h2>
		<p>
			<strong>Yet Another E-Banking Application</strong> <spring:message code="footer.developers" />
			Yaeba Group <a href="http://code.google.com/p/yaeba/w/list"><spring:message code="footer.more" /></a>
		</p>
	</div>
	<span style="float: right"> <a href="?lang=en"> <img
			src="${pageContext.request.contextPath}/images/drapeau_gb.png"
			border="0" width="24" /> </a> &nbsp;&nbsp; <a href="?lang=fr"> <img
			src="${pageContext.request.contextPath}/images/drapeau_fr.png"
			border="0" width="24" /> </a> </span>
	<div class="column2">
		<ul class="list">
			<li><a href="http://www.excilys.com">http://www.excilys.com</a></li>
		</ul>
	</div>
</div>