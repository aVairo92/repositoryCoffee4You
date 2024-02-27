<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%

ProdottoBean prodotto = (ProdottoBean) request.getAttribute("prodotto") ;
List<ProdottoBean> listaProdottiCategoria =(List<ProdottoBean>) request.getAttribute("prodottiCategoria");
Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");

int quantitàNelCarrello = 0 ;

if(carrello != null) {
for(ProdottoQuantita p : carrello.getProdotti()) {
	if(p.getProdotto().getId() == prodotto.getId()) {
		quantitàNelCarrello = p.getQuantita() ;
		}
	}
}

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
<title><%=prodotto.getNomeProdotto()%></title>
</head>
<body>

<%@include file="common/header.jsp" %>

<div class="container-generale row justify-content-around align-items-start">

<div class="container-product-image">
<div class="detail-product-img-container">
<img class="detail-product-img" src="<%="images/"+prodotto.getUrlImmagine()%>" alt="ImmagineProdotto">
</div>
</div>

<div class="container-product-details">

<div class="info-product-container">
<h2 class="m-0 text-decoration-underline"><%=prodotto.getMarcaProdotto()%></h2>
<h1 class="m-0"><%=prodotto.getNomeProdotto()%></h1>
<h3 class="m-0"><%=prodotto.getPrezzoProdotto()%> Euro</h3>
<h3 class="m-0 elimina">Iva:<%=prodotto.getIvaProdotto()%>%</h3>
</div>

<div class="info-product-container">
<p class="details-descrizione">" <%=prodotto.getDescrizioneProdotto()%> "</p>
</div>

<div class="info-product-container">

<form name="addToCart" id="addToCart" action="carrello" method="POST">
  <input type="hidden" name="action" value="add">
  <input type="hidden" name="id" value="<%=prodotto.getId()%>">
  <select name="quantity" id="quantity" class="btn-qnt">
  <option value="" disabled="disabled">Seleziona quantità</option>
  <%for(int i=1;i<=(prodotto.getDisponibilità() - quantitàNelCarrello);i++) { %>
  <option value="<%=i%>"><%=i%></option>
  <%}%>
  </select>
  <input id="submit-addToCart" class="btn-checkout-det text-decoration-none" type="submit" value="AGGIUNGI AL CARRELLO">
</form>

</div>

<div class="row consegna-product-container">
<div>
<i class="bi bi-truck fs-3"></i>
</div>
<div class="col-12 row justify-content-beetween">
<div class="col-6"><h6 class="fw-bold">Tempi Consegna</h6></div>
<div class="col-6"><h6>1-3 giorni lavorativi</h6></div>
</div>
<div class="col-12 row justify-content-beetween">
<div class="col-6"><h6 class="fw-bold">Consegna Standard</h6></div>
<div class="col-6"><h6>6.99 Euro</h6></div>
</div>
<div class="col-12 row justify-content-beetween">
<div class="col-6"><h6 class="fw-bold">Consegna Premium</h6></div>
<div class="col-6"><h6>0.00 Euro</h6></div>
</div>
</div>

<div class="row consegna-product-container">
<div>
<i class="bi bi-truck fs-3"></i>
</div>
<div class="col-12 row justify-content-beetween">
<div class="col-6"><h6 class="fw-bold">Consegna prevista</h6></div>
<div class="col-6"><h6><%=dConsegna%></h6></div>
</div>

<%if(carrello.calcolaTotale() >= 25) { %>
<div class="col-12 row justify-content-beetween">
<div class="col-6"><h6 class="fw-bold">Spedizione</h6></div>
<div class="col-6"><h6>0.00 Euro</h6></div>
</div>

<% } else { %>
<div class="col-12 row justify-content-beetween">
<div class="col-6"><h6 class="fw-bold">Spedizione</h6></div>
<div class="col-6"><h6>6.99 Euro</h6></div>
</div>
<div class="col-12 row justify-content-beetween">
<a class="text-decoration-none elimina fw-bold" href="catalogo">Aggiungi articoli</a>
</div>

<%}%>
</div>

</div>
</div>

<div class="container-generale row justify-content-around align-items-start">
<h1 class="fw-bold">Prodotti simili</h1>
<h2 class="fw-bolder">Prova anche:</h2>
<div class="container-prodInPromo">
<ul class="listaProdInPromo">
<%
Iterator<ProdottoBean> iterator = listaProdottiCategoria.iterator() ;
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

<%@include file="common/footer.jsp" %>
</body>
</html>