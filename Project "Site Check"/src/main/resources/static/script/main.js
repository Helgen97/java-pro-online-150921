$(document).ready(function () {
    submitButton();

    $('.input').focus(function () {
        $(this).parent().find(".label-txt").addClass('label-active');
    });

    $(".input").focusout(function () {
        if ($(this).val() == '') {
            $(this).parent().find(".label-txt").removeClass('label-active');
        }
    });
});

function submitButton() {
    $("#submit").click(function () {
        if ($("#email").val() === "")
            $("#emailError").text('Enter email');
        else
            $("#emailError").text('');

        if ($("#url").val() === "")
            $("#urlError").text('Enter url');
        else
            $("#urlError").text('');


        if (($("#email").val() !== "") && $("#url").val() !== "") {
            if (!validateEmail($("#email").val())) {
                $("#emailError").text('Not valid email');
            } else {
                if (!validateUrl($("#url").val())) {
                    $("#urlError").text('Not valid url');
                } else {
                    const json = {
                        email: $("#email").val(),
                        url: $("#url").val()
                    };

                    $.ajax({
                        type: "POST",
                        url: "/post",
                        contentType: "application/json",
                        data: JSON.stringify(json),
                        dataType: "json",
                        success: function (result) {
                            $(".result-input").val(result.siteStatus);
                        },
                        error: function (xhr, status, error) {
                            //TODO Fail function
                        }
                    })
                }
            }
        }
    })

    function validateEmail(email) {
        const emailReg = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return emailReg.test(email);
    }

    function validateUrl(url) {
        const emailReg = /^(ht|f)tp(s?)\:\/\/[0-9a-zA-Z]([-.\w]*[0-9a-zA-Z])*(:(0-9)*)*(\/?)([a-zA-Z0-9\-\.\?\,\'\/\\\+&amp;%\$#_]*)?$/;
        return emailReg.test(url);
    }

}