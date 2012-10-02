<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	List<String> list = new ArrayList<String>();
	list = (List<String>)request.getAttribute("errors");
	System.out.println(list.toString());
	String errorMsg = list.toString().replace("[", "").replace("]", "");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<h3 align="center">
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			Somthing Went Wrong! please try after some time    <%=    errorMsg%>
		</h3>
	</body>
</html>
