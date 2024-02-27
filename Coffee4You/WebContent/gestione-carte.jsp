<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.beans.CartaDiCreditoBean" %>
<%
Collection<CartaDiCreditoBean> carteCliente = (Collection<CartaDiCreditoBean>) request.getSession().getAttribute("carteCliente") ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Carte cliente</title>
</head>
<body>

<%@include file="common/header.jsp" %>
<div class="row main-container">

<div class="first-container">
<%@ include file="common/account-vertical-menu.jsp" %>
</div>

<div class="second-container">
<div class="title">
<h2><strong>Carte di Credito </strong></h2>
<span>Visualizza e gestisci le tue carte di credito.</span>
</div>


<div class="row info">

<div class="addAddress" data-bs-toggle="modal" data-bs-placement="top" title="Aggiungi Carta"
data-bs-target="#addCartaModal">
<div>
<i class="icon-address bi bi-plus-circle-fill"></i>
<h6><strong>Aggiungi Carta</strong></h6>
</div>
</div>

<%if(carteCliente != null && carteCliente.size()!= 0) {

	int nCarta = 0 ;
	Iterator<CartaDiCreditoBean> iterator = carteCliente.iterator() ;
	
	while(iterator.hasNext()) { 
		CartaDiCreditoBean carta = (CartaDiCreditoBean) iterator.next() ;
		nCarta = nCarta + 1 ;

%>

<div class="address">

<div class="titolo">
<h5>Carta di Credito</h5>
</div>

<div class="titolare">
<h4><%=carta.getTitolare().getNome()%> <%=carta.getTitolare().getCognome()%></h4>
</div>

<div class="col informazioni">
<h6><%=carta.getCodiceCarta()%></h6>
<h6>Data Scadenza:<%=carta.getDataScadenza()%></h6>
<h6>Saldo:<%=carta.getSaldo()%></h6>
</div>

<div class="row pulsanti justify-content-center">

<div class="delete justify-content-start align-items-center" style="width:50px;height:50px;">
<form id="<%="rimuoviCarta"%><%=nCarta%>" action="carte" method="POST">
<input type="hidden" name="action" value="remove">
<input type="hidden" name="codiceCarta" value="<%=carta.getCodiceCarta()%>">
<input type="hidden" name="titolareId" value="<%=carta.getTitolare().getId()%>">
</form>
<a class="btn-addr" onClick="document.getElementById('<%="rimuoviCarta"%><%=nCarta%>').submit();"><i class="icon-address bi bi-trash-fill"></i></a>
</div>

</div>

</div>

<%}%>

	<%}%>
</div>

</div>
</div>

<%@ include file="modal/aggiungi-carta.jsp" %>
<%@ include file="common/footer.jsp" %>

</body>
</html>