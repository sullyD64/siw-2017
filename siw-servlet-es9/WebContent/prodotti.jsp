<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="it.uniroma3.model.Prodotto" %>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="prova.css" />
<title>Lista Prodotti</title>
</head>
<body>
	<h1>/operationSuccessful</h1>
	Hai inserito:<br>
	<ul>
	<!--
		<li>(nome, prezzo)</li>
		<c:forEach var="prodotto" items="${prodotti}">
		<li>${prodotto.nome}, ${prodotto.prezzo} EUR</li>
		</c:forEach>
	-->
		<li>Nome: ${prodotto.nome}</li>
		<li>Descrizione: ${prodotto.desc}</li>
		<li>Prezzo: ${prodotto.prezzo}</li>
		<li>Data di scadenza: ${prodotto.dataScadenza}</li>
	</ul>
</body>
</html>