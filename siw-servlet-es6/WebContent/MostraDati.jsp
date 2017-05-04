<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.uniroma3.servlet.Studente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="prova.css" />
<title>Dati confermati</title>
</head>
<body>
	<h1>Hai confermato i seguenti dati</h1>
	<ul>
		<%
			Studente studente = (Studente) session.getAttribute("studente");
		%>
		<li>Nome: <b><%=studente.getNome().toUpperCase()%></b></li>
		<li>Cognome: <b><%=studente.getCognome().toUpperCase()%></b></li>
		<li>Matricola: <b><%=studente.getMatricola()%></b></li>
		<li>Data di nascita: <b><%=studente.getDataNascita()%></b></li>
	</ul>
	<hr>
	<iframe
		src="//giphy.com/embed/4LWEwOMfbVeYU?html5=true&hideSocial=true"
		width="200" height="200" frameBorder="0" class="giphy-embed"></iframe>

	<hr>
	<h2>/netInfo</h2>
	<%
		String address = (String) request.getRemoteAddr();
		String host = (String) request.getRemoteHost();
		String userAgent = request.getHeader("User-Agent");
	%>
	<br />IP:
	<b><%=address%></b>
	<br />Host:
	<b><%=host%></b>
	<br />userAgent:
	<b><%=userAgent%></b>
</body>
</html>