<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YAEBA - Yet Another E-Banking Application - page
	d'authentification'</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="templateCss/style.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>
<body>
	<div id="wrapper">
		<div id="logo">
			<h1>
				<a href="#">YAEBA - Yet Another E-Banking Application </a>
			</h1>
			<p>
				<em> code by <a href="http://code.google.com/p/yaeba"> the
						yaeba team</a> </em>
			</p>
		</div>
		<hr />
		<!-- end #logo -->
		<div id="header">
			<div id="menu">
				<ul>
					<li><a href="#" class="first">Home</a>
					</li>
					<li class="current_page_item"><a href="#">Blog</a>
					</li>
					<li><a href="#">Photos</a>
					</li>
					<li><a href="#">About</a>
					</li>
					<li><a href="#">Links</a>
					</li>
					<li><a href="#">Contact</a>
					</li>
				</ul>
			</div>
			<!-- end #menu -->
			<div id="search">
				<form method="get" action="">
					<fieldset>
						<input type="text" name="s" id="search-text" size="15" /> <input
							type="submit" id="search-submit" value="GO" />
					</fieldset>
				</form>
			</div>
			<!-- end #search -->
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
				<div id="sidebar">
					<ul>
						<li>
							<h2>Aliquam tempus</h2>
							<p>Mauris vitae nisl nec metus placerat perdiet est.
								Phasellus dapibus semper urna. Pellentesque ornare, orci in
								consectetuer hendrerit, volutpat.</p>
						</li>
						<li>
							<h2>Pellenteque ornare</h2>
							<ul>
								<li><a href="#"></a><a href="#">Nec metus sed donec</a></li>
								<li><a href="#">Magna lacus bibendum mauris</a></li>
								<li><a href="#">Velit semper nisi molestie</a></li>
								<li><a href="#">Eget tempor eget nonummy</a></li>
								<li><a href="#">Nec metus sed donec</a></li>
							</ul>
						</li>
						<li>
							<h2>Turpis nulla</h2>
							<ul>
								<li><a href="#"></a><a href="#">Nec metus sed donec</a></li>
								<li><a href="#">Magna lacus bibendum mauris</a></li>
								<li><a href="#">Velit semper nisi molestie</a></li>
								<li><a href="#">Eget tempor eget nonummy</a></li>
								<li><a href="#">Nec metus sed donec</a></li>
								<li><a href="#"></a><a href="#">Nec metus sed donec</a></li>
								<li><a href="#">Magna lacus bibendum mauris</a></li>
								<li><a href="#">Velit semper nisi molestie</a></li>
								<li><a href="#">Eget tempor eget nonummy</a></li>
								<li><a href="#">Nec metus sed donec</a></li>
							</ul>
						</li>
					</ul>
				</div>
				<!-- end #sidebar -->
				<div style="clear: both;">&nbsp;</div>
			</div>

		</div>
		<div id="footer">
			<p>
				Copyright (c) 2008 Sitename.com. All rights reserved. Design by <a
					href="http://www.freecsstemplates.org/">Free CSS Templates</a>.
			</p>
			<p>
				Copyright (c) 2008 Sitename.com. All rights reserved. Code by <a
					href="http://www.excilys.com/">excilys</a>.
			</p>
		</div>
		<!-- end #footer -->
	</div>
</body>
</html>