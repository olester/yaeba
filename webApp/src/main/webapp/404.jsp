<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	//on pourrait directement gere une jsp d'erreur avec message et code,etc...
	request.setAttribute("title", "page perdue");
	request.setAttribute("titlePrefix", "YAEBA - Yet Another E-Banking Application");
%>
<jsp:include page="WEB-INF/pageModules/head.jsp"></jsp:include>
<body>
	<div id="wrapper">
		<!-- #logo -->
		<jsp:include page="WEB-INF/pageModules/logo.jsp"></jsp:include>
		<div id="header">
			<!-- #menu -->
			<jsp:include page="WEB-INF/pageModules/menu.jsp"></jsp:include>
			<!-- #search -->
			<jsp:include page="WEB-INF/pageModules/search.jsp"></jsp:include>
		</div>
		<!-- end #header -->
		<!-- end #header-wrapper -->
		<div id="page">
			<div id="page-bgtop">
				<div id="content">
					<div class="post">
						<h2 class="title">
							<a href="#">La page que vous demandez n'existe pas!!!</a>
						</h2>
						<p class="meta">
							<span>Code by <a href="http://code.google.com/p/yaeba">Yaeba
									Team</a> </span>
						</p>
						<div class="entry">
							<h1>:/</h1>
							<br />
							<a href="index.jsp">retourner à la page d'accueil</a>
						</div>
					</div>
				</div>
				<!-- #sidebar -->
				<jsp:include page="WEB-INF/pageModules/sideBar.jsp"></jsp:include>
				<div style="clear: both;">&nbsp;</div>
			</div>

		</div>
		<!-- #footer -->
		<jsp:include page="WEB-INF/pageModules/footer.jsp"></jsp:include>
	</div>
</body>
</html>