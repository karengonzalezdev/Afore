var timeout = 60000;

$(window).resizeListener();
$("body").on("layoutChange", function () {
	switch($(this).attr("layout")) {
        case "MOB":
            $(".cambio-iframe").attr('target', '_blank').removeClass("cambio-iframe");
            $(".tabla-fondos").attr('target', '_blank').removeClass("tabla-fondos");
            $(".tipos-tramites").attr('target', '_blank').removeClass("tipos-tramites");
			$("#mobilemenu").removeClass("hidden");
			$("#mobiledropdown").css("display","none").removeClass("dropped");
            $(".lateral-redes, .lateral-afore").hide();
            $(".social-footer, .central-afore").removeClass("hidden");
            $(".ayuda-mini, .ayuda-big").addClass("hidden");
            $(document).mouseup(function(){
                if($("#log-button").length!=0){
                    $("#loginbox").hide();
                }
            });
			break;
		case "720":
            $(".cambio-iframe").attr('target', '_blank').removeClass("cambio-iframe");
            $(".tabla-fondos").attr('target', '_blank').removeClass("tabla-fondos");
            $(".tipos-tramites").attr('target', '_blank').removeClass("tipos-tramites");
			$("#mobilemenu").removeClass("hidden");
			$("#mobiledropdown").css("display","none").removeClass("dropped");
            $(".lateral-redes, .lateral-afore").hide();
            $(".social-footer, .central-afore").removeClass("hidden");
            $(".ayuda-mini, .ayuda-big").addClass("hidden");
            if( $(".central-afore").length == 0 ) {
                $(".main").append("<div class='central-afore'><p style='padding-top: 2px;'><a class='cambio-iframe' href='http://miretiroprofuturo.com/cambiate/cambio_de_afore.php' target='_blank' style='font-size: 1.2em;'><span>C�mbiate a</span> AFORE PROFUTURO</a></p></div>");
            }
            $(document).mouseup(function(){
                if($("#log-button").length!=0){
                    $("#loginbox").hide();
                }
            });
			break;
		case "960":
			$("#mobilemenu").addClass("hidden");
			$("#mobiledropdown").css("display","block");
            $(".lateral-redes, .lateral-afore").show();
            $(".social-footer, .central-afore").addClass("hidden");
            $(".ayuda-mini, .ayuda-big").removeClass("hidden");
            $( '.tip-redes' ).tipsy({gravity: 'e',cls:"redes"});
            $( '.tip-n' ).tipsy({gravity: 'n', html: true});
            $( '.tip-s' ).tipsy({gravity: 's', html: true});
            $( '.tip-e' ).tipsy({gravity: 'e', html: true});
            $( '.tip-w' ).tipsy({gravity: 'w', html: true});
            $(document).mouseup(function(){
                if($("#log-button").length!=0){
                    $("#loginbox").fadeOut();
                }
            });
			break;
        case "1000":
            $("#mobilemenu").addClass("hidden");
            $("#mobiledropdown").css("display","block");
            $(".lateral-redes, .lateral-afore").show();
            $(".social-footer, .central-afore").addClass("hidden");
            $( '.tip-redes' ).tipsy({gravity: 'e',cls:"redes"});
            $( '.tip-n' ).tipsy({gravity: 'n', html: true});
            $( '.tip-s' ).tipsy({gravity: 's', html: true});
            $( '.tip-e' ).tipsy({gravity: 'e', html: true});
            $( '.tip-w' ).tipsy({gravity: 'w', html: true});
            $(document).mouseup(function(){
                if($("#log-button").length!=0){
                    $("#loginbox").fadeOut();
                }
            });
            break;

		case "1200":
			$("#mobilemenu").addClass("hidden");
			$("#mobiledropdown").css("display","block");
            $(".lateral-redes, .lateral-afore").show();
            $(".social-footer, .central-afore").addClass("hidden");
            $( '.tip-redes' ).tipsy({gravity: 'e',cls:"redes"});
            $( '.tip-n' ).tipsy({gravity: 'n', html: true});
            $( '.tip-s' ).tipsy({gravity: 's', html: true});
            $( '.tip-e' ).tipsy({gravity: 'e', html: true});
            $( '.tip-w' ).tipsy({gravity: 'w', html: true});
            $(document).mouseup(function(){
                if($("#log-button").length!=0){
                    $("#loginbox").fadeOut();
                }
            });
			break;
	}
});


