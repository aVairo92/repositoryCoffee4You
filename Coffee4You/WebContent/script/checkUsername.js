/**
 * Script per controllare se l'username e gia impegnata
 */
$(document).ready(function() {

$("#username").on("input",function() {	
		
	var suggest = $("#username").val() ;
	var check = 'checkUsername' ;
				   
	//Chiamata Ajax
	$.get('registrazione', {"suggest":suggest,"check":check},
        function(resp) { // on sucess
        showErrorUsername(resp) ;
    		})
            .fail(function() { // on failure
            });   
                         
             }) ;	
             
$("#email").on("input",function() {	
		
	var suggest = $("#email").val() ;
	var check = 'checkEmail' ;
				   
	//Chiamata Ajax
	$.get('registrazione', {"suggest":suggest,"check":check},
        function(resp) { // on sucess
        showErrorEmail(resp) ;
    		})
            .fail(function() { // on failure
            });   
                         
             }) ;	
             
             

});