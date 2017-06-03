function validate_first_name(first_name) {
    if (first_name.length == 0) {
        return 'First name cannot be empty.';
    } else if (!/^[a-z-]+$/.test(first_name)) {
        return 'Incorrect first name';
    }
    return '';
}

function validate_last_name(last_name) {
    if (last_name.length == 0) {
        return 'Last name cannot be empty.';
    } else if (!/^[a-z-]+$/.test(last_name)) {
        return 'Incorrect last name';
    }
    return '';
}

function validate_email(email) {
    if (email.length == 0) {
        return 'Email cannot be empty.';
    } else if (!/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(email)) {
        return 'Incorrect email';
    }
    return '';
}

function validate_password(pass1, pass2) {
    if (pass1.length < 5) {
        return 'Password must be more than 5 symbols.';
    } else if (pass1 != pass2) {
        return 'Passwords do not match.';
    }
    return '';
}
