$(document).ready(function() {

    $("#mobiledropdown li").hover(function(){
        $(this).children(".menu-hover").show();
        $(this).children("a").children(".flecha-azul").show();
    }, function(){
        $(this).children(".menu-hover").hide();
        $(this).children("a").children(".flecha-azul").hide();
    });
    $('.menu-hover-derecha-fondo').hide();
    $('.menu-hover-derecha-fondo').eq(0).show();
    $(".menu-hover-izquierda ul li").hover(function(){
        var eq = $(this).index();
        $(".menu-hover-izquierda ul li").removeClass("menu-active");
        $(this).addClass("menu-active");
        $(".menu-hover-derecha-fondo").stop().hide();
        $(".menu-hover-derecha-fondo").eq(eq).show();
    });

    $(".options-content li").hide();
    $("ul.options-tabs li:first").addClass("active").show();
    $(".options-content li:first").addClass("that-tab").show();

    $("ul.options-tabs li").click(function()
    {
        $("ul.options-tabs li").removeClass("active");
        $(this).addClass("active");
        $(".options-content li").hide();

        var activeTab = $(this).find("a").attr("href");
        $(activeTab).fadeIn();
        return false;
    });

    });