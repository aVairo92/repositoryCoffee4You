<%
Collection<ClienteBean> clienti =(Collection<ClienteBean>) request.getAttribute("clienti") ;
Collection<OrdineBean> listaOrdini = (Collection<OrdineBean>) request.getAttribute("ordiniDaVisualizzare") ;
%>
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.util.*,it.unisa.model.beans.ClienteBean,it.unisa.model.beans.OrdineBean,it.unisa.model.cart.ProdottoQuantita"%>
<head>
<meta charset="ISO-8859-1">
<title>Ordini</title>
</head>
<body>

<%@ include file="../common/header.jsp" %>

<div class="row main-container">

<div class="first-container">
<%@ include file="../common/account-vertical-menu.jsp" %>
</div>

<div class="second-container">

<div class="filtro-ordine">
<h1>Lista Ordini</h1>
<form action="ordini" method="POST">
<div id="selectClient">
<label for="usernameCliente">Cliente:</label>
<select name="usernameCliente" id="usernameCliente">
<option value=""></option>
<%
for(ClienteBean cliente : clienti){
%>
<option value="<%=cliente.getUsername()%>"><%=cliente.getUsername()%></option>
<%
}
%>
</select>
</div>
<label>Date to:<input type="date" name="after"></label>
<label>Date from:<input type="date" name="before"></label>
<div class="btn-container-filtro">
<input class="btn-filtro" type="submit" value="Visualizza">
<input class="btn-filtro" type="reset" value="Reset">
</div>
</form>
</div>

<%if(listaOrdini != null && listaOrdini.size() != 0) { 
	Iterator<OrdineBean> iterator = listaOrdini.iterator() ;
	while(iterator.hasNext()) { 
		OrdineBean ordine = iterator.next() ;
%>
<div class="border border-dark main-ordine flex row">

<div class="id-container">
<h3>Numero Ordine: <strong><%=ordine.getId()%></strong></h3>
</div>

<div class="info-ordine-container row">

<div class="col-sm-3 col-6">
<h6>Nome Acquirente:</h6>
<h6><strong><%=ordine.getAcquirente().getNome()%> <%=ordine.getAcquirente().getCognome()%></strong></h6>
</div>

<div class="col-sm-3 col-6">
<h6>Data Emissione:</h6>
<h6><strong><%=ordine.getDataEmissione()%></strong></h6>
</div>

<div class="col-sm-2 col-6">
<h6>Spesa totale:</h6>
<h6><strong><%=ordine.getTotaleSpesa()%></strong></h6>
</div>

<div class="col-sm-4 col-6">
<a class="text-decoration-none"  href="ordini?action=dettagli&id=<%=ordine.getId()%>" target="_blank"><button class="btn-checkout">DETTAGLI</button></a>
</div>

</div>

<div class="prod-ordine-container row">
<%List<ProdottoQuantita> pq = ordine.getProdottiOrdine();
if(pq != null && pq.size() != 0) {
for(ProdottoQuantita p : pq) { %>
<div class="cart-img-container col-2">
<img class="cart-img" src="images/<%=p.getImg()%>">
</div>
<%		}
}
%>
</div>

</div>

<%} 
	}else {%>

<div class="empty-cart">
<div class="empty-cart-img">
<img src="images/shopping-cart.jpg" class="img-fluid img-card-catalogo card-img-top" alt="Cart-Empty">
</div>

<h3>Nessun ordine effetuato.</h3>

<div class="row empty-cart-btn">
<a class="col-12 text-decoration-none"  href="catalogo"><button class="btn-checkout">CATALOGO</button></a>
</div>
</div>

<%}%>

</div>
</div>

<%@ include file="../common/footer.jsp" %>
</body>
</html>