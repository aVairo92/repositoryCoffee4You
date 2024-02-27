<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.beans.ClienteBean,it.unisa.model.beans.OrdineBean,it.unisa.model.cart.*"%>
<%
OrdineBean ordine =(OrdineBean) request.getAttribute("ordineFattura") ;
ClienteBean cliente = (ClienteBean) request.getAttribute("clienteFattura") ;
%>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" charset="ISO-8859-1">
<title>Fattura</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/firstStyle.css" rel="stylesheet" type="text/css">

<!-- html2canvas library -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/1.4.1/html2canvas.min.js"></script>
<!-- jsPDF library -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.5.1/jspdf.umd.min.js"></script>

<script>
window.jsPDF = window.jspdf.jsPDF;

//Convert HTML content to PDF
function scaricaPdf() {
 var doc = new jsPDF();
	
 // Source HTMLElement or a string containing HTML.
 var elementHTML = document.querySelector("#pdf-content");

 doc.html(elementHTML, {
     callback: function(doc) {
         // Save the PDF
         doc.save('fatturaC4Y.pdf');
     },
     margin: [10, 25, 10, 25],
     autoPaging: 'text',
     x: 0,
     y: 0,
     width: 190, //target width in the PDF document
     windowWidth: 675 //window width in CSS pixels
 });
}
</script>

</head>

<body style='font-family:Tahoma;font-size:12px;color: #333333;background-color:#FFFFFF;padding:25px;'>


<div id="pdf-content">
<table border='0' cellpadding='0' cellspacing='0' style='height:100%; width:595px;font-size:12px;'>
  <tr>
    <td valign='top'><table width='100%' cellspacing='0' cellpadding='0'>
        <tr>
          <td valign='bottom' width='50%' height='50'><div align='left'><img class="fattura-img-container" src="images/logo2.png" /></div><br /></td>

          <td width='50%'>&nbsp;</td>
        </tr>
      </table>Fattura a: <br/><br/>
      <table width='100%' cellspacing='0' cellpadding='0'>
      <tr>
        <td valign='top' width='35%' style='font-size:12px;'> <strong >[<%=ordine.getAcquirente().getNome()%> <%=ordine.getAcquirente().getCognome()%>]</strong><br /> 
[<%=ordine.getIndirizzoConsegna()%>] <br/>C.F.: [<%=cliente.getCodiceFiscale()%>]<br />

</td>
        <td valign='top' width='35%'>
</td>
<td valign='top' width='30%' style='font-size:12px;'>Data emissione: <%=ordine.getDataEmissione() %><br/>		
</td>
</tr>
</table>
    
<table width='100%' height='100' cellspacing='0' cellpadding='0'>
<tr>
<td><div align='center' style='font-size: 14px;font-weight: bold;'>Ordine n. <%=ordine.getId() %> </div></td>
</tr>
</table>
<table width='100%' cellspacing='0' cellpadding='2' border='1' bordercolor='#CCCCCC'>
<tr>
<td width='35%' bordercolor='#ccc' bgcolor='#f2f2f2' style='font-size:12px;'><strong>Nome Prodotto </strong></td>
<td bordercolor='#ccc' bgcolor='#f2f2f2' style='font-size:12px;'><strong>Quantità</strong></td>
<td bordercolor='#ccc' bgcolor='#f2f2f2' style='font-size:12px;'><strong>Prezzo</strong></td>
<td bordercolor='#ccc' bgcolor='#f2f2f2' style='font-size:12px;'><strong>Iva</strong></td>
<td bordercolor='#ccc' bgcolor='#f2f2f2' style='font-size:12px;'><strong>Subtotale</strong></td>
</tr>
        
<tr style="display:none;"><td colspan="*">

<% for(ProdottoQuantita p: ordine.getProdottiOrdine()) { %>

<tr>
<td valign='top' style='font-size:12px;'><%=p.getNome()%></td>
<td valign='top' style='font-size:12px;'><%=p.getQuantita()%></td>
<td valign='top' style='font-size:12px;'><%=p.getPrezzo()%></td>
<td valign='top' style='font-size:12px;'><%=p.getIva()%></td>
<td valign='top' style='font-size:12px;'><%=( (p.getPrezzo()+((p.getPrezzo()*p.getIva())/100)) *p.getQuantita())%>Euro</td>
</tr>

<%}%>

</table>


<table width='100%' cellspacing='0' cellpadding='2' border='0'>
<tr>
<td style='font-size:12px;width:50%;'><strong></strong></td>
<td>
<table width='100%' cellspacing='0' cellpadding='2' border='0'>
<tr>
<td  align='right' style='font-size:12px;'><b>Spesa Totale</b></td>
<td  align='right' style='font-size:12px;'><b><%=ordine.getTotaleSpesa()%></b></td>
</tr>
</table>
</td>
</tr>
</table> 
   
<table width='100%' height='50'><tr><td style='font-size:12px;text-align:justify;'></td></tr></table>
<table  width='100%' cellspacing='0' cellpadding='2'>
<tr>
<td width='33%' style='border-top:double medium #CCCCCC;font-size:12px;' valign='top' ><b>[COFFE4YOU]</b><br/>
P.IVA: [1212001212]<br/>
</td>
<td width='33%' style='border-top:double medium #CCCCCC; font-size:12px;' align='center' valign='top'>
[Via Madonna delle Grazie 70] <br/>
</td>

<td valign='top' width='34%' style='border-top:double medium #CCCCCC;font-size:12px;' align='right'>BANCA: [Unicredit Banca]<br/> IBAN: [IT 12 A 12345 12345 123456789012]  <br/>SWIFT/BIC: [UNCRITMM] <br/>     
 </td>
      </tr>
    </table>
</td>
  </tr>
</table>

</div>

<button class="btn-fattura" onClick="scaricaPdf();">Scarica in PDF</button>

</body>
</html>