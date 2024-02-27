<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carta Esistente</title>
</head>
<body>
<%@include file="../common/header.jsp"%>

<div class="empty-cart">
<div class="empty-cart-img">
<img src="images/err-cat.jpg" class="img-fluid img-card-catalogo card-img-top" alt="Cart-Empty">
</div>

<h3>Errore Carta di Credito!!! Riprova!</h3>

<div class="row empty-cart-btn">
<a class="col-6 text-decoration-none"  href="carrello"><button class="btn-checkout">Ritorna al carrello</button></a>
<a class="col-6 text-decoration-none"  href="carte"><button class="btn-checkout">Menu carte</button></a>
</div>
</div>

<%@include file="../common/footer.jsp"%>
</body>
</html>