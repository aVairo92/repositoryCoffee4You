/**
 * Script per i suggerimenti nella barra di ricerca
 */
$(document).ready(function() {

    $("#searchBar").click(function() {
		$("#search-div").removeClass("container-searchbar").addClass("container-searchbar-active searchbar-active") ;				
		});

	$(document).click(function(event) {
		
		var idclick = event.target.id ;
		
		var id1 = $("#searchBar").attr("id") ;
		var id2 = $("#search-div").attr("id") ;
		var id3 = $("#show-suggest").attr("id") ;
		var id4 = $("#link-suggest").attr("id") ;
		var id5 = $("#submit-search").attr("id") ;
		
		if(idclick == id5) {
		$("#search-div").removeClass("container-searchbar-active").addClass("container-searchbar") ;
		$("#show-suggest").hide() ;
		} else if(((idclick!=id1)&&(idclick!=id2)&&(idclick!=id3)&&(idclick!=id4)) ) {
		$("#searchBar").val() ;
		$("#search-div").removeClass("container-searchbar-active").addClass("container-searchbar") ;
		$("#show-suggest").hide() ;
		} 
		
		});

	$("#searchBar").keyup(function() {	
		
		var suggest = $("#searchBar").val() ;
		
	if(suggest.length >= 2) {

	$("#show-suggest").show() ;
		   
	//Chiamata Ajax
	$.get('prodotto', {"suggest": suggest},
        function(resp) { // on sucess
        showSuggest(resp) ;
    		})
            .fail(function() { // on failure
                alert("Request failed.");
            });   
            
            } else {
				$("#show-suggest").empty() ;
				$("#show-suggest").removeClass("show-suggest").addClass("notActive") ;
			}
             
             }) ;	
		  
});