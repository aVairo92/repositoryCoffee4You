<%
Carrello carrello = (Carrello) request.getAttribute("carrello");
List<ProdottoBean> listaProdotti =(List<ProdottoBean>) request.getAttribute("prodotti");
Collection<CategoriaProdottoBean> listaCategoriaProdotti =(Collection<CategoriaProdottoBean>) request.getAttribute("categorie");

AmministratoreBean adminLoggato = (AmministratoreBean) request.getSession().getAttribute("adminLoggato") ;
Boolean isAdminLoggato = false ;
if((Boolean) request.getSession().getAttribute("isAdminLoggato") != null ){
	isAdminLoggato = true ;
}
%>    
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
import="java.util.*,it.unisa.model.beans.ProdottoBean,it.unisa.model.beans.CategoriaProdottoBean,it.unisa.model.beans.AmministratoreBean,it.unisa.model.cart.*"%>
<head>
<meta http-equiv="Content-type" charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Catalogo</title>
</head>
<body>
<script src="https://unpkg.com/@popperjs/core@2"></script>

<%@ include file="common/header.jsp" %>

<div class="main-container-navbar">

<div class="container-fluid catalogo-container">
<div class="catalogo-sidebar-prodotti row g-2 align-item-start justify-content-start">
<div class="pre-catalogo col-12 d-flex d-row justify-content-start align-items-start">
<div class="col-4 d-flex d-row justify-content-start align-items-start">
<h3 class="small-size">Catalogo</h3>
<h4 class="small-size">(<%=listaProdotti.size()%>)</h4>
</div>
<div class="col-8 d-flex d-row justify-content-end align-items-start">
<a id="btn-filtra" class="btn btn-filtro" data-bs-toggle="collapse" aria-expanded="false" aria-controls="#sidebarCatalogo" href="#sidebarCatalogo" role="button">Filtro</a>

<div class="dropdown">
  <button class="btn btn-filtro dropdown-toggle" type="button" id="ordinaMenu" 
  data-bs-toggle="dropdown" aria-expanded="false">Ordina</button>
  
  <ul class="dropdown-menu ordina-menu text-end" aria-labelledby="ordinaMenu">
    <li><a class="dropdown-item text-center" href="catalogo?action=ordinaPrezzo">Prezzo</a></li>
    <li><a class="dropdown-item text-center" href="catalogo">Marca</a></li>
  </ul>   
  </div>
</div>
</div>

<%--INIZIO SEZIONE ADMIN --%>
<%
if(isAdminLoggato == true && adminLoggato != null) {
%>
<div class="sezione-admin col-12 d-flex d-row justify-content-start align-items-start">

<div class="col-12 d-flex d-row justify-content-start align-items-start">
<a class="btn btn-filtro" data-bs-toggle="modal" data-bs-placement="top" title="AggiungiProdotto" data-bs-target="#aggiungiProdottoModal">Aggiungi Prodotto</a>
<a class="btn btn-filtro" data-bs-toggle="modal" data-bs-placement="top" title="RimuoviProdotto" data-bs-target="#rimuoviProdottoModal">Rimuovi Prodotto</a>
<a class="btn btn-filtro" data-bs-toggle="modal" data-bs-placement="top" title="AggiornaDisponibilita" data-bs-target="#aggiornaDisponibilitaModal">Aggiorna Disponibilità</a>
</div>

</div>
<%}%>
<%--FINE SEZIONE ADMIN --%>

<div id="sidebarCatalogo" class="catalogo-sidebar col-sm-12 col-md-2">
  <div class="sidebarCat">
<h3 class="small-size">Filtra per:</h3>

<form action="catalogo" method="POST">
<input type="hidden" name="action" value="addFiltro">
  
<div class="box-filtro">
<h4 class="small-size">Categoria:</h4>
<%
if(listaCategoriaProdotti!=null && listaCategoriaProdotti.size() != 0) {
	Iterator<CategoriaProdottoBean> iteratorCat = listaCategoriaProdotti.iterator() ;
	while(iteratorCat.hasNext()) { 
		CategoriaProdottoBean cat = (CategoriaProdottoBean) iteratorCat.next() ;
%>
<div class="form-check">
  <input class="form-check-input" type="checkbox" name="categorieSelezionate" value="<%=cat.getNomeCategoria()%>" id="<%=cat.getNomeCategoria()%>">
  <label class="form-check-label" for="<%=cat.getNomeCategoria()%>"><%=cat.getNomeCategoria()%></label>
</div>  
<%}
}%>
</div>
    