$(document).ready(function() {
    $(".social_feed").data("elms",new Array());
	$("body").trigger("layoutChange");
    $("#mobilemenu").on("click",function(){
        $("#mobiledropdown").toggleClass("dropped");
        if($("#mobiledropdown").hasClass("dropped"))
            $("#mobiledropdown").slideDown("fast",function(){
            });
        else
            $("#mobiledropdown").slideUp("fast",function(){
            });
    });

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

//    TABLA
    $(".azul-blanco tbody tr:even").css("background-color", "#cfe8f7");

    var actual = window.location.pathname;
    actual = actual.split("/");
    actual = actual[actual.length-1];

   $('.navigation').find("a").each(function(){
       var linkcito = $(this).attr('href').split("..")[1];
       linkcito = linkcito.split("/");
       linkcito = linkcito[linkcito.length-1];
       if (linkcito == actual){
           $(this).parent("li").addClass('active');
       }
   });

   $('.menu-hover-izquierda').find("ul").find("a").each(function(){
       var submenu = $(this).attr('href').split("/");
       submenu = submenu[submenu.length-1];
       if(actual != ''){
           if (submenu == actual){
               $(this).parent("li").addClass('menu-hover-izquierda-hover');
           }
       }
   });


    var nav_alto = $(".nav_espacio").height();
    $("#quien-las-regula2, #fondos-de-ahorro2, #tienen-afore2, #cuentas-individuales2, #donde-esta-dinero2, #administran2").css({ position: 'absolute', marginTop: -nav_alto+"px"});


    var ancho;
    switch($("body").attr("layout")) {
        case "MOB":
            ancho = '90%';
            break;
        case "720":
            ancho = '75%';
            break;
        case "960":
            ancho = '45%';
            break;
        case "1200":
            ancho = '35%';
            break;
    }

    $(".tipos-tramites").fancybox();
    $(".tramites").fancybox();
    $(".fancy-azul").fancybox( {
        onComplete: function() {
            $('#fancybox-outer').css('background', '#004b8d');
            $('#fancybox-content').css('border-color', '#004b8d');
        },
        onClosed: function() {
            $('#fancybox-outer').css('background', '#fff');
            $('#fancybox-content').css('border-color', '#fff');
        }
    });
    $("#fancy-tres").fancybox( {
        onComplete: function() {
            $('#fancybox-outer').css('background', '#004b8d');
            $('#fancybox-content').css('border-color', '#004b8d');
        },
        onClosed: function() {
            $('#fancybox-outer').css('background', '#fff');
            $('#fancybox-content').css('border-color', '#fff');
        }
    });
    $(".opciones").fancybox({
        onComplete: function() {
            $('#fancybox-outer').css('background', '#fff');
            $('#fancybox-content').css('border-color', '#fff');
        },
        onClosed: function() {
            setTimeout("test()",1);
        }
    });
    $(".opcion-imsste").fancybox({
        onComplete: function() {
        },
        onClosed: function() {
            setTimeout("test()",1);
        }
    });
    $(".cambio-iframe").fancybox({
        type: "iframe",
        width: 810,
        height: 500
    });

    $(".cambio-iframe2").fancybox({
        type: "iframe",
        width: 810,
        height: 500,
        onClosed: function() {
            setTimeout("test2()",1);
        }
    });

    $(".ayuda-mini").click(function () {
        $(this).animate({right: '-77px'}, 300);
        $(".ayuda-big").animate({right: '-1px'}, 1500);
        _gaq.push(['_trackEvent', 'LinksDerecha', 'Necesitas Ayuda', "Mostrar"]);
        return false;
    });

    $(".cerrar-ayuda, #close-assistant").click(function (event) {
        event.preventDefault();
        $(".ayuda-big").stop().removeClass("aux").animate({right: '-271px'}, 500);
        $(".ayuda-mini").stop().animate({right: '0'}, 2000);
        event.stopPropagation();
        console.log("closed");
        $.cookie('bye_assistant', 'yep', {expires: 1, path: '/'});
        _gaq.push(['_trackEvent', 'LinksDerecha', 'Necesitas Ayuda', "Cerrar"]);
        return false;
    });

    $("#noticias div a").click(function (event) {
        event.preventDefault();
        $.fancybox.close();
        var rel =  $(this).attr("rel");
        var href = $(this).attr("href");
        switch(rel) {
            case "interno":
                window.location.href = href;
                break;
            case "externo":
                window.open(href, '_blank');
                break;
            case "frame":
                return 0;
                break;
        }
    });

    $(".lightbox").fancybox();

//    $('#log-button').click(function(a) {
//        a.preventDefault();
//        $("#loginbox").stop().slideToggle();
//        return false;
//    });
    $("#loginbox").mouseup(function(){
        return false;
    });
    $("#log-button").mouseup(function(){
        return false;
    })
    
});

