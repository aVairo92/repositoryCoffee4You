<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="registrazioneModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content home">
      <div class="modal-header log">
        <h4 class="modal-title" id="exampleModalLabel">Registrazione</h4>
        <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i></button>
      </div>
      <div class="modal-body">
      
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
             <input class="allowed-submit btn btn-primary" type="submit" value="Registrati" id="submit-btn" disabled="disabled">
             </div>
             <div class="col-sm">
             <input id="reset-btn" class="btn btn-primary" type="reset" value="Reset">
             </div>
             <div class="col-sm">
             <a class="btn btn-primary" href="/Coffee4You/home.jsp">Annulla</a>
             </div>
          </div>
         </form>   
       </div>
     </div>
  </div>
</div>
</body>
</html>