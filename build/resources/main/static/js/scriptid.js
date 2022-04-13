// JavaScript Document

$(document).ready(function(){
	
	// Bloc saisir identifiant ou numero de téléphone

	$(".bidentity").click(function() {
	
		$('.identity').hide();
		$('.verify').fadeIn();	
	
	});
	 		
	// Bloc verifier code
		
	$(".bverify").click(function() {
	
		$('.verify').hide();
		$('.valid').fadeIn();	
	
	});
	
	
		return false;
		
	 
		
});