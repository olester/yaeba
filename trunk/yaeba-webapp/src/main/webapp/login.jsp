<%
	boolean connecte = false;
	//if (request.getSession(false) != null) {
	//	connecte = true;
	//} else {
	if (request.getParameter("login").equals("login") && request.getParameter("password").equals("password")) {
		connecte = true;
		//request.getSession();
		//	}
	}

	if (connecte) {
%>
<jsp:include page="index.jsp" />
<%
	} else {
%>
<jsp:include page="erreurLogin.jsp" />
<%
	}
%>