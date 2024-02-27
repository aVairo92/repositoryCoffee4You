/*
*Script per stampare i suggerimenti trovati
*/
function showSuggest(json) {
		
	$("#show-suggest").removeClass("notActive").addClass("show-suggest") ;
	$("#show-suggest").empty().append("<ul id='ul-suggest' class='ul-suggest'>");
	
	// Get each band object...
	$.each(json, function(i,prod) {
		$("#ul-suggest").append("<li class='li-suggest' id='link-suggest'><a class='a-suggest' id='link-suggest' href='carrello?action=dettagli&id="+prod.id+"'>"+"<i class='bi bi-search' style='width: 24px; height: 24px; margin-right:15px ;font-size: 22px;'></i>"+ prod.nomeProdotto + "</a></li>");
	});	
				
}

function showErrorUsername(json) {
	// Get each band object...
	$.each(json, function(i,cliente) {
			if(cliente.id != -1) {	
			$('.username-msg').addClass('invalid-msg').text("Username non disponibile") ;
			$("#username").addClass('invalid-input').removeClass('valid-input') ;
			$('#submit-btn').attr('disabled','disabled') ;		       

	}
	});	
				
}

function showErrorEmail(json) {
	// Get each band object...
	$.each(json, function(i,cliente) {
			if(cliente.id != -1) {	
			$('.email-msg').addClass('invalid-msg').text("Email non disponibile") ;
			$("#email").addClass('invalid-input').removeClass('valid-input') ;
			$('#submit-btn').attr('disabled','disabled') ;		       

	}
	});	
				
}
