<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ordine effettuato</title>
</head>
<body>

<%@include file="common/header.jsp"%>


<div class="empty-cart">
<div class="empty-cart-img">
<img src="images/box.jpg" class="img-fluid img-card-catalogo card-img-top" alt="OrdineEffettuato">
</div>

<h1>Grazie per aver utilizzato Coffee4You!!!</h1>

<div class="row empty-cart-btn">
<a class="col-6 text-decoration-none"  href="catalogo"><button class="btn-checkout">Continua lo shopping</button></a>
<a class="col-6 text-decoration-none"  href="ordini"><button class="btn-checkout">I miei ordini</button></a>
</div>
</div>


<%@include file="common/footer.jsp"%>

</body>
</html>