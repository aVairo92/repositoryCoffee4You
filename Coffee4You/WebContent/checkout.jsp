<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.cart.*,it.unisa.model.beans.IndirizzoSpedizioneBean,it.unisa.model.beans.CartaDiCreditoBean"%>
<%
Carrello carrello = (Carrello) request.getSession().getAttribute("carrello") ; 

Collection<IndirizzoSpedizioneBean> indirizziCliente = (Collection<IndirizzoSpedizioneBean>) request.getSession().getAttribute("indirizziCliente") ;
Collection<CartaDiCreditoBean> carteCliente = (Collection<CartaDiCreditoBean>) request.getSession().getAttribute("carteCliente") ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout</title>
</head>
<body>
<%@include file="common/header.jsp" %>

<div class="container-generale row justify-content-start align-items-start">

<div class="cart-container">
<h3>Riepilogo prodotti</h3>

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
<% if(carrello.getProdotti().size()!=0) {%>
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
<%}%>

<div class="container-address-cart">

<form action="checkout" method="POST">

<div class="input-addr-card">

<h4>Indirizzo Consegna</h4>
<a class="elimina text-decoration-none"  data-bs-toggle="modal" data-bs-placement="top" title="Aggiungi Indirizzo"
data-bs-target="#addAddressModal">Aggiungi indirizzo</a><br>

<div>
<select class="select" id="indirizzo" name="viaIndirizzo">
<%
if(indirizziCliente != null) {
for(IndirizzoSpedizioneBean indirizzo : indirizziCliente){
%>
<option class="option" value=" <%=indirizzo.getVia()%>"><%=indirizzo.getVia()%></option>
<%
} 
}
%>
</select>
</div>

<h4>Scegli carta</h4>
<a class="elimina text-decoration-none" data-bs-toggle="modal" data-bs-placement="top" title="Aggiungi Carta"
data-bs-target="#addCartaModal">Aggiungi carta</a><br>

<div class="select">
<select class="select" id="carta" name="codiceCarta">
<%
if(carteCliente != null) {
for(CartaDiCreditoBean carta : carteCliente){
%>
<option value="<%=carta.getCodiceCarta()%>"><%=carta.getCodiceCarta()%></option>
<%
}
}
%>
</select>
</div>

</div>

<div>
<a class="elimina text-decoration-none" href="carrello?action=clearCarrello">Annulla</a><br>
<a class="text-decoration-none"><input class="btn-checkout" type="submit" value="Finalizza Ordine"></a>
</div>

</form>
</div>

</div>

</div>

<%@ include file="modal/aggiungi-cartaCheck.jsp" %>
<%@ include file="modal/aggiungi-indirizziCheck.jsp" %>
<%@include file="common/footer.jsp" %>
</body>
</html>