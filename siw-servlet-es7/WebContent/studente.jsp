<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.uniroma3.servlet.Studente"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="prova.css" />
<title>Dati confermati</title>
</head>
<body>
	<h1>Hai confermato i seguenti dati</h1>
	<div align="center">
		<table>
			<% Studente studente = (Studente) request.getAttribute("studente"); %>
			<tr><td align = "right">Nome: </td><td><b>${studente.nome}</b></td></tr>
			<tr><td align = "right">Cognome: </td><td><b>${studente.cognome}</b></td></tr>
			<tr><td align = "right">Matricola: </td><td><b>${studente.matricola}</b></td></tr>
			<tr><td align = "right">Data di nascita: </td><td><b>${studente.dataNascita}></b></td></tr>
		</table>
	</div>
	<hr>
	<iframe src="//giphy.com/embed/4LWEwOMfbVeYU?html5=true&hideSocial=true"
	width="200" height="200" class="giphy-embed"></iframe>
	<hr>
	<h2>/netInfo</h2>
	<%
		String address = (String) request.getRemoteAddr();
		String host = (String) request.getRemoteHost();
		String userAgent = request.getHeader("User-Agent");
	%>
	IP:
	<b><%=address%></b><br />
	Host:
	<b><%=host%></b><br />
	userAgent:
	<b><%=userAgent%></b><br />
</body>
</html>