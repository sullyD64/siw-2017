<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.uniroma3.model.Prodotto" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="prova.css" />
<title>Inserimento prodotto</title>
</head>
<body>
	<hr>
	<h1>/addNewProdotto (esercizio 9)</h1>
	<hr>
	<form action="prodotto" method="get">
		<div align="center">
			<table>
				<tr>
					<td align="right">Nome*:</td>
					<td><input type="text" name="nome" size="20"
						autofocus="autofocus"
						value ="${prodotto.nome}" /></td>
					<td>${errNome}</td>
				</tr>
				<tr>
					<td align="right">Descrizione*:</td>
					<td><input type="text" name="descrizione" size="20"
						value="${prodotto.desc}" /></td>
					<td>${errDesc}</td>
				</tr>
				<tr>
					<td align="right">Prezzo*:</td>
					<td><input type="text" name="prezzo"
						value="${prodotto.prezzo}" /></td>
					<td>${errPrez}</td>
				</tr>
				<tr>
					<td align="right">Data scadenza*:</td>
					<td><input type="date"  name="dataScadenza" size="20" 
						placeholder="AAAA-MM-GG"
						value="${prodotto.dataScadenza}" /></td>
					<td>${errData}</td>
				</tr>
				<tr>
					<td colspan ="3" align = "center">(* = campo obbligatorio)</td>
				</tr>
			</table>
			<input type="submit" value="INVIA" />
		</div>
	</form>
</body>
</html>