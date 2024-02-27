/**
 * File javascript che serve a validare i campi del form per l aggiunta di 
 * un nuovo indirizzo di un cliente.
 */
$(document).ready(function() {
	
//Validazione via	
$('#via').on('input',function() {
		var via = $(this).val();
			
		if(via.length <= 2) {
			$('.via-msg').addClass('invalid-msg').text("Via richiesta") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.via-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione nCivico	
$('#nCivico').on('input',function() {
		
		var nCivico = $(this).val();
			
		if(nCivico.length <= 0) {
			$('.nCivico-msg').addClass('invalid-msg').text("Numero civico richiesto") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.nCivico-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione Provincia	
$('#provincia').on('input',function() {
		var via = $(this).val();
			
		if(via.length <= 2) {
			$('.provincia-msg').addClass('invalid-msg').text("Provincia richiesta") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.provincia-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione Città	
$('#citta').on('input',function() {
		var via = $(this).val();
			
		if(via.length <= 2) {
			$('.citta-msg').addClass('invalid-msg').text("Città richiesta") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.citta-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione cap	
$('#cap').on('input',function() {
		var via = $(this).val();
			
		if(via.length <= 2) {
			$('.cap-msg').addClass('invalid-msg').text("CAP richiesto") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.cap-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione cap	
$('#nazione').on('input',function() {
		var via = $(this).val();
			
		if(via.length <= 2) {
			$('.nazione-msg').addClass('invalid-msg').text("Nazione richiesta") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.nazione-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

$('#reset-btn-addr').on('click',function() {
	
	$('#via').removeClass('valid-input invalid-input');
	$('.via-msg').empty() ;
	$('#provincia').removeClass('valid-input invalid-input');
	$('.provincia-msg').empty() ;
	$('#nCivico').removeClass('valid-input invalid-input');
	$('.nCivico-msg').empty() ;
	$('#citta').removeClass('valid-input invalid-input');
	$('.citta-msg').empty() ;
	$('#cap').removeClass('valid-input invalid-input');
	$('.cap-msg').empty() ;
	$('#nazione').removeClass('valid-input invalid-input');
	$('.nazione-msg').empty() ;
	
	$('#submit-address').attr('disabled','disabled') ;		       
	 
});

//Validazione submit button
$('input').on('input',function(e){
	   if($('#formAddAddress').find('.valid-input').length==6) {
		   $('#submit-address').removeClass('allowed-submit');
	       $('#submit-address').removeAttr('disabled');
	   } else {
	       e.preventDefault();
	       $('#submit-address').attr('disabled','disabled') ;		       
	      }
	});


});