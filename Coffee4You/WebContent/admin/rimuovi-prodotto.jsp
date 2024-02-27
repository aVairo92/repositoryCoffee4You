<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Rimuovi Prodotto</title>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="rimuoviProdottoModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content admin-sezione">
     
<div class="modal-header log">
<h4 class="modal-title" id="staticBackdropLabel">Rimuovi Prodotto</h4>
<button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i></button>
</div>

<div class="modal-body">
<form action="rimuoviProdotto" method="GET">
<div class="campo">
<i class="bi bi-person"></i>
<input class="login-input" type="text" name="idProdotto" id="idProdotto" placeholder="Id Prodotto">
<div class="idCat-msg"></div>
</div>
<input type="submit" value="Rimuovi">
</form>

</div>
</div>  
</div>
</div>

</body>
</html>