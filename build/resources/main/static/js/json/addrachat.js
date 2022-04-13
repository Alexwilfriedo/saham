$("#sendrachat").click(function() {
	
	var dataString=$("#forminfo").serialize();
	   
	$.ajax({
	type: "POST",
	url: "js/json/addrachat.php",
	data: dataString,
	success: function(data){
		 $('html, body').animate({scrollTop:$('#showrachat').offset().top}, 300);

		$.ajax({
			 url: 'js/json/listrachat.json',
			 type: 'GET',
			 dataType: 'json',
			 data : { },
			 success : function(response) { 
				 var loop = String(); 
									   
				for (var i = 0; i < response.items.length ; i++) {
					loop +='<tr>'
						 +'<td>'+response.items[i].nom+'</td>'
						 +'<td>'+response.items[i].contrat+'</td>'
						 +'<td>'+response.items[i].lieu+'</td>'
						 +'<td>'+response.items[i].montant+'</td>'
						 +'<td><a href=""><i class="fa fa-trash" aria-hidden="true"></i></a></td>'
						 +'</tr>';									
				 };
				  
					$('#showrachat').html(loop); 
					 
			 }
		});
		 
	  }
   });
return false;
}); 