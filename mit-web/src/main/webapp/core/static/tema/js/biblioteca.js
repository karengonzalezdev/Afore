var elemento;
var cadena = new Array();
var codigo;
var div;
var abrir;
$(document).ready(function () {
    $(".link-ejemplo").click(function () {
        div = "";
        codigo = "";
        elemento = "";
        abrir = "";
        cadena = [];
        if($(this).hasClass("cerrar")) {
            $(this).text("Ver código de ejemplo");
            $(this).removeClass("cerrar");
        }
        else {
            $(this).addClass("cerrar");
            $(this).text("Cerrar código");
        }
        codigo = $(this).attr("href");
        div = ($(this).attr("href")).substr(1) + "-ejemplo";
        $(codigo).toggle();
        $(codigo).empty();

        var container = document.getElementById (div);
        getChildNodes(container);

        function getChildNodes(cont) {
            for (var i = 0; i < cont.childNodes.length; i++) {
                var child = cont.childNodes[i];
                if (child.nodeType == 3) {
                    // alert("No hagas nada");
                }
                else {
                    if (child.nodeType == 1) {
                        $(codigo).append(quitarEtiquetas(child.outerHTML.toString()) + "<br>");
                    }
                }
            }
        }

        function quitarEtiquetas(str) {
            return str.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/\n/g,"<br />") ;
        }
    });
});