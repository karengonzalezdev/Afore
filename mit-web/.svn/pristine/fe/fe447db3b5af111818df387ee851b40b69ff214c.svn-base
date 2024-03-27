// Creando un GET global
;(function(window){
var
 $_GET = window.$_GET = {},
 $_VAN = window.$_VAN = {},
 location = window.location,
 search = location.search,
 href = location.href,

 index = search.indexOf('?') != -1 ? search.indexOf('?') + 1 : 0,
 get = search.substr(index).split('&'),
 vanity = href.replace(/^https?:\/\/(.*?)\//i, '').replace(/\?.*$/i, '').split('/');

 for (var i in get){
 var split = get[i].split('=');
 $_GET[split[0]] = split[1]||null;
 }
 for (var i in vanity)
 $_VAN[i] = vanity[i]||null;
})(window);
// Document Ready


$(document).ready(function(){
	
    $(".doscolumnas").hide();
    $(".btn_instrucciones").click(function(){
        event.preventDefault();
        $(".doscolumnas").slideToggle();
        if ($(".flecha").hasClass('on')) {
            $(".flecha").removeClass('on').addClass('off');
            $(".flecha").attr('src','img/arrow-down.png');
        } else if ($(".flecha").hasClass('off')) {
            $(".flecha").removeClass('off').addClass('on');
            $(".flecha").attr('src','img/arrow-up.png');
        } else {
            $(".flecha").addClass('on');
            $(".flecha").attr('src','img/arrow-up.png');
        }
    });
});

$.urlParam = function(name){
    var results = new RegExp('[\\?&amp;]' + name ).exec(window.location.href);
    return results == null ? false:true;
}
$(function() {
    //Ejecutando el validador
    var validated = $('.validate').each(function() {
        var form = $(this);
        form.validate({
            errorPlacement: function(error, element) {
                if ( element.is(":radio") )
                    error.appendTo( element.parent().parent().parent().parent());
                else if ( element.is(":checkbox") )
                    error.appendTo(element.parent().parent());
                else
                    error.appendTo(element.parent());
            }
        });
    });

 // if($('#traspaso-registro').length > 0){
   /*   if($.urlParam('showErrors')){
          var validator = $("#traspaso-registro").validate();
          validator.showErrors({"curp": "La CURP proporcionada ya se encuentra asociada a un usuario, favor de revisar este dato ya que no es posible reg&iacute;strarlo nuevamente"});

          validator.showErrors({"curp": "De acuerdo a los registros de informaci&oacute;n de la administradora, ya eres cliente vigente de Profuturo"});

          validator.showErrors({"email": "Este correo electr&oacute;nico ya fue registrado previamente, favor de revisarlo o continuar tu tr&aacute;mite en el cuadro superior derecho"});

          validator.showErrors({"cellphone": "Este n&uacute;mero celular ya fue registrado previamente, favor de revisarlo o continuar tu tr&aacute;mite en el cuadro superior derecho"});

          var validator2 = $("#traspaso-login").validate();
          validator2.showErrors({"login-email": "Este correo electr&oacute;nico no est&aacute; registrado, favor de verificar el dato o iniciar tu tr&aacute;mite, registrando tus datos"});

          validator2.showErrors({"login-pass": "La contrase&ntilde;a proporcionada no coincide con el correo electr&oacute;nico, favor de revisar la informaci&oacute;n e intentarlo de nuevo."});
      }*/
    //}

	
    //Si existe un captcha lo ejecutamos
  // if($('#createcaptcha').length > 0)
    //showcaptcha("6LerpdoSAAAAAE22B6iFJvNTLZoWQ6Q-ehM3-_j_");
   /*if($_GET['ingresar'] == true) {
       if($('#traspaso-login').length > 0) {
           $('.boxed').addClass('traspaso-resalted');
       }
   }*/
    // Si hay un datepicker, lo regionalizamos y abrimos
   if($('.datepicker').length > 0) {
       $.datepicker.regional['es'] = {closeText:"Cerrar",prevText:"&#x3C;Ant",nextText:"Sig&#x3E;",currentText:"Hoy",monthNames:["Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"],monthNamesShort:["Ene","Feb","Mar","Abr","May","Jun","Jul","Ago","Sep","Oct","Nov","Dic"],dayNames:["Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"],dayNamesShort:["Dom","Lun","Mar","Mie","Juv","Vie","Sab"],dayNamesMin:["Do","Lu","Ma","Mi","Ju","Vi","Sa"],weekHeader:"Sm",dateFormat:"dd/mm/yy",firstDay:1,isRTL:!1,showMonthAfterYear:!1,yearSuffix:""};
       $.datepicker.setDefaults($.datepicker.regional['es']);
        $('.datepicker').datepicker({
            dateFormat: 'dd/mm/yy',
            minDate: -365,
            maxDate: new Date()
        });
   }

    //Traspaso 5
//    if($('.extra-form').length > 0) {
//        $('.validate').data('validator').settings.submitHandler = function(form) {
//            $('#send').addClass('oculto');
//            $('.ajaxSpinnerContainer').show();
//            var formdata = $('.validate').serialize();
            //Se envian por ajax los nuevos datos
//            $.ajax({
//               type: 'POST',
//               url: '#', //Insertar aqui la URL a donde se vayan a enviar los datos
//               data: formdata,
//               complete: function (jqXHR, textStatus) {
//                   $('#send').removeClass('oculto');
//                   $('.ajaxSpinnerContainer').hide();
//                   //window.location.href="traspaso6.html"
//               }
//            });
 //       }
  //  }
//    if($('#deposito').length > 0) {
//    	
//        $('#deposito').change(function(){
//        	
//            var valor = $(this).val();
//            
//            if(valor=='1896'){
//                $(".deposito-bancario").slideUp();
//                $(".transferencia-electronica").hide().removeClass('hidden').slideDown();
//            }else if(valor=='1897'){
//                $(".transferencia-electronica").slideUp();
//                $(".deposito-bancario").hide().removeClass('hidden').slideDown();
//            }else{
//                $(".deposito-bancario").slideUp();
//                $(".transferencia-electronica").slideUp();
//            }
//        });
//    }
    
//     $('#newpass2').blur(function(){
//    	
//        	var valor = $(this).val();
//        	var valor2 = $('#confirmpass').val();
//        	
//        	if(valor!=""&&valor2!="")
//        	{
//           		
//        		if(valor == valor2)
//           			{
//           				
//           				  $('#mensajeContrasenas').css({'color':'white'});
//           				  $('#formaqui\\:sendCambiarContrasena').removeAttr('disabled');
//           				//$('#mensajeContrasenas').css({'background':'red','font-size':'red', 'color': 'red','margin-bottom': '30px','margin-top': '5px;'});
//           			}
//           		else
//           			{
//           				  $('#mensajeContrasenas').css({'color':'red'});
//           				  $('#formaqui\\:sendCambiarContrasena').attr('disabled','-1');
//           			}
//           	}
//
//        });
//    
//    $('#confirmpass').blur(function(){
//        	var valor = $(this).val();
//        	var valor2 = $('#newpass2').val();
//        	
//        	if(valor!=""&&valor2!="")
//        	{
//           		
//        		if(valor == valor2)
//           			{
//           				
//           				  $('#mensajeContrasenas').css({'color':'white'});
//           				  $('#formaqui\\:sendCambiarContrasena').removeAttr('disabled');
//           				//$('#mensajeContrasenas').css({'background':'red','font-size':'red', 'color': 'red','margin-bottom': '30px','margin-top': '5px;'});
//           			}
//           		else
//           			{
//           				  $('#mensajeContrasenas').css({'color':'red'});
//           				  $('#formaqui\\:sendCambiarContrasena').attr('disabled','-1');
//           			}
//           	}
//        });
//   
   
   
   $(document).ready(function(){
    	
    	
    	var valor1 = $('#banco-emisor').val();
        var valor2 = $('#deposito').val();
    	if(valor1!=''&&valor2!='')
        	{
           		$(".imprimir-guia").hide().removeClass('hidden').slideDown();
           		//$(".boton-continuar").hide().removeClass('hidden').slideDown();
        		$(".deposito-bancario").slideUp();
                $(".transferencia-electronica").slideUp();
                
        	}
        	else{
           		$(".imprimir-guia").slideUp();
           		$(".boton-continuar").slideUp();
           		$(".deposito-bancario").slideUp();
                $(".transferencia-electronica").slideUp();
           		}
    });
    if($('#banco-emisor').length > 0  && $('#deposito').length > 0) {
    	
        $('#banco-emisor').change(function(){
        	var valor = $(this).val();
        	var valor2 = $('#deposito').val();
        	//if (!$('.imprimir-guia').is (':visible')) alert ("Visible!");
        	if(valor!=''&&valor2)
        	{
           			$(".imprimir-guia").hide().removeClass('hidden').slideDown();
	           		$(".deposito-bancario").slideUp();
	                $(".transferencia-electronica").slideUp();
	                $(".boton-continuar").slideUp();
	            
           	}
        	else{
           		$(".imprimir-guia").slideUp();
           		$(".boton-continuar").slideUp();
           		$(".deposito-bancario").slideUp();
                $(".transferencia-electronica").slideUp();
           		}
        });
        
        $('#deposito').change(function(){
        	var valor = $(this).val();
        	var valor2 = $('#banco-emisor').val();
        	if(valor!=''&&valor2!='')
        	{
           		$(".imprimir-guia").hide().removeClass('hidden').slideDown();
        		$(".deposito-bancario").slideUp();
                $(".transferencia-electronica").slideUp();
                $(".boton-continuar").slideUp();
        	}
        	else{
           		$(".imprimir-guia").slideUp();
           		$(".boton-continuar").slideUp();
           		$(".deposito-bancario").slideUp();
                $(".transferencia-electronica").slideUp();
           		}
        });
        
        $('#sasa\\:botonPrueba').click(function(){
        	var valor = $('#deposito').val();
        	
			
        	$(".boton-continuar").delay(10000).hide().removeClass('hidden').slideDown();
           	
           	if(valor=='1896'){
                $(".deposito-bancario").slideUp();
                $(".transferencia-electronica").delay(10000).hide().removeClass('hidden').slideDown();
            }else if(valor=='1897'){
                $(".transferencia-electronica").slideUp();
                $(".deposito-bancario").delay(10000).hide().removeClass('hidden').slideDown();
            }else{
                $(".deposito-bancario").slideUp();
                $(".transferencia-electronica").slideUp();
                $(".boton-continuar").slideUp();
            }
           		
});  
        
        $('#formPrueba').submit(function(){
        	alert("submit");
        	
        });
        
        
}	
    
    //Traspaso 16
   else if ($('confirma-form').length > 0) {
       $(".validate").data("validator").settings.submitHandler = function (form) {
          $('#send').addClass('oculto');
          $('#cancel').hide();
          $('.ajaxSpinnerContainer').show();
           var formdata = $('.validate').serialize();
           //Se envian por ajax los nuevos datos
           $.ajax({
               type: 'POST',
               url: '#', //Insertar aqui la URL a donde se vayan a enviar los datos
               data: formdata,
               complete: function (jqXHR, textStatus) {
                   $('.validate input:not(:submit)').each(function(i, el) {
                       var input = $(this);
                       $('#field-' + input.attr('id')).text(input.val());
                   });
                   $('.edicion, .form-label, #send-nochanges').show();
                   $('#send').removeClass('oculto');
                   $('input').addClass('disabled').attr('disabled','disabled');
                   $('.ajaxSpinnerContainer').hide();
               }
           });
       }
   }
   
    //Ejemplo Iniciar Tramite document.getElementById('formAjaxSoliitar:nss').placeholder="(10 dígitos: clave lada y número telefónico)";
    else if ($('#traspaso-login').length > 0) {    		
		 	$(".img-Soli").hover(		 		
				function () {						
				    $(this).attr("src","/MediosDiversosWeb/img/header-soli2.png");
				},
				function () {						
				    $(this).attr("src","/MediosDiversosWeb/img/header-soli.png");
				});
		 	$('#id_img_Soli').css( 'cursor', 'pointer' );
// $( "#traspaso-login.validate" ).load(alert("termino"));
    	// $(document).ajaxStart(function() {
   			//$( "#bloquea" ).show();
 		//});
    	//alert($('#traspaso-login.validate'));
    	//console.log($('#traspaso-login.validate'));
    	//console.log($('#traspaso-login'));
    	//$( "#traspaso-login\\:send-login" ).on( "click", function( event ) {
		//		alert("ejemplo");
		//	});
    	
    }
   //Autenticar Cuenta
	else if ($('#formAjaxAutenticarCuenta').length > 0) {
		
		  $('.monto').on('blur', function(){
		     if(/^[1-9]+[0-9]{0,4}$/.test(document.getElementById('formAjaxAutenticarCuenta:monto').value)){		         
	         	document.getElementById('formAjaxAutenticarCuenta:monto').value=document.getElementById('formAjaxAutenticarCuenta:monto').value + ".00"
		     	document.getElementById('formAjaxAutenticarCuenta:monto').focus();
		     	document.getElementById('formAjaxAutenticarCuenta:rastreo').focus(); 		         		   
		     } 			
		 });
	}
    
       //Corrige Datos
	else if ($('#form-CorrigeDatos').length > 0) {
		 
		var tipoUsuarioMenu=document.getElementById('tipoUsuarioMenu').innerHTML;   	
	  	if(tipoUsuarioMenu == "true"){
	  		 //es asesor	
	 		$('.Paso1_Asesor').show();	
	 	}else{
	 		$('.Paso1_Usuario').show();	
	 	}
		 		
		
		
		
		$('.monto').on('blur', function(){
		     if(/^[1-9]+[0-9]{0,4}$/.test(document.getElementById('form-CorrigeDatos:monto').value)){		         
	         	document.getElementById('form-CorrigeDatos:monto').value=document.getElementById('form-CorrigeDatos:monto').value + ".00"
		     	document.getElementById('form-CorrigeDatos:monto').focus();
		     	document.getElementById('form-CorrigeDatos:rastreo').focus(); 		         		   
		     } 			
		 });
	}
  //SolicitarTraspaso
   else if ($('#form-solicita').length > 0) {
	  
       $('.dnss').show();
       $('.motivo').slideUp();
       var issste =document.getElementById('form-solicita:regimen:1').checked;
       if(issste != true){
        	document.getElementById('form-solicita:nss').required=true;
       }
    	
    	if(document.getElementById('form-solicita:comentarios').value != ""){
    	   $('.motivo').slideDown();	 	                	
            document.getElementById('form-solicita:comentarios').required=true;
            document.getElementById('form-solicita:comentarios').maxLength="40";
       }

        $('.imssissste').on('change', function(){        
        	var value =document.getElementById('form-solicita:regimen:0').checked;
        	 var ambos =document.getElementById('form-solicita:regimen:2').checked;
         //   var value = $('input[id="regimen:0"]:checked').val();
           //  var ambos = $('input[id="regimen:2"]:checked').val();
            if(value == true) {            	
                $('.dnss').show();
                //document.getElementById('bloquea').style.display="block";
                document.getElementById('form-solicita:nss').value="";
                document.getElementById('form-solicita:nss').required=true;
                //document.getElementById('formAjaxSoliitar:nss').placeholder="(10 dígitos: clave lada y número telefónico)";
            } else if((ambos  == true)){
            	 $('.dnss').show();
                //document.getElementById('bloquea').style.display="block";
                document.getElementById('form-solicita:nss').value="";
                document.getElementById('form-solicita:nss').required=true;
            }else {
            	//alert("else");
            	//document.getElementById('bloquea').style.display="none";
            	// $("cargaMensaje").hide(); 
            	document.getElementById('form-solicita:nss').value="";
                 document.getElementById('form-solicita:nss').required=false;
            	$('.dnss').hide();              
                // $("#nss").rules('remove', 'required');
            }
        });
        
         $('#form-solicita\\:selectMotivo').on('change', function(){  
        	 
	        var valorMotivoRechazo = $('#form-solicita\\:selectMotivo').val();	  	         
	        if(valorMotivoRechazo == 4055){
	        	$('.motivo').slideDown();	 	        
	        	document.getElementById('form-solicita:comentarios').value="";
	            document.getElementById('form-solicita:comentarios').required=true;
	            document.getElementById('form-solicita:comentarios').maxLength="40";
	             
	        }else{
	        	$('.motivo').slideUp();	        	        
	        	 document.getElementById('form-solicita:comentarios').value="";
	             document.getElementById('form-solicita:comentarios').required=false;
	        }
        });
       
    	
    }
    
     //form-BuroCredito
   else if ($('#form-BuroCredito').length > 0) {
	  
       
       $('.numTarjeta').slideUp();
       $('.numHipotecario').slideUp();
       $('.numAutomotriz').slideUp();
       
       //var indTarjeta = document.getElementById('form-BuroCredito:creditoBancario:0').checked;
       //form-BuroCredito:creditoHipotecario:0
       //form-BuroCredito:creditoAutomotriz:0
     /*  if(indTarjeta == true){
        	document.getElementById('form-solicita:nss').required=true;
       }*/

       $('.indRadioTC').on('change', function(){        
        	 var value =document.getElementById('form-BuroCredito:creditoBancario:0').checked; 
        	 var inputNumTC=document.getElementById('form-BuroCredito:numTDC');
        	 var comboNumTC=document.getElementById('form-BuroCredito:instOtorgante');
        	 var inputLimTC=document.getElementById('form-BuroCredito:limitCreditoTDC');
            if(value == true) {             	
                $('.numTarjeta').show();
                inputNumTC.value=""; inputNumTC.required=true;                
                comboNumTC.value=""; comboNumTC.required=true;
                inputLimTC.value=""; inputLimTC.required=true;
            } else {            	
            	inputNumTC.value=""; inputNumTC.required=false;                            
            	comboNumTC.value=""; comboNumTC.required=false;
            	inputLimTC.value=""; inputLimTC.required=false;
            	$('.numTarjeta').hide();
            }
        });
       
        $('.indRadioCH').on('change', function(){        
        	 var value =document.getElementById('form-BuroCredito:creditoHipotecario:0').checked; 
        	 var inputNumTC=document.getElementById('form-BuroCredito:numCredHipo');
        	 var comboNumTC=document.getElementById('form-BuroCredito:instOtorganteHipo');        	 
            if(value == true) {             	
                $('.numHipotecario').show();
                inputNumTC.value=""; inputNumTC.required=true;                
                comboNumTC.value=""; comboNumTC.required=true;             
            } else {            	
            	inputNumTC.value=""; inputNumTC.required=false;                            
            	comboNumTC.value=""; comboNumTC.required=false;            
            	$('.numHipotecario').hide();
            }
        });
        
            $('.indRadioCA').on('change', function(){        
        	 var value =document.getElementById('form-BuroCredito:creditoAutomotriz:0').checked; 
        	 var inputNumTC=document.getElementById('form-BuroCredito:numCredAuto');
        	 var comboNumTC=document.getElementById('form-BuroCredito:instOtorganteAuto');        	 
            if(value == true) {             	
                $('.numAutomotriz').show();
                inputNumTC.value=""; inputNumTC.required=true;                
                comboNumTC.value=""; comboNumTC.required=true;             
            } else {            	
            	inputNumTC.value=""; inputNumTC.required=false;                            
            	comboNumTC.value=""; comboNumTC.required=false;            
            	$('.numAutomotriz').hide();
            }
        });
       
    }

    //Referencias SolicitarTraspaso3
    else if ($('#form-solicitaTraspaso3').length > 0) {
    		document.getElementById('form-solicitaTraspaso3:parentesco1').tabIndex="1";
    		document.getElementById('form-solicitaTraspaso3:curpRef1').tabIndex="2";
    				
    	var numCompAsigBlur=16;
    	for(var i=0; i < numCompAsigBlur; i++){
    		var campo=".onBlurCampo";    		
    		campo +=i+1;    		
    		if(i != 15 && i!=11 && i!=3){
    			//onchange
    			$(campo).on('keydown', function(e){		  		  	
			        var TABKEY = 9;				     
			        if(e.keyCode == TABKEY) {
			        		this.value=this.value.trim();
			        		var campo2="onBlurCampo";
			        		var campo3=".onBlurCampo";
			        		var aux ="";
			        		for(var o=0; o < 16; o++){
			        			campo2 +=o+1;
			        			campo3 +=o+2;		        		
			        			aux=this.id;		        					        			        		
			        			aux="#"+aux.replace(":","\\:");
			        			if($(aux).hasClass(campo2)){		        						        				
			        				$(campo3).focus();		        				
			        			}
			        			campo2="onBlurCampo";
			        			campo3=".onBlurCampo";
			        		}					    					
			            if(e.preventDefault) {
			                e.preventDefault();
			            }
			            return false;
			        }	
			 	});    			
    		}
	    	
    	}
	    
    	//
       var formulario="form-solicitaTraspaso3";
       var tipoUsuarioValue=document.getElementById(formulario+':tipoUsuario').innerHTML;       
       if(tipoUsuarioValue == "true"){
    	   //Es asesor    	 
    	   $('.Paso2_Asesor').show();   		
    	   $('.dnss').show();       
	       var issste =document.getElementById(formulario+':regimen:1').checked;
	       if(issste != true){
	        	document.getElementById(formulario+':nss').required=true;
	       }
	    	
	        $('.imssissste').on('change', function(){        
	        	var value =document.getElementById(formulario+':regimen:0').checked;
	        	 var ambos =document.getElementById(formulario+':regimen:2').checked;
	            if(value == true) {            	
	                $('.dnss').show();                
	                document.getElementById(formulario+':nss').value="";
	                document.getElementById(formulario+':nss').required=true;                
	            } else if((ambos  == true)){
	            	 $('.dnss').show();                
	                document.getElementById(formulario+':nss').required=true;
	            }else {
	            	document.getElementById(formulario+':nss').value="";
	                 document.getElementById(formulario+':nss').required=false;
	            	$('.dnss').hide();                              
	            }
	        });
       }else{    	   
    	   $('.Paso3_Usuario').show();  
    	   $('.dnss').hide();
    	   $('.imssissste').hide(); 
    	   $('.labelImss').hide();
    	   
       }
	
    }
    
    //Contrato
   else if ($('#formAjax').length > 0) {
	 
	   	
	     $('.monto').on('blur', function(){
		     if(/^[1-9]+[0-9]{0,4}$/.test(document.getElementById('formAjaxAutenticarCuenta:monto').value)){		         
	         	document.getElementById('formAjaxAutenticarCuenta:monto').value=document.getElementById('formAjaxAutenticarCuenta:monto').value + ".00"
		     	document.getElementById('formAjaxAutenticarCuenta:monto').focus();
		     	document.getElementById('formAjaxAutenticarCuenta:rastreo').focus(); 		         		   
		     } 			
		 });
	  // $("#formAjax\\:send-login").click(function(){
		//  $('#formAjax\\:send-login').load(alert("ejempllo")	);
	//	});
	  
	   
	   //alert("ejemplo");
	    // $('.ajaxSpinnerContainer').show();
	      /*   $('#ejemploMensaje').load(function() {
			  	alert("ejem");
			});*/
	  // $(".cargaMensaje").slideUp();
			//document.getElementById('bloquea').style.display="block";
      
    }
    
     //MensajeAutenticacion
    
     else if ($('#form-mensajeAutenticacion').length > 0) {	
    	
   		var tipoUsuarioValue=document.getElementById('tipoUsuario').innerHTML;   	
    	 if(tipoUsuarioValue == "true"){
   		 	$('.recuadro-azul').hide();	
   		 	if($('.texto-MensajeExito') != null){
   		 		$('.texto-MensajeExito').hide();
   		 	}
   		 	$('.Paso1_Asesor').show();
   		 }else{
   			 $('.Paso1_Usuario').show();
   		 }
   		 	
   	}
    
    //Buscar Tramite
    else if ($('#form-Buscar-Tramite').length > 0) {	
    	
   		$( "#form-Buscar-Tramite\\:clean" ).on( "click", function( event ) {	   			
				document.getElementById('form-Buscar-Tramite:curp').value="";				
				return false;
			});
    	 /*if(document.getElementById('form-Buscar-Tramite:curp')){
   		 	$('.recuadro-azul').hide();	
   		 	if($('.texto-MensajeExito') != null){
   		 		$('.texto-MensajeExito').hide();
   		 	}
   		 }*/
   		 	
   	}
    //Solicitar Traspaso 2
    
     else if ($('#form-finalizar').length > 0) {
	
    
    }
    
 
     else if ($('#form-CapturaTramite').length > 0) {
    	 var formulario="form-CapturaTramite";
    	 
		  $('.monto').on('blur', function(){
		     if(/^[1-9]+[0-9]{0,4}$/.test(document.getElementById(formulario+':monto').value)){		         
	         	document.getElementById(formulario+':monto').value=document.getElementById(formulario+':monto').value + ".00"
		     	document.getElementById(formulario+':monto').focus();
		     	document.getElementById(formulario+':rastreo').focus(); 		         		   
		     } 			
		 });
    	  
     	var tipoUsuarioMenu=document.getElementById('tipoUsuarioMenu').innerHTML;   	
	  	if(tipoUsuarioMenu == "true"){
	  		 //es asesor	
	 		$('.Paso1_Asesor').show();	
	 	}
        
    }
    

    
    //Mensaje Servidor No disponible
     else if ($('#formMensajeServidorNoDisponible').length > 0) {
		setTimeout(function(){              
			document.getElementById('boton-IniciarTramite').click();
               
            }, 
            6000);	
    
    }
    //Traspaso 12
    else if ($('#form-finaliza').length > 0) {
        $('.validate').data('validator').settings.errorPlacement = function(error, element) {
            if ( element.is(":radio") )
                error.appendTo( element.parent().parent());
            else if ( element.is(":checkbox") )
                error.appendTo(element.parent().parent());
            else
                error.appendTo(element.parent());
        }
        $('.validate').data('validator').settings.submitHandler = function (form) {
            $('#send').addClass('oculto');
            $('.ajaxSpinnerContainer').show();
            var formdata = $('.validate').serialize();
            //Se envian por ajax los nuevos datos
            $.ajax({
               type: 'POST',
               url: '#', //Insertar aqui la URL a donde se vayan a enviar los datos
               data: formdata,
               complete: function (jqXHR, textStatus) {
                   $('#send').removeClass('oculto');
                   $('.ajaxSpinnerContainer').hide();
                   $('.ajaxSpinnerContainer').hide();
                   //window.location.href="traspaso7.html"
               }
            });
        }
    }
    
    
      //MedioAutenticacion
     else if ($('#form-MedioAutenticacion').length > 0) {
       $(".explain_box").hide();
        $("#form-MedioAutenticacion\\:select-certificacion").change(function(){        	
            var actual = $(this).val();
            $(".explain_box").hide();
            $("#certificacion-"+actual).show();
        });
        
    }
 
   // Consultar Constancia
    
     else if ($('#form-consultarCostancia').length > 0) { 
    	 var idConstancia=document.getElementById('form-consultarCostancia:idComboTipoConstancia').value;
    	 
    	  
    	 
    	 if(idConstancia == "5596"){//id Constancia Registro      	              	   
         	 $(".classCheckRegUnif").slideDown();         	          	         	  
          }else{
        	  $(".classCheckRegUnif").slideUp();
          }    
    	 
         $("#form-consultarCostancia\\:idComboTipoConstancia").change(function(){      	                  	      	   
        	 var idConstancia=document.getElementById('form-consultarCostancia:idComboTipoConstancia').value; 
              if(idConstancia == "5596"){//id Constancia Registro      	              	   
             	 $(".classCheckRegUnif").slideDown();         	          	         	  
              }else{//id Constancia Traspaso      	   
             	 $(".classCheckRegUnif").slideUp();      	   
              }
          });
         

      }
    
        //Constancia de Traspaso
     else if ($('#form-solicitudCostancia').length > 0) {
       $(".explain_box").hide();
        
       
       $("#form-solicitudCostancia\\:idComboMedioIdentificador").change(function(){      	                  	      	   
    	   var actual = $(this).val();
            if(actual == "5598"){//Selecciona IdSMS            	
            	$(".divCompaniaTelefonica").show();
            	$("#form-solicitudCostancia\\:telefono-Movil").addClass("required");
            	$("#form-solicitudCostancia\\:idCompaniaTelefonica").addClass("required");            	
            	
            }else{
            	$(".divCompaniaTelefonica").hide();
            	$("#form-solicitudCostancia\\:telefono-Movil").removeClass("required");
            	$("#form-solicitudCostancia\\:idCompaniaTelefonica").removeClass("required");            	            	            	            	
            	            	
            } 
            
            //$("#certificacion-"+actual).show();
        });
         
       if(document.getElementById('form-solicitudCostancia:idHiddenTipoConstancia').innerHTML == "5596"){//id Constancia Registro
    	   
    	   $(".textoConstanciaRegistro").show();   
    	   $(".textoConstanciaTraspaso").hide();
    	   
    	   $("#form-solicitudCostancia\\:field-nss").addClass("nss");
    	   document.getElementById('form-solicitudCostancia:field-nss').maxLength="11";
    	       	       
    	   $("#form-solicitudCostancia\\:field-name").addClass("required nombre");
    	   document.getElementById('form-solicitudCostancia:field-name').maxLength="40";
    	 
    	   $("#form-solicitudCostancia\\:field-firstlastname").addClass("required paterno");
    	   document.getElementById('form-solicitudCostancia:field-firstlastname').maxLength="40";
    	   
    	   $("#form-solicitudCostancia\\:field-secondlastname").addClass("materno");
    	   document.getElementById('form-solicitudCostancia:field-secondlastname').maxLength="40";    	       	   
    	   
       }else{//id Constancia Traspaso
    	   
    	   document.getElementById("form-solicitudCostancia:field-nss").disabled=true;    	   
    	   document.getElementById("form-solicitudCostancia:field-name").disabled=true;
    	   document.getElementById("form-solicitudCostancia:field-firstlastname").disabled=true;
    	   document.getElementById("form-solicitudCostancia:field-secondlastname").disabled=true;
    	  $(".textoConstanciaRegistro").hide();
    	  $(".textoConstanciaTraspaso").show();
    	  // 
    	   
       }
       
       
        
    }
          //Generacion Contrasena Constancia
     else if ($('#form-generarContrasenaConstancia').length > 0) {       
        
       $("#form-generarContrasenaConstancia\\:imprimirSolicitud").click(function(){
    	   	$(".recenter-button").delay(9900).removeClass('larger-button');
             window.setTimeout(end, 10000);           
        });        
       
         if(document.getElementById('form-generarContrasenaConstancia:idHiddenTipoConstancia').innerHTML == "5596"){//id Constancia Registro
          	$('#form-generarContrasenaConstancia\\:actualizarDatosConstancia').hide();
          	$('#form-generarContrasenaConstancia\\:idLabelMensajeConstancia').hide();
          	$('#idImagenImpulsat').hide();
          	
         }else{
        	 $('#form-generarContrasenaConstancia\\:idLabelMensajeConstancia').show();
        	 $('#form-generarContrasenaConstancia\\:actualizarDatosConstancia').show();
        	 $('#idImagenImpulsat').show();
         }
       
    }
          //Constancia Exitoso
    else if ($('#form-constanciaExitoso').length > 0) {               
    	var mostrarDatosConstancia = document.getElementById('idMostrarDatosConstancia').innerHTML    	
    	
    	if(mostrarDatosConstancia != ""){
    		$(".mostrarConstancia").show();
    	}else{
    		$(".mostrarConstancia").hide();
    	}
    	
       $("#form-constanciaExitoso\\:imprimirSolicitud").click(function(){      	   
           $(".recenter-button").delay(9900).removeClass('larger-button'); 
    	   $(".boton-generarContrasena").delay(10000).hide().removeClass('hidden').slideDown();
           window.setTimeout(end, 10000);    	             
        });        
    }
    

});


var oldValue=null;
var contador=0;
function validarDobleCapturaTelefono(comp){    
	comp.value=comp.value.toUpperCase();	
     if(contador == 0 && comp.value != ''){
            oldValue=comp.value;
            contador=1;
            primerCaptura("errorDobleCapturaTelefono","el Tel&eacute;fono");                    
            comp.value="";
            comp.focus();
          
     }
     if(contador==1 && comp.value !=''){
            if(comp.value != oldValue){
            	errorCaptura("errorDobleCapturaTelefono","El Tel&eacute;fono");
            	 contador=0;
                 oldValue=null;
                 comp.value="";

            }else{
            	contador=0;
                oldValue=null;
                document.getElementById('errorDobleCapturaTelefono').innerHTML  = "";	
            }                   
     }
       return comp;
     
}

var oldValueCURP=null;
var contadorCURP=0;
function validarDobleCapturaCURP(comp){    
	comp.value=comp.value.toUpperCase();	
     if(contadorCURP == 0 && comp.value != ''){
            oldValueCURP=comp.value;
            contadorCURP=1;
            primerCaptura("errorDobleCapturaCURP","el CURP");                    
            comp.value="";
            comp.focus();
          
     }
     if(contadorCURP==1 && comp.value !=''){
            if(comp.value != oldValueCURP){
            	errorCaptura("errorDobleCapturaCURP","El CURP");
            	 contadorCURP=0;
                 oldValueCURP=null;
                 comp.value="";

            }else{
            	contadorCURP=0;
                oldValueCURP=null;
                document.getElementById('errorDobleCapturaCURP').innerHTML  = "";	
            }                   
     }
       return comp;
     
}


var oldValueRFC=null;
var contadorRFC=0;
function validarDobleCapturaRFC(comp){    
	comp.value=comp.value.toUpperCase();	
     if(contadorRFC == 0 && comp.value != ''){
            oldValueRFC=comp.value;
            contadorRFC=1;
            primerCaptura("errorDobleCapturaRFC","el RFC");                    
            comp.value="";
            comp.focus();
          
     }
     if(contadorRFC==1 && comp.value !=''){
            if(comp.value != oldValueRFC){
            	errorCaptura("errorDobleCapturaRFC","El RFC");
            	 contadorRFC=0;
                 oldValueRFC=null;
                 comp.value="";

            }else{
            	contadorRFC=0;
                oldValueRFC=null;
                document.getElementById('errorDobleCapturaRFC').innerHTML  = "";	
            }                   
     }
       return comp;
     
}

var oldValueEMAIL=null;
var contadorEMAIL=0;
function validarDobleCapturaEMAIL(comp){    
	comp.value=comp.value.toUpperCase();	
     if(contadorEMAIL == 0 && comp.value != ''){
            oldValueEMAIL=comp.value;
            contadorEMAIL=1;
            primerCaptura("errorDobleCapturaEMAIL","el Correo Electr&oacute;nico");                    
            comp.value="";
            comp.focus();
          
     }
     if(contadorEMAIL==1 && comp.value !=''){
            if(comp.value != oldValueEMAIL){
            	errorCaptura("errorDobleCapturaEMAIL","El Correo Electr&oacute;nico");
            	 contadorEMAIL=0;
                 oldValueEMAIL=null;
                 comp.value="";

            }else{
            	contadorEMAIL=0;
                oldValueEMAIL=null;
                document.getElementById('errorDobleCapturaEMAIL').innerHTML  = "";	
            }                   
     }
       return comp;
     
}

var oldValueTC=null;
var contadorTC=0;
function validarDobleCapturaTC(comp){    
	comp.value=comp.value.toUpperCase();	
     if(contadorTC == 0 && comp.value != ''){
            oldValueTC=comp.value;
            contadorTC=1;
            primerCaptura("errorDobleCapturaTC","el Numero de Tarjeta");                    
            comp.value="";
            comp.focus();
          
     }
     if(contadorTC==1 && comp.value !=''){
            if(comp.value != oldValueTC){
            	errorCaptura("errorDobleCapturaTC","El Numero de Tarjeta");
            	 contadorTC=0;
                 oldValueTC=null;
                 comp.value="";

            }else{
            	contadorTC=0;
                oldValueTC=null;
                document.getElementById('errorDobleCapturaTC').innerHTML  = "";	
            }                   
     }
       return comp;
     
}

var oldValueCveRas=null;
var contadorCveRas=0;
function validarDobleCapturaCveRas(comp){    
	comp.value=comp.value.toUpperCase();	
     if(contadorCveRas == 0 && comp.value != ''){
            oldValueCveRas=comp.value;
            contadorCveRas=1;
            primerCaptura("errorDobleCapturaCveRas","la Clave de rastreo");                    
            comp.value="";
            comp.focus();
          
     }
     if(contadorCveRas==1 && comp.value !=''){
            if(comp.value != oldValueCveRas){
            	errorCaptura("errorDobleCapturaCveRas","La Clave de rastreo");
            	 contadorCveRas=0;
                 oldValueCveRas=null;
                 comp.value="";

            }else{
            	contadorCveRas=0;
                oldValueCveRas=null;
                document.getElementById('errorDobleCapturaCveRas').innerHTML  = "";	
            }                   
     }
       return comp;
     
}

//Funciones para Doble captura
function primerCaptura(label,descCaptura){
	document.getElementById(label).innerHTML  = "Ingresar nuevamente "+ descCaptura +" para confirmar";					
	document.getElementById(label).style.color="#6aaf70";			
}

function errorCaptura(label,descCaptura){	
	document.getElementById(label).innerHTML  = descCaptura +" no coincide con el dato anterior, Favor de revisarlo";					
	document.getElementById(label).style.color="red";												
}

 function begin(){				   				    	
	$.blockUI({ 
	message: '<h3><img src="../../img/ajax-loader.gif" />Por favor espere...</h3>',
	 css: { backgroundColor: 'rgb(255, 255, 255)', width:'240px', height: '55px', color: '#fff'} });				         
}  
function end(){  
  $.unblockUI();
}  

function submitTransaction(e){				   		
     if(e.valid()){
     	begin();
     }
}

function submitTransactionTest(e){				   		
     if(e.valid()){
    	 
     	$.blockUI({ 
		message: '<h3><img src="../../../img/ajax-loader.gif" />Por favor espere...</h3>',
	 	css: { backgroundColor: 'rgb(255, 255, 255)', width:'240px', height: '55px', color: '#fff'} });
     }
}

function submitTransactionTest2(e){
	 $('.extra-large').removeClass('required');
    $.blockUI({ 
		message: '<h3><img src="../../../img/ajax-loader.gif" />Por favor espere...</h3>',
	 	css: { backgroundColor: 'rgb(255, 255, 255)', width:'240px', height: '55px', color: '#fff'} }); 	
}

function ocultarOpciones(){	 
	   
	   if($('#idOpcion2').hasClass('des'))
		{   $('#idOpcion2').slideUp();
	   		$('#idOpcion2-1').slideUp();	
	   		$('#idOpcion2-2').slideUp();
	   		$('#idOpcion2').removeClass('des');
		   $('#idOpcion2-1').removeClass('des');	
		   $('#idOpcion2-2').removeClass('des');
	   }else{
			$('#idOpcion2').slideDown();
	   		$('#idOpcion2-1').slideDown();	
	   		$('#idOpcion2-2').slideDown();	   		
	   		$('#idOpcion2').addClass('des');
	   		$('#idOpcion2-1').addClass('des');	
	   		$('#idOpcion2-2').addClass('des');
	   		
	   }
	  
}

function limpiarMensajeForm(idMensaje){	
	 document.getElementById(idMensaje).innerHTML="";
}
