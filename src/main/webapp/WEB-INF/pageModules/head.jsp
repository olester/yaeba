<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String titlePrefix = (String)request.getAttribute("titlePrefix");
	String title = (String)request.getAttribute("title");
	//TODO ou pas.
	String[] keywords = new String[0];
	String description = "page d'accueil";
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><%=titlePrefix %> - <%= title%></title>
<meta name="keywords" content="<% for(int i = 0; i< keywords.length; i++) System.out.print(keywords[i]+"; "); %>" />
<meta name="description" content="<%= description %>" />
<link href="templateCss/style.css" rel="stylesheet" type="text/css"
	media="screen" />
</head>