/**   
 *File javascript che serve a validare i campi del form per l'aggiunta di 
 * un nuovo prodotto nel catalogo.
 */
$(document).ready(function() {
	
//Validazione idCategoria	
$('#idCategoria').on('input',function() {
		var idCategoria = $(this).val();
		var regExpIdCat=/^[0-9]+$/;
		
		if(idCategoria.length == 0) {
			$('.idCat-msg').addClass('invalid-msg').text("Id Categoria richiesto") ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
			}else if(!(regExpIdCat.test(idCategoria))) {
				$('.idCat-msg').addClass('invalid-msg').text('Solo numeri sono permessi') ;
				$(this).addClass('invalid-input').removeClass('valid-input') ;
			} else {
				$('.idCat-msg').empty() ;
				$(this).addClass('valid-input').removeClass('invalid-input') ;
			}		
});

//Validazione nomeCategoria
$('#nomeCategoria').on('input',function() {
	var nomeCategoria = $(this).val();
		
	if(nomeCategoria.length <= 2) {
		$('.nomeCat-msg').addClass('invalid-msg').text("Nome Categoria richiesto") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.nomeCat-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione descrizioneCategoria
$('#descrizioneCategoria').on('input',function() {
	var descrizioneCategoria = $(this).val();
		
	if(descrizioneCategoria.length <= 2) {
		$('.desCat-msg').addClass('invalid-msg').text("Descrizione Categoria richiesto") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.desCat-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione nomeProdotto
$('#nomeProdotto').on('input',function() {
	var nomeProdotto = $(this).val();
		
	if(nomeProdotto.length <= 2) {
		$('.nomeProd-msg').addClass('invalid-msg').text("Nome Prodotto richiesto") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.nomeProd-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione descrizioneProdotto
$('#descrizioneProdotto').on('input',function() {
	var desProdotto = $(this).val();
		
	if(desProdotto.length <= 2) {
		$('.descrizioneProd-msg').addClass('invalid-msg').text("Descrizione Prodotto richiesta") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.descrizioneProd-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione marcaProdotto
$('#marcaProdotto').on('input',function() {
	var marcaProdotto = $(this).val();
		
	if(marcaProdotto.length <= 2) {
		$('.marcaProd-msg').addClass('invalid-msg').text("Marca Prodotto richiesta") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.marcaProd-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione disponibilita prodotto
$('#disponibilitaProdotto').on('input',function() {
	var disponibilitaProdotto = $(this).val();
	var regExpDispProd=/^[0-9]+$/;
		
	if(disponibilitaProdotto.length == 0) {
		$('.disponibilitaProd-msg').addClass('invalid-msg').text("Disponibilità Prodotto richiesta") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else if(!(regExpDispProd.test(disponibilitaProdotto))){
			$('.disponibilitaProd-msg').addClass('invalid-msg').text('Solo numeri sono permessi') ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.disponibilitaProd-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione prezzo prodotto
$('#prezzoProdotto').on('input',function() {
	var prezzoProdotto = $(this).val();
	var regExpPrezzoProd=/^-?\d+\.?\d*$/;
		
	if(prezzoProdotto.length == 0) {
		$('.prezzoProd-msg').addClass('invalid-msg').text("Prezzo Prodotto richiesta") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else if(!(regExpPrezzoProd.test(prezzoProdotto))){
			$('.prezzoProd-msg').addClass('invalid-msg').text('Solo interi o float sono permessi') ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.prezzoProd-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Valida iva prodotto
$('#ivaProdotto').on('input',function() {
	var ivaProdotto = $(this).val();
	var regExpIvaProd=/^-?\d+\.?\d*$/;
		
	if(ivaProdotto.length == 0) {
		$('.ivaProd-msg').addClass('invalid-msg').text("Iva Prodotto richiesta") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else if(!(regExpIvaProd.test(ivaProdotto))){
			$('.ivaProd-msg').addClass('invalid-msg').text('Solo interi o float sono permessi') ;
			$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.ivaProd-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Valida Url prodotto
$('#urlProdotto').on('input',function() {
	var urlProdotto = $(this).val();
		
	if(urlProdotto.length <= 2) {
		$('.urlProd-msg').addClass('invalid-msg').text("Url Prodotto richiesto:formato corretto-> yyyyy/xxxxxxx.yxz") ;
		$(this).addClass('invalid-input').removeClass('valid-input') ;
		} else {
			$('.urlProd-msg').empty() ;
			$(this).addClass('valid-input').removeClass('invalid-input') ;
		}		
});

//Validazione
$('input[name="promo"]').on('click',function() {
	$('.sesso').addClass('valid-input') ;
});


$('#reset-addProduct').on('click',function() {
	$('#idCategoria').removeClass('valid-input invalid-input');
	$('.idCat-msg').empty() ;
	$('#nomeCategoria').removeClass('valid-input invalid-input');
	$('.nomeCat-msg').empty() ;
	$('#descrizioneCategoria').removeClass('valid-input invalid-input');
	$('.desCat-msg').empty() ;
	$('#nomeProdotto').removeClass('valid-input invalid-input');
	$('.nomeProd-msg').empty() ;
	$('#descrizioneProdotto').removeClass('valid-input invalid-input');
	$('.descrizioneProd-msg').empty() ;
	$('#marcaProdotto').removeClass('valid-input invalid-input');
	$('.marcaProd-msg').empty() ;
	$('#disponibilitaProdotto').removeClass('valid-input invalid-input');
	$('.disponibilitaProd-msg').empty() ;
	$('#prezzoProdotto').removeClass('valid-input invalid-input');
	$('.prezzoProd-msg').empty() ;
	$('#ivaProdotto').removeClass('valid-input invalid-input');
	$('.ivaProd-msg').empty() ;
	$('#urlProdotto').removeClass('valid-input invalid-input');
	$('.urlProd-msg').empty() ;
	$('.sesso').removeClass('valid-input');
	
	$('#submit-addProduct').attr('disabled','disabled');
});

//Validazione submit button
$('input').on('input',function(e){
	   if( $('#formAggiungiProdotto').find('.valid-input').length == 13) {
		   $('#submit-addProduct').removeClass('allowed-submit');
	       $('#submit-addProduct').removeAttr('disabled');
	   } else {
	       e.preventDefault();
	       $('#submit-addProduct').attr('disabled','disabled') ;		       
	      }
	});


});