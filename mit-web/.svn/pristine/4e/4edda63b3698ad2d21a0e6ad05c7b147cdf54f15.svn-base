$(document).ready(function(){
    if(!$().setMask) {
        $('[placeholder]').focus(function() {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function() {
            var input = $(this);
            if ((input.val() == '' || input.val() == input.attr('placeholder')) && (input.attr('type') != 'password')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur();
        $('[placeholder]').closest('form').submit(function(){
            var frm = $(this).closest('form');
            frm.find('[placeholder]').each(function(){
                var esto = $(this);
                if (esto.val() == esto.attr('placeholder')){
                    esto.val('');
                }
            })
        });
    }
    else{
        $('[placeholder]').setMask({
            mask : null
        });
    }
    $('[placeholder]').closest('form').find("a").click(function(e){
        var frm = $(this).closest("form");
        frm.find('[placeholder]').each(function(){
            var esto = $(this);
            if (esto.val() == esto.attr('placeholder')){
                esto.val('');
            }
            if($().validate) {
                esto.validate().form();
            }
        })

    });
});
