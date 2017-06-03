jQuery(document).ready(function () {
    $('#register').on('click', function () {

        var first_name = $("#registration").find('input[name="first name"]');
        var last_name = $("#registration").find('input[name="last name"]');
        var email = $("#registration").find('input[name="e-mail"]');
        var pass = $("#registration").find('input[name="password"]');
        var pass_conf = $("#registration").find('input[name="password confirmation"]');

        if (validate(first_name) && validate(last_name)
            && validate(email) && validate_passwords(pass, pass_conf)) {
            $("#registration").submit();
        } else {
            $("#registration").submit(function () {
            });
            return false;
        }
    });

    function validate(element) {
        var error_message;
        switch (element.attr('name')) {
            case 'first name':
                error_message = validate_first_name(element.val());
                break;
            case 'last name':
                error_message = validate_last_name(element.val());
                break;
            case 'e-mail':
                error_message = validate_email(element.val());
                break;
        }

        if (error_message != '') {
            mistake_found(element, error_message);
            return false;
        } else {
            element.css({'background-color': 'white'});
        }
        return true;
    }

    function validate_passwords(pass1, pass2, e) {
        var is_valid = true;
        var error_message = validate_password(pass1.val(), pass2.val());
        if (error_message != '') {
            mistake_found(pass1, error_message);
            is_valid = false;
        } else {
            pass1.css({'background-color': 'white'});
        }

        error_message = validate_password(pass2.val(), pass1.val());
        if (error_message != '') {
            mistake_found(pass2, error_message);
            is_valid = false;
        } else {
            pass2.css({'background-color': 'white'});
        }
        return is_valid;
    }

    function mistake_found(element, message) {
        element.val('');
        element.attr('placeholder', message);
        element.css({'background-color': 'yellow'});
    }
});
