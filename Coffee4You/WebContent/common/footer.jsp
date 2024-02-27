<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Footer</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/firstStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<meta name="viewport" content="width=device-width,initial-scale=1" />
</head>
<body>
  <footer>
    <div class="row">
      <div class="col-2 offset-2">
        <h4>Coffee4You</h4>
        <ul class="nav flex-column">
          <li class="nav-item mb-2"><a href="/Coffee4You/home.jsp" class="nav-link p-0 btn-footer">Home</a></li>
          <li class="nav-item mb-2"><a href="catalogo" class="nav-link p-0 btn-footer">Catalogo</a></li>
          <li class="nav-item mb-2"><a href="#" class="nav-link p-0 btn-footer">FAQs</a></li>
          <li class="nav-item mb-2"><a href="#" class="nav-link p-0 btn-footer">About</a></li>
        </ul>
      </div>
      
      <div class="col-2 offset-4">
        <h4>Catalogo</h4>
        <ul class="nav flex-column">
          <li class="nav-item mb-2"><a href="categoria?nomeCategoria=Beans" class="nav-link p-0 btn-footer">Caffe in Grani</a></li>
          <li class="nav-item mb-2"><a href="categoria?nomeCategoria=Ground" class="nav-link p-0 btn-footer">Caffe in Polvere</a></li>
          <li class="nav-item mb-2"><a href="categoria?nomeCategoria=Cialde" class="nav-link p-0 btn-footer">Cialde</a></li>
          <li class="nav-item mb-2"><a href="categoria?nomeCategoria=Capsule" class="nav-link p-0 btn-footer">Capsule</a></li>
          <li class="nav-item mb-2"><a href="categoria?nomeCategoria=Macchine" class="nav-link p-0 btn-footer">Macchine</a></li>
          <li class="nav-item mb-2"><a href="categoria?nomeCategoria=Accessori" class="nav-link p-0 btn-footer">Accessori</a></li>
        </ul>
      </div>

    </div>

    <div class="d-flex justify-content-between align-items-baseline py-auto my-auto border-top">
      <p class="d-flex">&copy; 2022 C4Y, Inc. All rights reserved.</p>
      <ul class="list-unstyled d-flex">
        <li class="ms-3"><a class="btn-footer" href="#"><i class="bi bi-twitter" ></i></a></li>
        <li class="ms-3"><a class="btn-footer link-dark" href="#"><i class="bi bi-facebook"></i></a></li>
        <li class="ms-3"><a class="btn-footer link-dark" href="#"><i class="bi bi-instagram"></i></a></li>
      </ul>
    </div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="https://unpkg.com/@popperjs/core@2/dist/umd/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="script/logreg.js" type="text/javascript"></script>
<script src="script/validaDettagliProdotti.js" type="text/javascript"></script>
<script src="script/validaRegistrazione.js" type="text/javascript"></script>
<script src="script/validaAggiungiProdotto.js" type="text/javascript"></script>
<script src="script/scriptCatalogo.js" type="text/javascript"></script>
<script src="script/validaAggiungiIndirizzo.js" type="text/javascript"></script>
<script src="script/validaAggiungiCarta.js" type="text/javascript"></script>
<script src="script/attivaRicerca.js" type="text/javascript"></script>
<script src="script/checkUsername.js" type="text/javascript"></script>
<script src="script/ajaxCallFunction.js" type="text/javascript"></script>

</footer>
</body>
</html>