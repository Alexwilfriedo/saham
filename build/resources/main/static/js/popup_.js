	$(document).ready(function() { 
		$('.Btnmenudrop').click(function(){
			$('.menudrop').fadeIn(100);
			$('.Btnmenudrop').hide(); 
			$('.Btnmenudrop2').show(); 
		});
		$('.Btnmenudrop2').click(function(){
			$('.menudrop').fadeOut(100);			
			$('.Btnmenudrop').show(); 
			$('.Btnmenudrop2').hide(); 
		});
		 
		$('.content-i').mouseout(function(){
			$('.menudrop').fadeOut(100);
			$('.Btnmenudrop').show(); 
			$('.Btnmenudrop2').hide();  
		});	
		$('.menu-top-image-i').mouseout(function(){
			$('.menudrop').fadeOut(100);
			$('.Btnmenudrop').show(); 
			$('.Btnmenudrop2').hide();  
		});	
		

	});
    $(document).ready(function(){

        /*******   Pagination Contrat     ********************/

        $(".divs div").each(function(e) {
            if (e != 0)
                $(this).hide();
        });

        $("#next").click(function(){
            if ($(".divs div:visible").next().length != 0)
                $(".divs div:visible").next().show().prev().hide();
            else {
                $(".divs div:visible").show();
                //$(".divs div:first").show();
            }
            return false;
        });

        $("#prev").click(function(){
            if ($(".divs div:visible").prev().length != 0)
                $(".divs div:visible").prev().show().next().hide();
            else {
                $(".divs div:visible").show();
                //$(".divs div:last").show();
            }
            return false;
        });


        $("marquee").mouseover(function(){
            this.stop();
        });
        $("marquee").mouseout(function(){
            this.start();
        });
        /******   Bouton menu Produit     *******************/

        $('.BtnShowpro').click(function(){

            var val = $(this).attr('id');

            if ($('#DivShowPro'+val).is(":visible")){
                $('#DivShowPro'+val).hide();
            }else if ($('#DivShowPro'+val).is(":hidden")){
                $('.SectionPro').hide();
                $('#DivShowPro'+val).show();
            }
        });

        $('.BtnActivC').click(function(){
            $('.sectionD a').removeClass( "activContrat" );
            $(this).addClass( "activContrat" );
        });

        /******   FIN Bouton menu produit     *******************/


        /** Pop produit **/
        $('.bDivProdContact').click(function(){
            $(".DivProdContact").fadeIn(300);

            return false;
        });

        $('.xDivProdContact').click(function(){
            $(".DivProdContact").fadeOut(300);

            return false;
        });
        /** fin Pop produit **/

        $( ".content-masque" ).hide();
    });