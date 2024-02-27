<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,it.unisa.model.beans.ProdottoBean,it.unisa.model.beans.CategoriaProdottoBean"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" charset="ISO-8859-1">
<title>Header</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/firstStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>

<body>

<nav class="navbar navbar-expand-md bg-light">

<div class="main-container-navbar row">

<div class="logo-container row">
<a class="navbar-brand" href="/Coffee4You/home.jsp"><img src="images/logo2.png" alt="logo"></a>
</div>

<div class="tgl-btn-container">
<button class="navbar-toggler tgl-btn" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation"><i class="bi bi-card-text"></i></button>
</div>

<div class="container-category collapse navbar-collapse" id="navbarSupportedContent">

<ul class="navbar-nav">

<li class="nav-item"><div class="dropdown">
<a  class="nav-link btn btn-primary section2 dropdown-toggle"
	href="#" role="button" id="dropdownMenuLink"
	data-bs-toggle="dropdown" aria-expanded="false">Caffe'</a>
	<ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
		<li><a class="dropdown-item" href="categoria?nomeCategoria=Beans">Caffe' in grani</a></li>
		<li><a class="dropdown-item" href="categoria?nomeCategoria=Ground">Caffe' macinato</a></li>
	</ul>
			         </div>
</li>
<li class="nav-item"><a class="nav-link btn btn-primary section2" href="categoria?nomeCategoria=Cialde">Cialde</a></li>
<li class="nav-item"><a class="nav-link btn btn-primary section2" href="categoria?nomeCategoria=Capsule">Capsule</a></li>
<li class="nav-item"><a class="nav-link btn btn-primary section2" href="categoria?nomeCategoria=Macchine">Macchine</a></li>
<li class="nav-item"><a class="nav-link btn btn-primary section2" href="categoria?nomeCategoria=Accessori">Accessori</a></li>
<li class="nav-item"><a class="nav-link btn btn-primary section2" href="catalogo">Catalogo</a></li>
</ul>

</div>

<div id="search-div" class="container-searchbar">

<form class="searchbar-form" action="prodotto" method="POST">
<div>
<input type="hidden" name="action" value="search">
<input class="form-control me-2" id="searchBar" name="nomeRicerca" type="search" placeholder="Ricerca" autocomplete="off" aria-label="Ricerca">
</div>

<div id="show-suggest" class="notActive"></div>

</form>
</div>



<div class="container-icon row">

<ul class="icone-menu navbar-nav">
<li class=" icon-li nav-item"><a class="nav-link" data-bs-toggle="tooltip" data-bs-placement="top" title="Carrello"
    href="carrello"><i class="bi bi-cart-fill"></i></a></li>

					<%
						if ((session.getAttribute("clienteLoggato") != null) || (session.getAttribute("adminLoggato") != null)) {
					%>
					<li class="icon-li nav-item"><a class="nav-link"
						data-bs-toggle="tooltip" data-bs-placement="top" title="IlMioAccount"
						href="account"><i class="bi bi-person-circle"></i></a></li>
					<li class="icon-li nav-item"><a class="nav-link"
						data-bs-toggle="tooltip" data-bs-placement="top" title="Logout"
						href="logout"><i class="bi bi-x-circle-fill"></i></a></li>

					<%
						} else {
					%>

					<li class="icon-li nav-item"><a class="nav-link"
						data-bs-toggle="modal" data-bs-placement="top" title="Login"
						data-bs-target="#loginModal"><i class="bi bi-door-open-fill"></i></a></li>
					<li class=" icon-li nav-item"><a class="nav-link"
						data-bs-toggle="modal" data-bs-placement="top" title="Registrati"
						data-bs-target="#registrazioneModal"><i
							class="bi bi-person-plus-fill"></i></a></li>

					<%
						}
					%>
				</ul>
				</div> 
								
			</div>
						
</nav>
	
	<%@include file="../modal/login.jsp"%>
	<%@include file="../modal/registrazione.jsp"%>
</body>
</html>