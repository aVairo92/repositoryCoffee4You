<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.beans.OrdineBean,it.unisa.model.beans.ProdottoBean,it.unisa.model.cart.*"%>

<%
OrdineBean ordine =(OrdineBean) request.getAttribute("ordine") ;
List<ProdottoQuantita> prodotti = ordine.getProdottiOrdine() ;
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettaglio ordine</title>
</head>
<body>

<%@ include file="/common/header.jsp" %>

<div class="row main-container">

<div class="first-container">
<%@ include file="/common/account-vertical-menu.jsp" %>
</div>

<div class="second-container row">
<%
if(prodotti!=null && prodotti.size() != 0) {
	Iterator<ProdottoQuantita> iterator = prodotti.iterator() ;
%>
<div class="main-det-ordine">

<%while(iterator.hasNext()) { 
		ProdottoQuantita p = (ProdottoQuantita) iterator.next() ;
%>

<div class="prod-ord-container row flex">

<div class="img-detOrdine-container">
<img class="cart-img" src="images/<%=p.getImg()%>">
</div>

<div class="info-detOrdine-container row justify-content-start align-items-center">

<div class="col-sm-3 col-6">
<h6>Nome Prodotto:</h6>
<h6 class="small"><strong><%=p.getNome()%></strong></h6>
</div>

<div class="col-sm-3 col-6">
<h6>Quantità:</h6>
<h6 class="small"><strong><%=p.getQuantita()%></strong></h6>
</div>

<div class="col-sm-3 col-6">
<h6>Prezzo Acquisto:</h6>
<h6 class="small"><strong><%=p.getPrezzo()%></strong></h6>
</div>

<div class="col-sm-3 col-6">
<h6>Iva:</h6>
<h6 class="small"><strong><%=p.getIva()%></strong></h6>
</div>

</div>


</div>
<%}%>
</div>

<div class="flex riepilogo-container">
<div class="riepilogo-sticky">

<div class="infoId">
<h4>N°ordine:<%=ordine.getId()%></h4>
</div>

<div class="info">
<h4>Acquirente</h4>
<h6><strong><%=ordine.getAcquirente().getNome()+" "+ordine.getAcquirente().getCognome()%></strong></h6>
</div>
<div class="info">
<h4>Indirizzo Consegna</h4>
<h6><strong><%=ordine.getIndirizzoConsegna()%></strong></h6>
</div>
<div class="info">
<h4>Data Emissione</h4>
<h6><strong><%=ordine.getDataEmissione()%></strong></h6>
</div>
<div class="info">
<h4>Stato ordine</h4>
<h6><strong><%=ordine.getStatoOrdine()%></strong></h6>
</div>

<div class="info">
<h4>Spesa totale</h4>
<h6><strong><%=ordine.getTotaleSpesa()%> Euro</strong></h6>
</div>

<a class="text-decoration-none"  href="fattura?action=genera&id=<%=ordine.getId()%>" 
target="_blank"><button class="btn-checkout">Fattura</button></a>

</div>
</div>
	<%}%>

</div>
</div>

<%@ include file="/common/footer.jsp" %>

</body>
</html>