<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="it.uniroma3.servlet.Studente"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="prova.css" />
<title>Inserimento Studente</title>
</head>
<body>
	<hr>
	<h1>/addNewStudent (esercizio 7)</h1>
	<hr>
	<form action="studenteController" method="get">
		<div align="center">
			<table>
				<tr>
					<td align="right">Nome:</td>
					<td><input type="text" name="nome" size="20"
						autofocus="autofocus"
						value = '${nome}'/></td>
				</tr>
				<tr>
					<td align="right">Cognome:</td>
					<td><input type="text" name="cognome" size="20"
						value="${cognome}" /></td>
				</tr>
				<tr>
					<td align="right">Matricola:</td>
					<td><input type="text" name="matricola" size="6" maxlength="6"
						pattern=".{6,}" placeholder="XXXXXX"
						value="${matricola}" /></td>
					<td>${errMatricola}</td>
				</tr>
				<tr>
					<td align="right">Data nascita:</td>
					<td><input type="date" name="dataNascita" size="20" 
						value="${dataNascita}" /></td>
				</tr>
			</table>
			<input type="submit" value="INVIA" />
		</div>
	</form>
</body>
</html>