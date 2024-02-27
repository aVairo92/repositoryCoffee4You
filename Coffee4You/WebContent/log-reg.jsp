<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" charset="ISO-8859-1">
<title>Accedi</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/firstStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<div class="ms-5 logo-container">
<a class="navbar-brand" href="/Coffee4You/home.jsp">
<img src="images/logo2.png" alt="logo">
</a>
</div>

<div class="container-generale-logreg">

<div id="btn-accedi" class="btn-container">
<button class="btn-logreg">Accedi</button>
</div>

<div id="section-log" class="log-reg-container">

<h3 class="fw-bold">Bello rivederti!</h3>

<form class="login" action="login" method="POST">

<div class="campo">
<i class="bi bi-person-fill"></i><input class="login-input" type="text" name="username" placeholder="Username" required>
</div>
<div class="campo">
<i class="bi bi-lock-fill"></i>
<input class="login-input" type="password" name="password" placeholder="Password" required>
</div>

<div class="login row">
<div class="col-sm">
<input class="btn-logreg" type="submit" value="Accedi">
</div>
</div>

</form>

</div>

<div class="separatore"></div>

<div id="btn-registrati" class="btn-container">
<button class="btn-logreg">Registrati</button>
</div>

<div id="section-reg" class="log-reg-container">

<h3 class="fw-bold">Registrati</h3>

 <form name="formRegistrazione" id="formRegistrazione" action="registrazione" method="POST">
      
      <div class="intestazione">
      <h4><strong>Info cliente</strong></h4>
      </div>
         
         <div class="campo">
         <i class="bi bi-person"></i>
         <input class="login-input" type="text" name="nome" id="nome" placeholder="Nome">
         <div class="nome-msg"></div>
         </div>
         
         <div class="campo">
         <i class="bi bi-person"></i>
         <input class="login-input" type="text" name="cognome" id="cognome" placeholder="Cognome">
         <div class="cognome-msg"></div>
         </div>
         
         <div class="campo">
         <i class="bi bi-gender-ambiguous"></i>
         <label class="sesso"><b>Sesso:</b></label>
         <label class="sesso"><input type="radio" name="sesso" value="M">Maschile</label>
         <label class="sesso"><input type="radio" name="sesso" value="F">Femminile</label>
         </div>
         
         <div class="campo">
         <i class="bi bi-card-heading"></i>
         <input class="login-input" type="text" name="codiceFiscale" id="codiceFiscale" placeholder="Codice Fiscale">
         <div class="codiceFiscale-msg"></div>
         </div>
         
         <div class="intestazione">
         <h4><strong>Info utenza</strong></h4>
         </div>
         
         <div class="campo">
         <i class="bi bi-person-fill"></i>
         <input class="login-input" type ="text" name="username" id="username" placeholder="Username">
         <div class="username-msg"></div>
         </div>
         
         <div class="campo">
         <i class="bi bi-key-fill"></i>
         <input class="login-input" type="password" name="password" id="password" placeholder="Password">
         <div class="password-msg"></div>
         </div>
         
         <div class="campo">
         <i class="bi bi-key-fill"></i>
         <input class="login-input" type ="password" name="confermaPassword" id="confermaPassword" placeholder="Conferma Password">
         <div class="confermaPassword-msg"></div>
         </div>
         
         <div class="campo">
         <i class="bi bi-at"></i>
         <input class="login-input" type="text" name="email" id="email" placeholder="Email">
         <div class="email-msg"></div>
         </div>
                 
         <div class="campo">
         <i class="bi bi-telephone-fill"></i>
         <input class="login-input" type ="text" name="telefono" id="telefono" placeholder="N°Telefono">
         <div class="telefono-msg"></div>
         </div>
         
          <div class="login row">
             <div class="col-sm">
             <input class="allowed-submit btn-logreg" type="submit" value="Registrati" id="submit-btn" disabled="disabled">
             </div>
          </div>
         </form>   
         
</div>

</div>

<%@include file="common/footer.jsp"%>
</body>
</html>