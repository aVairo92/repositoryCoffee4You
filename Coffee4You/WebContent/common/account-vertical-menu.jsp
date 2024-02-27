<%
AmministratoreBean adminLoggato = (AmministratoreBean) request.getSession().getAttribute("adminLoggato") ;
Boolean isAdminLoggato = false ;
if((Boolean) request.getSession().getAttribute("isAdminLoggato") != null ){
	isAdminLoggato = true ;
}
%>    

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
import="java.util.*,it.unisa.model.beans.AmministratoreBean"%>
<meta charset="ISO-8859-1">
<title>Account</title>

<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/firstStyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
<meta name="viewport" content="width=device-width, initial-scale=1">

</head>

<body>
<div class="vertical-nav-container">

<h5 class="title-menu">Il tuo Account</h5>

<ul class="vertical-menu">
  <li class="vertical-menu-item">
    <a class="vertical-menu-link" href="account?action=profilo">Profilo</a>
  </li>
  <li class="vertical-menu-item">
    <a class="vertical-menu-link" href="ordini">Ordini</a>
  </li>
  <%
  if(isAdminLoggato == true && adminLoggato != null) {
  %>
  <li class="vertical-menu-item">
    <a class="vertical-menu-link" href="catalogo">Modifica Catalogo</a>
  </li>
  <%} else {%>
  <li class="vertical-menu-item">
  <a class="vertical-menu-link" href="indirizzi">Indirizzi</a>
  </li>
  <li class="vertical-menu-item">
  <a class="vertical-menu-link" href="carte">Carte</a>
  </li>
  <%}%>
</ul>
</div>

</body>
</html>