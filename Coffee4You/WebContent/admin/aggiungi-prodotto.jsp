<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi Prodotto</title>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="aggiungiProdottoModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content admin-sezione">
      
<div class="modal-header log">
<h4 class="modal-title" id="staticBackdropLabel">Aggiungi Prodotto</h4>
<button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i></button>
</div>

<div class="modal-body">
<form name="formAggiungiProdotto" id="formAggiungiProdotto" action="aggiungiProdotto" method ="GET">

<div class="info">
<div class="intestazione">
<h4><strong>Info Categoria</strong></h4>
</div>

<div class="campo">
<i class="bi bi-shop"></i>
<input class="login-input" type="text" name="idCategoria" id="idCategoria" placeholder="Id Categoria">
<div class="idCat-msg"></div>
</div>

<div class="campo">
<i class="bi bi-shop"></i>
<input class="login-input" type="text" name="nomeCategoria" id="nomeCategoria" placeholder="Nome Categoria">
<div class="nomeCat-msg"></div>
</div>

<div class="campo">
<i class="bi bi-shop"></i>
<input class="login-input" type="text" name="descrizioneCategoria" id="descrizioneCategoria" placeholder="Descrizione Categoria">
<div class="desCat-msg"></div>
</div>

</div>
<div class="info">

<div class="intestazione">
<h4><strong>Info Prodotto</strong></h4>
</div>

<div class="campo">
<i class="bi bi-stack"></i>
<input class="login-input" type="text" name="nomeProdotto" id="nomeProdotto" placeholder="Nome Prodotto">
<div class="nomeProd-msg"></div>
</div>

<div class="campo">
<i class="bi bi-stack"></i>
<input class="login-input" type="text" name="descrizioneProdotto" id="descrizioneProdotto" placeholder="Descrizione Prodotto">
<div class="descrizioneProd-msg"></div>
</div>

<div class="campo">
<i class="bi bi-shop"></i>
<input class="login-input" type="text" name="marcaProdotto" id="marcaProdotto" placeholder="Marca Prodotto">
<div class="marcaProd-msg"></div>
</div>

<div class="campo">
<i class="bi bi-123"></i>
<input class="login-input" type="text" name="disponibilitaProdotto" id="disponibilitaProdotto" placeholder="Disponibilità Prodotto">
<div class="disponibilitaProd-msg"></div>
</div>

<div class="campo">
<i class="bi bi-tag-fill"></i>
<input class="login-input" type="text" name="prezzoProdotto" id="prezzoProdotto" placeholder="Prezzo Prodotto">
<div class="prezzoProd-msg"></div>
</div>

<div class="campo">
<i class="bi bi-percent"></i>
<input class="login-input" type="text" name="ivaProdotto" id="ivaProdotto" placeholder="Iva Prodotto">
<div class="ivaProd-msg"></div>
</div>

<div class="campo">
<i class="bi bi-card-image"></i>
<input class="login-input" type="text" name="urlProdotto" id="urlProdotto" placeholder="Url Immagine Prodotto">
<div class="urlProd-msg"></div>
</div>

<div class="campo">
<i class="bi bi-gift-fill"></i>
<label class="sesso"><b>Promo:</b></label>
<label class="sesso"><input type="radio" name="promo" value="false">Si</label>
<label class="sesso"><input type="radio" name="promo" value="true">No</label>
</div>

</div>

<div class="login d-flex d-row text-center">
<div class="col">
<input class="allowed-submit btn btn-primary" type="submit" value="Aggiungi" id="submit-addProduct" disabled="disabled">
</div>
<div class="col">
<input id="reset-addProduct" class="btn btn-primary" type="reset" value="Reset">
</div>
</div>

</form>

</div>
</div>  
</div>
</div>
</body>
</html>