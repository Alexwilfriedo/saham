/** MENU PRODUIT **/
$(".MenuUp").hide();
$(".xMenuUp").hide();
$(".DivProdContact").hide();

$('.bMenuUp').mouseover(function(){
    $(".xMenuUp").show();
    $(".MenuUp").slideDown(200);

    return false;
});

$('.xMenuUp').mouseover(function(){
    $(".xMenuUp").hide();
    $(".MenuUp").slideUp(200);
    return false;
});
$('.LogoheadCenter').mouseover(function(){
    $(".xMenuUp").hide();
    $(".MenuUp").slideUp(300);
    return false;
});
$('.menuheadRight').mouseover(function(){
    $(".xMenuUp").hide();
    $(".MenuUp").slideUp(300);
    return false;
});

/** FIN MENU PRODUIT **/