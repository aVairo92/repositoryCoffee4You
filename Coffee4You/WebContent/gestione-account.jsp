<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
OrdineBean ordine = (OrdineBean) request.getAttribute("ordineFattura") ;
ClienteBean cliente = (ClienteBean) request.getAttribute("clienteFattura") ;
Collection<ProdottoQuantita> prodottiOrdine = (Collection<ProdottoQuantita>) request.getAttribute("prodottiOrdine") ;
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,it.unisa.model.beans.*,it.unisa.model.cart.ProdottoQuantita"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Account</title>
</head>
<body>

<%@ include file="common/header.jsp" %>

<div class="row main-container">

<div class="first-container">
<%@ include file="common/account-vertical-menu.jsp" %>
</div>

<div class="second-container">

<div class="col mb-5">
<img class="menu-account-img" src="images/logo2.png" alt="Cart-Empty">
<h2>Il tuo Account</h2>
<span>Dal tuo account Coffee4You puoi gestire i tuoi ordini e controllare le info del tuo profilo. </span>
</div>

<div class="m-5 row text-center">
<div class="col-12 mb-2">
<i class="bag-icon bi bi-bag"></i>
</div>
<div class="col-12 mb-2">
<a class="text-decoration-none btn-account" href="/Coffee4You/home.jsp">Continua lo Shopping</a>
</div>
</div>

</div>
</div>

<%@ include file="common/footer.jsp" %>
</body>
</html>