<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><tiles:getAsString name="title" />
</title>
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript">
	function allumerBouton(bouton) {
		var bouton=document.getElementById(bouton);
		bouton.setAttribute("class", "current_page_item");
	}
</script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div id="menu">
			<tiles:insertAttribute name="menu" />
		</div>
		<div id="page">
			<div id="content">
				<tiles:insertAttribute name="content" />
			</div>
<!-- 			<div id="sidebar"> -->
<%-- 				<tiles:insertAttribute name="sidebar" /> --%>
<!-- 			</div> -->
			<div style="clear: both;">&nbsp;</div>
		</div>
	</div>
	<div id="footer-content">
		<tiles:insertAttribute name="footer-content" />
	</div>
	<div id="footer">
		<p>
			(c) 2009 Sitename.com. Design by <a href="http://www.nodethirtythree.com">nodethirtythree</a> and <a
				href="http://www.freecsstemplates.org">Free CSS Templates</a>.
		</p>
	</div>
</body>
</html>