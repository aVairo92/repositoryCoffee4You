<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.beans.ClienteBean"%>
<%
ClienteBean titolare =(ClienteBean) request.getSession().getAttribute("clienteLoggato") ;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi Carta</title>
</head>
<body>

<!-- Modal -->
<div class="modal fade" id="addCartaModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content addressModal">
      
      <div class="modal-header">
        <h4 class="modal-title" id="staticBackdropLabel">Aggiungi Carta</h4>
           <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i></button>
      </div>
      
<div class="modal-body">

<form id="formAddCarta" action="carte" method="POST">
<input type="hidden" name="action" value="add">
<input type="hidden" name="check" value="true">
<input type="hidden" name="titolareId" value="<%=titolare.getId()%>">

<div class="campo">
<i class="bi bi-0-circle"></i>
<input class="login-input" type="text" name="codiceCarta" id="codiceCarta" placeholder="Numero Carta">
<div class="nCarta-msg"></div>
</div>

<div class="campo">
<i class="bi bi-0-circle"></i>
<input class="login-input" type="date" name="dataScadenza" id="dataScadenza" placeholder="Data Scadenza">
<div class="dataScadenza-msg"></div>
</div>

<div class="campo">
<i class="bi bi-0-circle"></i>
<input class="login-input" type="password" name="pin" id="pin" placeholder="PIN Carta">
<div class="pin-msg"></div>
</div>

<div class="campo">
<i class="bi bi-0-circle"></i>
<input class="login-input" type="text" name="saldo" id="saldo" placeholder="Saldo Carta">
<div class="saldo-msg"></div>
</div>

<div class="login row">
<div class="col-sm">
<input class="allowed-submit btn btn-primary" type="submit" value="Aggiungi carta" id="submit-carta" disabled="disabled">
</div>
<div class="col-sm">
<input id="reset-btn-carta" class="btn btn-primary" type="reset" value="Reset">
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