function test() {
    $("#fancy-tres").trigger("click");
}

function test2() {
    $("#noticias-link").trigger("click");
    console.log("plop");
}

// (function($){
//     $(document).bind("idle.idleTimer", function(){
//         $(".ayuda-mini").animate({right: '-25px'}, 1000);
//         $(".ayuda-big").animate({right: '-1px'}, 500);
//     });

//     $(document).bind("active.idleTimer", function(){
//     });

//     $.idleTimer(timeout);
// })(jQuery);
//

function asistente(){

    var mini = $(".ayuda-mini");
    var big = $(".ayuda-big");
    var scrolloff = $("footer").height();
    var altura = document.body.scrollHeight;
    var altura_porcentaje = altura - scrolloff;
    var coso = altura_porcentaje - 50;

    if($.cookie('bye_assistant') == null){
        $(window).on('scroll', function(event) {
            var altura = document.body.scrollHeight;
            var altura_porcentaje = altura - scrolloff;
            var offset = $(".ayuda-mini").offset();
            if(offset.top >= altura_porcentaje && big.hasClass("hiding")) {
                big.removeClass("hiding");
                $(".ayuda-big, .ayuda-mini").clearQueue();
                mini.stop().animate({right: '-77px'}, 300);
                big.stop().animate({right: '0'}, 1500);
            }
            else if(!big.hasClass("hiding") && offset.top <= coso ){
                big.addClass("hiding");
                $(".ayuda-big, .ayuda-mini").clearQueue();
                mini.stop().stop().animate({right: '0'}, 100);
                big.stop().stop().animate({right: '-270px'}, 1000);
            }
            event.stopPropagation();
            return false;
        });
    };
    return false;
}


$(function(){
	$(".social_feed").fbWall({
		id:'Profuturo.GNP',
		accessToken:'AAAEfYnC5JGABADG4Ah3dFtEgkxlPf5usqqdWJHv85jp4oAjaKEBUh93Lyr2lI0QZB7HK8lyqnVHKMQNlRRbJoFKG7wXsA55z9eZAbufgZDZD',
		showGuestEntries:false,
		showComments:false,
		max:1,
		timeConversion:24,
		callback:fb_cb
	});
});
$(function($){
	$(".social_feed").tweet({
		username: "ProfuturoGNP",
		join_text: "auto",
		count: 1,
		auto_join_text_default: "",
		auto_join_text_ed: "",
		auto_join_text_ing: "",
		auto_join_text_reply: "",
		auto_join_text_url: "",
		loading_text: ""
	});
}).bind("loaded", function (e,result) {
		var a = $(".social_feed").data("elms");
		var b = a.concat(result);
		$(".social_feed").data("elms",b);
		if (b.length>1) printList();
	});

function fb_cb(result) {
	var a = $(".social_feed").data("elms");
	var b = a.concat(result);
	$(".social_feed").data("elms",b);
	if (b.length>1) printList();
}

