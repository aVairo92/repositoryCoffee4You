<%
Carrello carrello = (Carrello) request.getAttribute("carrello");

Boolean isClienteLoggato =(Boolean) request.getSession().getAttribute("isClienteLoggato") ;

List<ProdottoBean> listaProdottiPromo =(List<ProdottoBean>) request.getAttribute("prodPromo");

DateFormat dFormat = DateFormat.getDateInstance(DateFormat.FULL) ; 
Calendar calendar = Calendar.getInstance() ;
calendar.add(Calendar.DAY_OF_MONTH,+3) ;
Date dataConsegna = calendar.getTime() ;
String dConsegna = dFormat.format(dataConsegna) ;
%>
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,java.text.DateFormat,it.unisa.model.beans.ProdottoBean,it.unisa.model.cart.*"%>
<head>
<meta charset="ISO-8859-1">
<link href="firstStyle.css" rel="stylesheet" type="text/css">
<title>Carrello</title>
</head>
<body>

<%@include file="common/header.jsp" %>

<% if(carrello!=null && carrello.getProdotti().size()!=0) { %>

<div class="container-generale row justify-content-start align-items-start">

<div class="cart-container">
<h4>Carrello(<%=carrello.getProdotti().size()%>)</h4>
<h6>Spedito da Coffee4You...</h6>
<%
List<ProdottoQuantita> prodottiCarrello = carrello.getProdotti() ;

for(ProdottoQuantita p : prodottiCarrello) {
%>
<div class="cart-product row border border-dark">

<div class="cart-img-container col-3">
<img class="cart-img" src="images/<%=p.getProdotto().getUrlImmagine()%>">
</div>

<div class="d-flex d-column cart-info-container col-5 col-sm-4">
<div class="info">
<h3><%=p.getProdotto().getMarcaProdotto()%></h3>
<h4><%=p.getProdotto().getNomeProdotto()%></h4>
<h5>Prezzo:<%=p.getProdotto().getPrezzoProdotto()%> Euro</h5>
<h5>Iva:<%=p.getProdotto().getIvaProdotto()%>%</h5>
<a class="elimina text-decoration-none" href="carrello?action=remove&id=<%=p.getProdotto().getId()%>"><i class="bi bi-trash-fill"></i>Elimina</a>
</div>
</div>

<div class="d-flex d-column cart-info-container col-3 col-sm-4">
<div class="info">
<h4>Quantità: <%=p.getQuantita()%></h4>
<h4 class="prz-tot"><%=p.getProdotto().calcolaPrezzoIvato()*p.getQuantita()%>Euro</h4>
</div>
</div>

</div>
<%}%>
</div>
<div class="cart-container-tot">
<div class="tot-title">
<h3 class="prz-tot">Totale</h3>
</div>
<div class="tot-sub row align-items-start">
<div class="col-12 row justify-content-beetween">
<div class="col-8"><h4>Subtotale:</h4></div>
<div class="col-4"><h5><%=carrello.calcolaTotale()%> Euro</h5></div>
</div>
<div class="col-12 row justify-content-beetween">
<div class="col-8"><h4>Spese di spedizione:</h4></div>
<%if(carrello.calcolaTotale() < 25) { %>
<div class="col-4"><h5>6.99 Euro</h5></div>
<%}else{ %>
<div class="col-4"><h5>0.00 Euro</h5></div>
<%}%>
</div>
</div>
<a class="elimina text-decoration-none" href="carrello?action=clearCarrello">Svuota Carrello</a><br>
<div>
<%if(isClienteLoggato != null){%>
<a class="text-decoration-none"  href="checkout.jsp"><button class="btn-checkout">PROCEDI</button></a>
<%}else{%>
<a class="text-decoration-none" href="log-reg.jsp"><button class="btn-checkout">PROCEDI</button></a> 
<%}%>
</div>
</div>

<div class="cart-container">
<h3>Data di Consegna:</h3>

<h4><%=dConsegna%></h4>
</div>
<div class="cart-container">
<h3>Metodi di pagamento disponibili:</h3>
<div class="row">
<ul class="pay-ul">
<li class="pay-il"><img class="pay-img" src="images/payments/postepay.png"></li>
<li class="pay-il"><img class="pay-img" src="images/payments/mastercard.png"></li>
<li class="pay-il"><img class="pay-img" src="images/payments/visa.png"></li>
</ul>
</div>
</div>
</div>

<div class="container-prodInPromo">
<h3>Le Nostre Offerte...</h3>
<a href="catalogo"><button>Scopri di più--></button></a>

<div class="container-prodInPromo">
<ul class="listaProdInPromo">
<%
Iterator<ProdottoBean> iterator = listaProdottiPromo.iterator() ;
while(iterator.hasNext()) { 
	ProdottoBean prod = (ProdottoBean) iterator.next() ;
%>	
<li class="prodInPromo">
<div class="card card-catalogo" style="width: 18rem;height: 26rem;">
  <a href="carrello?action=dettagli&id=<%=prod.getId()%>" target="_blank"><img src="images/<%=prod.getUrlImmagine()%>" class="img-fluid img-card-list-product card-img-top" alt="<%=prod.getNomeProdotto()%>"></a>
 <div class="info-list-prod">
  <h3 class="list-prod-text"><%=prod.getMarcaProdotto()%></h3>
  <h3 class="list-prod-text"><%=prod.getNomeProdotto()%></h3>
  <h3 class="list-prod-text">Euro <%=prod.getPrezzoProdotto()%></h3>
 </div>
</div>
</li>
<%}%>
</ul>
</div>

</div>

<%} else {%>

<div class="empty-cart">
<div class="empty-cart-img">
<img src="images/shopping-cart.jpg" class="img-fluid img-card-catalogo card-img-top" alt="Cart-Empty">
</div>

<h3>Nessun articolo nel carrello</h3>

<div class="row empty-cart-btn">
<a class="col-6 text-decoration-none"  href="catalogo"><button class="btn-checkout">CATALOGO</button></a>
<a class="col-6 text-decoration-none"  href="log-reg.jsp"><button class="btn-checkout">Accedi</button></a>
</div>
</div>

<%}%>

<%@include file="common/footer.jsp"%>
<%@include file="modal/login.jsp"%>
</body>
</html>