/**
 * File javascript che serve ad aggiornare il valore dell'input range 
 * del prezzo massimo da visualizzare.
 */

$(document).ready(function(){ 
	
$('#range').val(0); 

if(screen.width >= 800) {
	$('#sidebarCatalogo').removeClass('collapse');		
	}

/** mousemove */ 
$('#range').change(function(){
$('#valBox').html("Euro "+$(this).val()); 
}); 
/** mousemove */ 

$(window).resize(function(){ 

	if(screen.width < 800) {
			$('#sidebarCatalogo').addClass('collapse');
		} else {
			$('#sidebarCatalogo').removeClass('collapse');		
			}
	
});

});