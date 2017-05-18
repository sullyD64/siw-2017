<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="terminal.css" />
<title>Nuovo Prodotto</title>
</head>
<body>
	<h1>/addNewProdotto (esercizio finale)</h1>
	<hr>
	<div align="center">
		<form action="product" method="post">
			<table>
				<tr>
					<td align="right">Nome*:</td>
					<td><input type="text" name="name" 
						autofocus="autofocus"
						value="${product.name}" /></td>
					<td>${errName}</td>
				</tr>
				<tr>
					<td align="right">Descrizione*:</td>
					<td><input type="text" name="description"
						value="${product.description}" /></td>
					<td>${errDesc}</td>
				</tr>
				<tr>
					<td align="right">Prezzo*:</td>
					<td><input type="text" name="price"
						value="${product.price}" /></td>
					<td>${errPrice}</td>
				</tr>
				<tr>
					<td align="right">Data di scadenza*:</td>
					<td><input type="date" name="expirationDate"
						placeholder = "AAAA-MM-GG"
						value="${product.expirationDate}" /></td>
					<td>${errDate}</td>
				</tr>
				<tr>
					<td colspan ="3" align = "center">(* = campo obbligatorio)</td>
				</tr>
			</table>
			<input type="submit" name="submit" value="INVIA" />
		</form>
		<a href = "product">Lista Prodotti</a>
	</div>
</body>
</html>