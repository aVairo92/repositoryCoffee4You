<%
CategoriaProdottoBean categoria = (CategoriaProdottoBean) request.getAttribute("categoria") ;
List<ProdottoBean> prodottiCategoria = (List<ProdottoBean>) request.getAttribute("prodottiCategoria") ;
%>

<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" 
    import="java.util.*,it.unisa.model.beans.ProdottoBean,it.unisa.model.beans.CategoriaProdottoBean"%>
<head>
<meta http-equiv="Content-type" charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=categoria.getNomeCategoria()%></title>
</head>
<body>
<%@ include file="common/header.jsp" %>

<div class="main-container-navbar">

<div class="descrizione-categoria-container row row-cols-1 row-cols-sm-2 row-cols-md-2 row-cols-lg-2 row-cols-xl-3 g-2 justify-content-start align-items-center">

<div class="image-cat-container">
<img src="images/<%=categoria.getUrlImmagine1()%>" class="img-cat img-fluid" alt="<%=categoria.getUrlImmagine1()%>">
</div>

<div class="text-cat-container">
<p id="descr-cat">"<%=categoria.getDescrizione()%>"</p>
</div>

<div id="img2" class="image-cat-container">
<img src="images/<%=categoria.getUrlImmagine2()%>" class="img-cat img-fluid" alt="<%=categoria.getUrlImmagine2()%>">
</div>

</div>

<div class="pre-catalogo-categoria d-flex d-row justify-content-start align-items-start">
<div class="d-flex d-row justify-content-start align-items-start">
<h3><%=categoria.getNomeCategoria()%></h3>
<h4>(<%=prodottiCategoria.size()%>)</h4>
</div>
<a class="btn btn-primary" href="#" role="button">Fai la tua scelta</a>
</div>

<div class="product-categoria-container">
<%
if(prodottiCategoria!=null && prodottiCategoria.size() != 0) {
%>
<div class="row row-cols-1 row-cols-sm-1 row-cols-md-2 row-cols-lg-auto g-2 justify-content-center">
<%
Iterator<ProdottoBean> iterator = prodottiCategoria.iterator() ;
while(iterator.hasNext()) { 
	ProdottoBean prod = (ProdottoBean) iterator.next() ;
%>	
<div class="card card-catalogo">
  <div class="card-head">
  <a class="nome-prod-cat text-center" href="carrello?action=dettagli&id=<%=prod.getId()%>" target="_blank"><%=prod.getNomeProdotto()%></a>
  </div>
  <h4 class="prezzo-card text-center border border-3 border-danger">Euro <%=prod.getPrezzoProdotto()%></h4>
  <div class="card-img">
  <a href="carrello?action=dettagli&id=<%=prod.getId()%>" target="_blank"><img src="images/<%=prod.getUrlImmagine()%>" class="img-fluid img-card-catalogo card-img-top" alt="<%=prod.getNomeProdotto()%>"></a>
  </div>
  <div class="card-body">  
  <form action="carrello">
  <input type="hidden" name="action" value="add">
  <input type="hidden" name="id" value="<%=prod.getId()%>">
  <label class="label-qnt" for="quantity">Quantità:</label>
  <input class="form-qnt" type="number" name="quantity" min="1" max="<%=prod.getDisponibilità()%>">
  <input class="form-btn border border-danger" type="submit" value="Add to Cart">
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

<%@ include file="common/footer.jsp" %>

</body>
</html>