<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.beans.IndirizzoSpedizioneBean"%>
<%
Collection<IndirizzoSpedizioneBean> indirizziCliente = (Collection<IndirizzoSpedizioneBean>) request.getAttribute("indirizziCliente") ;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Indirizzi Cliente</title>
</head>
<body>

<%@include file="common/header.jsp" %>
<div class="row main-container">

<div class="first-container">
<%@ include file="common/account-vertical-menu.jsp" %>
</div>

<div class="second-container">
<div class="title">
<h2><strong>Indirizzi </strong></h2>
<span>Visualizza e gestisci i tuoi indirizzi.</span>
</div>
<div class="row info">

<div class="addAddress" data-bs-toggle="modal" data-bs-placement="top" title="Aggiungi Indirizzo"
data-bs-target="#addAddressModal">
<div>
<i class="icon-address bi bi-plus-circle-fill"></i>
<h6><strong>Aggiungi Indirizzo</strong></h6>
</div>
</div>

<%if(indirizziCliente != null && indirizziCliente.size()!= 0) {

	int nIndirizzo = 0 ;
	Iterator<IndirizzoSpedizioneBean> iterator = indirizziCliente.iterator() ;
	
	while(iterator.hasNext()) { 
		IndirizzoSpedizioneBean indirizzo = (IndirizzoSpedizioneBean) iterator.next() ;
		nIndirizzo = nIndirizzo + 1 ;
		
		
%>

<div class="address">

<div class="titolo">
<h5>Indirizzo Spedizione</h5>
</div>

<div class="titolare">
<h4><%=indirizzo.getResidente().getNome()%> <%=indirizzo.getResidente().getCognome()%></h4>
</div>

<div class="col informazioni">
<h6><%=indirizzo.getVia()%>,<%=indirizzo.getnCivico()%></h6>
<h6><%=indirizzo.getCittà()%>,<%=indirizzo.getProvincia()%></h6>
<h6><%=indirizzo.getNazione()%></h6>
</div>

<div class="row pulsanti">

<div class="delete justify-content-start align-items-center" style="width:50px;height:50px;">
<form id="<%="rimuoviIndirizzo"%><%=nIndirizzo%>" action="indirizzi" method="POST">
<input type="hidden" name="action" value="remove">
<input type="hidden" name="residenteIdRem" value="<%=indirizzo.getResidente().getId()%>">
<input type="hidden" name="viaRem" value="<%=indirizzo.getVia()%>">
<input type="hidden" name="provinciaRem" value="<%=indirizzo.getProvincia()%>">
<input type="hidden" name="cittaRem" value="<%=indirizzo.getCittà()%>"  >
<input type="hidden" name="nCivicoRem" value="<%=indirizzo.getnCivico()%>">
<input type="hidden" name="capRem" value="<%=indirizzo.getCap()%>">
<input type="hidden" name="nazioneRem" value="<%=indirizzo.getNazione()%>">
</form>
<a class="btn-addr" onClick="document.getElementById('<%="rimuoviIndirizzo"%><%=nIndirizzo%>').submit();"><i class="icon-address bi bi-trash-fill"></i></a>
</div>

<div class="modifica justify-content-start align-items-center">
<a data-bs-toggle="modal" data-bs-placement="top" title="Aggiungi Indirizzo"
data-bs-target="#addAddressModal" class="row justify-content-start align-items-center btn-addr">
<i class="col-3 icon-address bi bi-pencil-fill"></i><span class="col-7"><strong>Modifica</strong></span></a>
</div>

</div>

</div>

<%}%>

	<%}%>
</div>

</div>
</div>

<%@ include file="modal/aggiungi-indirizzo.jsp" %>
<%@ include file="common/footer.jsp" %>

</body>
</html>