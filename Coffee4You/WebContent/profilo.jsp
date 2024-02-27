<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
ClienteBean cliente = (ClienteBean) request.getSession().getAttribute("clienteLoggato");
AmministratoreBean admin = (AmministratoreBean) request.getSession().getAttribute("adminLoggato") ;
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*,it.unisa.model.beans.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profilo</title>
</head>
<body>

<%@ include file="common/header.jsp" %>

<div class="row main-container">

<div class="first-container">
<%@ include file="common/account-vertical-menu.jsp" %>
</div>

<div class="second-container">
<div class="title">
<h2><strong>Profilo</strong></h2>
<span>Visualizza i miei dati personali e le info sull'account.</span>
</div>
<div>
<h4>Dati Personali</h4>
<div class="row info">

<div class="sub-info">
<h6><strong>Nome</strong></h6>
<%if(cliente != null) { %>
<span><%=cliente.getNome()%> <%=cliente.getCognome()%></span>
<%} else { %>
<span><%=admin.getNome()%> <%=admin.getCognome()%></span>
<%}%>
</div>

<div class="sub-info">
<h6><strong>Sesso</strong></h6>
<%if(cliente != null) { %>
<span><%=cliente.getSesso()%></span>
<%} else { %>
<span><%=admin.getSesso()%></span>
<%}%>
</div>

<%if(cliente != null) { %>
<div class="sub-info">
<h6><strong>Codice Fiscale</strong></h6>
<span><%=cliente.getCodiceFiscale()%></span>
</div>
<%}%> 

</div>
</div>

<div>
<h4>Info Account</h4>
<div class="row info">

<div class="sub-info">
<h6><strong>Username</strong></h6>
<%if(cliente != null) { %>
<span><%=cliente.getUsername()%></span>
<%} else { %>
<span><%=admin.getUsername()%></span>
<%}%>
</div>

<div class="sub-info">
<h6><strong>Email</strong></h6>
<%if(cliente != null) { %>
<span><%=cliente.getEmail()%></span>
<%} else { %>
<span><%=admin.getEmail()%></span>
<%}%>
</div>

<div class="sub-info">
<h6><strong>Telefono</strong></h6>
<%if(cliente != null) { %>
<span><%=cliente.getTelefono()%></span>
<%} else { %>
<span><%=admin.getTelefono()%></span>
<%}%>
</div>

</div>

</div>
</div>

</div>

<%@ include file="common/footer.jsp" %>

</body>
</html>