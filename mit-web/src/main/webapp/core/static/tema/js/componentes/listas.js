$(function() {
    $('ol.numeros-grises li').prepend(function(index) {
        return "<span class='span-order'>" + (index + 1) + '</span>';
    });
});