<div class="box-filtro">
<h4 class="small-size">Marca:</h4>

<div class="form-check">
  <input class="form-check-input" type="checkbox" name="marcheSelezionate" value="Borbone" id="borbone">
  <label class="form-check-label" for="borbone">Borbone</label>
</div>

<div class="form-check">
  <input class="form-check-input" type="checkbox" name="marcheSelezionate" value="Nespresso" id="nespresso">
  <label class="form-check-label" for="nespresso">Nespresso</label>
</div>

<div class="form-check">
  <input class="form-check-input" type="checkbox" name="marcheSelezionate" value="Illy" id="illy">
  <label class="form-check-label" for="illy">Illy</label>
</div>
</div>

<div class="box-filtro">
<h4 class="small-size">Prezzo:</h4>
<input id="range" class="form-range" type="range" name="prezzoMax" min="0" max="1000" step="1">
<h6 id="valBox">Euro 0</h6>
</div>

<div class="box-filtro">
<button class="btn btn-filtro" type="submit">Mostra risultati</button>
<button class="btn btn-filtro" type="reset">Reset</button>
</div>
</form>

  </div>
</div>

<div class="catalogo-product-container col-md-10">
<%
if(listaProdotti!=null && listaProdotti.size() != 0) {
%>
<div class="row row-cols-lg-auto g-2 justify-content-center">
<%
Iterator<ProdottoBean> iterator = listaProdotti.iterator() ;
while(iterator.hasNext()) { 
	ProdottoBean prod = (ProdottoBean) iterator.next() ;
	int disponibilità = prod.getDisponibilità();
	
	if(carrello != null) {
	for(ProdottoQuantita p : carrello.getProdotti()) {
		if(p.getProdotto().getId() == prod.getId()) {
			disponibilità = disponibilità - p.getQuantita() ;
			}
		}
	}
%>	
<div class="card card-catalogo">
  <div class="card-head">
  <a class="nome-prod-cat text-center" href="carrello?action=dettagli&id=<%=prod.getId()%>" target="_blank"><%=prod.getNomeProdotto()%></a>
  <%if(isAdminLoggato && adminLoggato != null) {%>
  <h5>Id:<%=prod.getId()%></h5>
  <%}%>
  </div>
  <h4 class="prezzo-card text-center border border-3 border-danger">Euro <%=prod.getPrezzoProdotto()%></h4>
  <div class="card-img">
  <a href="carrello?action=dettagli&id=<%=prod.getId()%>" target="_blank"><img src="images/<%=prod.getUrlImmagine()%>" class="img-fluid img-card-catalogo card-img-top" alt="<%=prod.getNomeProdotto()%>"></a>
  </div>
  <div class="card-body">  
  <form action="carrello" method="POST">
  <input type="hidden" name="action" value="add">
  <input type="hidden" name="id" value="<%=prod.getId()%>">
  <%if(disponibilità <= 0) { %>
  <h6>SOLD OUT</h6>
  <input class="form-btn-disabled" type="submit" disabled value="Add to Cart">
  <%} else {%>
  <label class="label-qnt" for="quantity">Quantità:</label>
  <input class="form-qnt" type="number" name="quantity" min="1" max="<%=disponibilità%>">
  <input class="form-btn" type="submit" value="Add to Cart">
  <%}%>
  </form>
  </div>
  </div>
<% }
} else { %>
<h2>NON CI SONO PRODOTTI NEL CATALOGO!!!</h2>	
<%
}
%>
</div>
</div>
</div>
</div>

</div>

<%@include file="../admin/aggiungi-prodotto.jsp" %>
<%@include file="../admin/rimuovi-prodotto.jsp" %>
<%@include file="../admin/aggiorna-disponibilita.jsp" %>
<%@ include file="common/footer.jsp" %> 
</body>
</html>