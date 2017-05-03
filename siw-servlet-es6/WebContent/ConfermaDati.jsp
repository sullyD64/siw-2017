<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="it.uniroma3.servlet.Studente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="prova.css"/>
<title>Conferma Dati</title>
</head>
<body>
<h1>Dati inseriti nella form</h1>
<ul>
<% Studente studente = (Studente)session.getAttribute("studente"); %>
<li>Nome: <b><%= studente.getNome().toUpperCase() %></b></li>
<li>Cognome: <b><%= studente.getCognome().toUpperCase() %></b></li>
<li>Matricola: <b><%= studente.getMatricola() %></b></li>
<li>Data di nascita: <b><%= studente.getDataNascita() %></b></li>
</ul>
<h2>Scegli:</h2>
<ul>
<% String encodedURL = response.encodeURL("MostraDati.jsp");%>
<li><a href = "<%= encodedURL%>">Conferma i dati inseriti</a></li>
<li><a href = "newStudente.html">Torna all'inserimento</a>
</ul>
</body>
</html>