function printList() {
	var a = $(".social_feed").data("elms");
	a.sort( function(row1, row2) {
		var k1 = row1["created_time"], k2 = row2["created_time"];
		return (k1 > k2) ? 1 : ( (k2 > k1) ? -1 : 0 );
	} );

	for (var post in a) {
		var s = (a[post].source=="fb"?"facebook":"twitter");
		var c = $("<li />").addClass("footer-"+s).html(a[post].message);
		c.prepend($("<span />").addClass("span-"+s)).append($("<br />")).append($("<span />").addClass("footer-social-url").html("<a href='"+a[post].url+"'>Leer mas &rarr;</a>"));
		$(".social_feed").append(c);
	}

}

function showcaptcha(key){
    Recaptcha.create(key,
    "createcaptcha",{
        theme: "white"
        //callback: Recaptcha.focus_response_field
        }
    );
    return key;
}

function strip(html) {
    var tmp = document.createElement("DIV");
    tmp.innerHTML = html;
    if (!String.prototype.trim) {
        String.prototype.trim=function(){return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');};
    }
}


$(document).ready(function () {
    /*
        HEADER & LINKS DERECHA
     */
    $(".header-logo a").attr("ga-label","Pagina principal");

    $(".header-corporate-menu a").each(function () {
        $(this).attr("ga-label",$(this).html());
    });

    $("#log-button").attr("ga-label","Ingresa a tu cuenta");

    $("header .section").each(function () {
        $(this).attr("ga-label",$(".title",$(this)).html());
    });

    $("header a").each(function () {
        $(this).attr("ga-category","MenuPrincipal");
        $(this).attr("ga-action","click");
    });

    $("header .menu-hover a").each(function () {
        $(this).attr("ga-label", $(this).parents(".menu-hover").siblings("a").attr("ga-label") + " - " + strip($(this).html()));
    });

    $("a.cambio-iframe").attr("ga-category","LinksDerecha").attr("ga-label",strip($("a.cambio-iframe").html())).attr("ga-action","click");


    $(".lateral-redes a").each(function () {
        $(this).attr("ga-category","LinksDerecha").attr("ga-label","Social - " +$(this).attr("original-title")).attr("ga-action","click");
    });

    $(".asistente-button").click(function () {
        _gaq.push(['_trackEvent', "LinksDerecha", "Necesitas Ayuda", "Enviar"]);
    });

    /*
        SECCION AFORE
     */
    $(".afore2 a").each(function () {
        $(this).attr("ga-category","Para que sirve mi AFORE").attr("ga-label",strip($(this).html())).attr("ga-action","click");
    });
    $(".afore2 .ventajas a").each(function () {
        $(this).attr("ga-action","Descarga");
    });
    $(".afore4 .afore4bbeta a").each(function () {
        $(this).attr("ga-category","Pension IMSS").attr("ga-label",strip($(this).html())).attr("ga-action","Preguntas");
    });
    $(".afore4 .afore4cbeta a").each(function () {
        $(this).attr("ga-category","Pension ISSSTE").attr("ga-label",strip($(this).html())).attr("ga-action","Info");
    });
    $(".afore5");


    /*
        FOOTER
     */
    $("footer a").each(function () {
        $(this).attr("ga-category","Footer").attr("ga-label",strip($(this).html())).attr("ga-action","click");
    });

});

$(document).ready(function () {
    $('body').delegate("a, button", 'click', function(event) {
        if ($(this).is("[ga-category]")) {
            var optVal = $(this).attr("ga-optval");
            //console.log("category: "+$(this).attr("ga-category")+", label: " + $(this).attr("ga-label") + ", action: " + $(this).attr("ga-action") + (optVal?(", optVal: "+optVal):"") );
            if (optVal) _gaq.push(['_trackEvent', $(this).attr("ga-category"), $(this).attr("ga-action"), $(this).attr("ga-label"), optVal]);
            else _gaq.push(['_trackEvent', $(this).attr("ga-category"), $(this).attr("ga-action"), $(this).attr("ga-label")]);
        }
    });
    $('#duda').change(function(){
        window.location = 'https://www.profuturo.mx/content/wps/portal/ayuda/'+$(this).val();
        
    });
});