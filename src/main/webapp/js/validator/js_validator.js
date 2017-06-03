(function () {
    window.addEventListener("submit", function (e) {
        var first_name = document.getElementById('first_name');
        var last_name = document.getElementById('last_name');
        var email = document.getElementById('email');
        var pass = document.getElementById('pass')
        var confirm_pass = document.getElementById('confirm_pass')

        validate(first_name, e);
        validate(last_name, e);
        validate(email, e);
        validate_passwords(pass, confirm_pass, e);

    });

    function validate(element, e) {
        var error_message;
        switch (element.name) {
            case 'first name':
                error_message = validate_first_name(element.value);
                break;
            case 'last name':
                error_message = validate_last_name(element.value);
                break;
            case 'e-mail':
                error_message = validate_email(element.value);
                break;
        }

        if (error_message != '') {
            mark_mistake(element, error_message);
            e.preventDefault();
        } else {
            element.style.background = "white";
        }
    }

    function validate_passwords(pass1, pass2, e) {
        var error_message = validate_password(pass1.value, pass2.value);
        if (error_message != '') {
            mark_mistake(pass1, error_message);
            e.preventDefault();
        } else {
            pass1.style.background = "white";
        }

        error_message = validate_password(pass2.value, pass1.value);
        if (error_message != '') {
            mark_mistake(pass2, error_message);
            e.preventDefault();
        } else {
            pass2.style.background = "white";
        }
    }

    function mark_mistake(mistake, message) {
        mistake.value = '';
        mistake.placeholder = message;
        mistake.style.background = 'yellow';
    }
})()
