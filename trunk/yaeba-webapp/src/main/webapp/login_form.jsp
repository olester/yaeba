<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
	request.setAttribute("title", "page de login");
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
							<a href="#">Veuillez vous loguer:</a>
						</h2>
						<p class="meta">
							l'application est en cours de construction' <br /> <span>Designed
								by <a href="http://code.google.com/p/yaeba">Yaeba Team</a> </span>
						</p>
						<div class="entry">
							<form action="login.jsp" method="post">
								<div>
									<span>login:</span> <span> <input type="text"
										name="login" /> </span>
								</div>
								<div>
									<span>password:</span> <span><input type="password"
										name="password" /> </span>
								</div>
								<div>
									<input type="submit" value="login" />
								</div>
							</form>
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