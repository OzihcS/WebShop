package validator;

import DTO.UserDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static util.Constants.Attributes;
import static util.Constants.ErrorMessages;

/**
 * Performs validation of {@link UserDTO} objects fields.
 */
public class UserValidator {

    private static final Pattern NAME_REGEX = Pattern.compile("[a-zA-zа-яА-Я- ]+", Pattern.CASE_INSENSITIVE);

    private static final Pattern EMAIL_REGEX = Pattern.compile("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            Pattern.CASE_INSENSITIVE);

    private Map<String, String> errors;
    private UserDTO userDTO;

    public UserValidator(UserDTO userDTO) {
        errors = new HashMap<>();
        this.userDTO = userDTO;
    }

    /**
     * Validate {@link UserDTO}.
     */
    public void validate() {
        validateFirstName();
        validateLastName();
        validateEmail();
        validatePasswords();
    }

    private void validateFirstName() {
        String firstName = userDTO.getFirstName();

        if (firstName == null || firstName.isEmpty()) {
            errors.put(Attributes.FIRST_NAME, ErrorMessages.FIRST_NAME_EMPTY);
            return;
        }
        if (!NAME_REGEX.matcher(firstName).matches()) {
            errors.put(Attributes.FIRST_NAME, ErrorMessages.WRONG_INPUT_NAME);
            return;
        }
    }

    private void validateLastName() {
        String lastName = userDTO.getLastName();

        if (lastName == null || lastName.isEmpty()) {
            errors.put(Attributes.LAST_NAME, ErrorMessages.LAST_NAME_EMPTY);
            return;
        }
        if (!NAME_REGEX.matcher(lastName).matches()) {
            errors.put(Attributes.LAST_NAME, ErrorMessages.WRONG_INPUT_NAME);
            return;
        }
    }

    private void validateEmail() {
        String email = userDTO.getEmail();

        if (email == null || email.isEmpty()) {
            errors.put(Attributes.EMAIL, ErrorMessages.EMAIL_EMPTY);
            return;
        }

        if (!EMAIL_REGEX.matcher(email).matches()) {
            errors.put(Attributes.EMAIL, ErrorMessages.WRONG_INPUT_EMAIL);
            return;
        }
    }

    private void validatePasswords() {
        String password = userDTO.getPassword();
        String passwordConfirmation = userDTO.getPasswordConfirmation();

        if (password.length() < 5 || passwordConfirmation.length() < 5) {
            errors.put(Attributes.PASSWORD, ErrorMessages.SHORT_PASSWORD);
            errors.put(Attributes.PASSWORD_CONFIRMATION, ErrorMessages.SHORT_PASSWORD);
            return;
        }

        if (!password.equals(passwordConfirmation)) {
            errors.put(Attributes.PASSWORD, ErrorMessages.PASSWORDS_NOT_MATCHES);
            errors.put(Attributes.PASSWORD_CONFIRMATION, ErrorMessages.PASSWORDS_NOT_MATCHES);
        }
    }

    /**
     * Returns {@link Map} of errors where key is fields where error was found, value - error message.
     *
     * @return errors map.
     */
    public Map<String, String> getErrors() {
        return errors;
    }
}
