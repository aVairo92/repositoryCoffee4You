/**
 * File javascript che serve a validare i campi del form per l aggiunta di 
 * una nuova carta di un cliente.
 */
$(document).ready(function() {
	
//Validazione codiceCarta	
$('#codiceCarta').on('input',function() {
		var codice = $(this).val();
		var regExpNome = /^[0-9]{16}$/;	
			
		if(codice.length == 0) {
			$('.nCarta-msg').addClass('invalid-msg').text("Codice carta richiesto") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else if(codice.length != 16) {
				$('.nCarta-msg').addClass('invalid-msg').text("Il codice carta deve essere di 16 caratteri") ;
				$(this).addClass('invalid-input').removeClass('valid-input') ;
				} else if(!(regExpNome.test(codice))) {
				$('.nCarta-msg').addClass('invalid-msg').text("Codice carta non valido:solo numeri sono permessi") ;
				$(this).addClass('invalid-input').removeClass('valid-input') ;
				} else {
				$('.nCarta-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione dataScadenza	
$('#dataScadenza').on('input',function() {
		var data = $(this).val();
				
		if(data.length == 0) {
			$('.dataScadenza-msg').addClass('invalid-msg').text("Data Scadenza richiesta") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.dataScadenza-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});


//Validazione codiceCarta	
$('#pin').on('input',function() {
		var pin = $(this).val();
			
		if(pin.length == 0) {
			$('.pin-msg').addClass('invalid-msg').text("PIN richiesto") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else if( pin.length<=3 || pin.length >= 11) {
				$('.pin-msg').addClass('invalid-msg').text("Il PIN deve essere lungo tra 4 e 10 caratteri") ;
				$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.pin-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione codiceCarta	
$('#saldo').on('input',function() {
		var saldo = $(this).val();
			
		if(saldo.length < 1) {
			$('.saldo-msg').addClass('invalid-msg').text("Saldo carta richiesto") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else if(saldo <= 0.0) {
				$('.saldo-msg').addClass('invalid-msg').text("Il saldo della carta deve essere positivo") ;
				$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.saldo-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

$('#reset-btn-carta').on('click',function() {
	
	$('#codiceCarta').removeClass('valid-input invalid-input');
	$('.nCarta-msg').empty() ;
	$('#dataScadenza').removeClass('valid-input invalid-input');
	$('.dataScadenza-msg').empty() ;
	$('#pin').removeClass('valid-input invalid-input');
	$('.pin-msg').empty() ;
	$('#saldo').removeClass('valid-input invalid-input');
	$('.saldo-msg').empty() ;
	
	$('#submit-carta').attr('disabled','disabled') ;		       
	 
});

//Validazione submit button
$('input').on('input',function(e){
	   if($('#formAddCarta').find('.valid-input').length==4) {
		   $('#submit-carta').removeClass('allowed-submit');
	       $('#submit-carta').removeAttr('disabled');
	   } else {
	       e.preventDefault();
	       $('#submit-carta').attr('disabled','disabled') ;		       
	      }
	});


});