<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="terminal.css" />
<title>Prodotto</title>
</head>
<body>
	<h1>${product.name}</h1>
	<div>Descrizione: ${product.description}</div>
	<div>Prezzo: ${product.price}</div>
	<div>Data scadenza: ${product.expirationDate}</div>
</body>
</html>