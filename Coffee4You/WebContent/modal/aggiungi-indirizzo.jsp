<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.beans.ClienteBean"%>
<%
ClienteBean residente =(ClienteBean) request.getSession().getAttribute("clienteLoggato") ;
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aggiungi Indirizzo</title>
</head>
<body>


<!-- Modal -->
<div class="modal fade" id="addAddressModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content addressModal">
      
      <div class="modal-header">
        <h4 class="modal-title" id="staticBackdropLabel">Aggiungi Indirizzo</h4>
           <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"><i class="bi bi-x"></i></button>
      </div>
      
<div class="modal-body">

<form id="formAddAddress" action="indirizzi" method="POST">
<input type="hidden" name="action" value="add">
<input type="hidden" name="residenteId" value="<%=residente.getId()%>">

<div class="campo">
<i class="bi bi-signpost-fill"></i>
<input class="login-input" type="text" name="via" id="via" placeholder="Via">
<div class="via-msg"></div>
</div>

<div class="campo">
<i class="bi bi-0-circle"></i>
<input class="login-input" type="text" name="nCivico" id="nCivico" placeholder="Numero Civico">
<div class="nCivico-msg"></div>
</div>

<div class="campo">
<i class="bi bi-building"></i>
<input class="login-input" type="text" name="citta" id="citta" placeholder="Città">
<div class="citta-msg"></div>
</div>

<div class="campo">
<i class="bi bi-buildings"></i>
<input class="login-input" type="text" name="provincia" id="provincia" placeholder="Provincia">
<div class="provincia-msg"></div>
</div>

<div class="campo">
<i class="bi bi-123"></i>
<input class="login-input" type="text" name="cap" id="cap" placeholder="CAP">
<div class="cap-msg"></div>
</div>

<div class="campo">
<i class="bi bi-globe"></i>
<input class="login-input" type="text" name="nazione" id="nazione" placeholder="Nazione">
<div class="nazione-msg"></div>
</div>

<div class="login row">
<div class="col-sm">
<input class="allowed-submit btn btn-primary" type="submit" value="Aggiungi indirizzo" id="submit-address" disabled="disabled">
</div>
<div class="col-sm">
<input id="reset-btn-addr" class="btn btn-primary" type="reset" value="Reset">
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