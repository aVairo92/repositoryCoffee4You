/**
 * Script per animare la jsp log reg.
 */
$(document).ready(function(){ 
	
$('#btn-registrati').on('click',function() {
	$('#btn-registrati').toggle("slow") ;
	$('#btn-accedi').toggle("slow") ;
	$('#section-reg').toggle("slow") ;
	$('#section-log').toggle("slow") ;
})

$('#btn-accedi').on('click',function() {
	$('#btn-registrati').toggle("slow") ;
	$('#btn-accedi').toggle("slow") ;
	$('#section-reg').toggle("slow") ;
	$('#section-log').toggle("slow") ;
})
	
});