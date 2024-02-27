/**
 * File javascript che serve a validare i campi del form per la registrazione di 
 * un nuovo cliente al sito.
 */
$( document ).ready(function() {
	
//Validazione nome	
$('#nome').on('input',function() {
		var nome = $(this).val();
		var regExpNome = /^[a-zA-Z ]*$/ ;
		
		if(nome.length <= 2) {
			$('.nome-msg').addClass('invalid-msg').text("Nome richiesto") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			}else if(!(regExpNome.test(nome))) {
				$('.nome-msg').addClass('invalid-msg').text('Solo caratteri e spazi sono permessi') ;
				$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.nome-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione cognome
$('#cognome').on('input',function() {
	var cognome = $(this).val();
	var regExpNome = /^[a-zA-Z ]*$/ ;
	
	if(cognome.length <= 2) {
		$('.cognome-msg').addClass('invalid-msg').text("Cognome richiesto") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		}else if(!(regExpNome.test(cognome))) {
			$('.cognome-msg').addClass('invalid-msg').text('Solo caratteri e spazi sono permessi') ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.cognome-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione
$('input[name="sesso"]').on('click',function() {
	$('.sesso').addClass('valid-input') ;
});


//Validazione Codice Fiscale
$('#codiceFiscale').on('input',function() {
	
	var codFiscale = $(this).val();
	var regExpCodFisc =/^[a-zA-Z]{6}[0-9]{2}[abcdehlmprstABCDEHLMPRST]{1}[0-9]{2}([a-zA-Z]{1}[0-9]{3})[a-zA-Z]{1}$/;
	
	if(codFiscale.length == 0) {
	$('.codiceFiscale-msg').addClass('invalid-msg').text("Inserire il codice fiscale") ;
	$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else if(!regExpCodFisc.test(codFiscale)) {
		$('.codiceFiscale-msg').addClass('invalid-msg').text('Attenzione formato corretto: "VRANNY92H16L628G" ') ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;	
	} else {
		$('.codiceFiscale-msg').empty() ;
		$(this).addClass('valid-input').removeClass('invalid-input') ;
	}
});

//Validazione Username
$('#username').on('input',function () {
	
	var username = $(this).val();
	var regExpUsername = /^[a-zA-Z0-9-_]{6,24}$/ ;
	
	if(username.length == 0) {
	$('.username-msg').addClass('invalid-msg').text("Username richiesto") ;
	$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else if (username.length < 6 || username.length > 24) {
		$('.username-msg').addClass('invalid-msg').text('Inserire minimo 6 caratteri e massimo 24 caratteri') ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;			
	}else if( !regExpUsername.test(username)) {
		$('.username-msg').addClass('invalid-msg').text('Caratteri permessi:lettere,numeri,_ e -') ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else {
		$('.username-msg').empty() ;
		$(this).addClass('valid-input').removeClass('invalid-input') ;
	}
});

//Validazione Password
$('#password').on('input',function () {
	
	var password = $(this).val();
	var regExpPassword = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,16}$/ ;
	
	if(password.length == 0) {
	$('.password-msg').addClass('invalid-msg').text("Password richiesto") ;
	$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else if (password.length < 8 || password.length > 24) {
		$('.password-msg').addClass('invalid-msg').text('La password deve avere da 8 a 16 caratteri') ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;			
	}else if( !regExpPassword.test(password)) {
		$('.password-msg').addClass('invalid-msg').text('La password deve contenere almeno una lettera e un numero') ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else {
		$('.password-msg').empty() ;
		$(this).addClass('valid-input').removeClass('invalid-input') ;
	}
});

//Validazione ConfermaPassword
$('#confermaPassword').on('input',function () {
	
	var confermaPassword = $(this).val();
	var password = $('#password').val() ;
	
	if(confermaPassword != password) {
	$('.confermaPassword-msg').addClass('invalid-msg').text("Le password devono coincidere") ;
	$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else {
		$('.confermaPassword-msg').empty() ;
		$(this).addClass('valid-input').removeClass('invalid-input') ;
	}
});

//Validazione Email
$('#email').on('input',function () {
	
	var email = $(this).val();
	var regExpEmail = /^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])$/;
	
	if(email.length == 0) {
		$('.email-msg').addClass('invalid-msg').text("Email richiesta") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else if(!regExpEmail.test(email)) {
			$('.email-msg').addClass('invalid-msg').text('Email non valida: formato corretto-> xxx@xxx.it') ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.email-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}

	});

//Validazione N°Telefono
$('#telefono').on('input',function () {
	
	var telefono = $(this).val();
	var regExpTelefono = /^[0-9]{10,11}$/;
	
	if(telefono.length == 0) {
	$('.telefono-msg').addClass('invalid-msg').text("Telefono richiesto") ;
	$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else if(!regExpTelefono.test(telefono)) {
		$('.telefono-msg').addClass('invalid-msg').text('N° di telefono non valido') ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
	} else {
		$('.telefono-msg').empty() ;
		$(this).addClass('valid-input').removeClass('invalid-input') ;
	}
});


$('#reset-btn').on('click',function() {
	$('#nome').removeClass('valid-input invalid-input');
	$('.nome-msg').empty() ;
	$('#cognome').removeClass('valid-input invalid-input');
	$('.cognome-msg').empty() ;
	$('.sesso').removeClass('valid-input');
	$('#codiceFiscale').removeClass('valid-input invalid-input');
	$('.codiceFiscale-msg').empty() ;
	$('#username').removeClass('valid-input invalid-input');
	$('.username-msg').empty() ;
	$('#password').removeClass('valid-input invalid-input');
	$('.password-msg').empty() ;
	$('#confermaPassword').removeClass('valid-input invalid-input');
	$('.confermaPassword-msg').empty() ;
	$('#email').removeClass('valid-input invalid-input');	
	$('.email-msg').empty() ;
	$('#telefono').removeClass('valid-input invalid-input');
	$('.telefono-msg').empty() ;
	
	$('#submit-btn').attr('disabled','disabled') ;		       
	 
});

//Validazione submit button
$('input').on('input',function(e){
	   if($('#formRegistrazione').find('.valid-input').length==11) {
		   $('#submit-btn').removeClass('allowed-submit');
	       $('#submit-btn').removeAttr('disabled');
	   } else {
	       e.preventDefault();
	       $('#submit-btn').attr('disabled','disabled') ;		       
	      }
	});

});	