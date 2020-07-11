

(function ($) {
    "use strict";


    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        return check;
    });


    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }

    $("#login-form").submit(function( event ) {
        var username = $("#email").val();
        var password = $("#pass").val();
        console.log(username);
        console.log(password);

        $.ajax({
            type: "POST",
            headers:{"email":username,"pass":password},
            url: 'http://localhost:8080/doctor/login',
            contentType: 'application/json',
            success:  function (data){
                console.log(data);
                
            },
            fail: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
             },
            error: function(jqXhr, textStatus, errorThrown){
                console.log(errorThrown);
             }


      });
      event.preventDefault();
    });

})(jQuery);
