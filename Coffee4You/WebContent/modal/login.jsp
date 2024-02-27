<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="loginModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content home">
      
      <div class="modal-header log">
        <h4 class="modal-title" id="staticBackdropLabel">Login</h4>
           <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i></button>
      </div>
      
<div class="modal-body">

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
<input class="btn btn-primary" type="submit" value="Login">
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