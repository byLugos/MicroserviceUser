package user.domain.utils;
import user.domain.exceptions.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

public class ValidateUser {

    private static final String ERROR_INVALID_NAME = "El nombre o el apellido no puede ser nulo, vacío.";
    private static final String ERROR_INVALID_DOCUMENT = "El documento de identidad debe ser numérico.";
    private static final String ERROR_INVALID_EMAIL = "El correo electrónico no es válido.";
    private static final String ERROR_INVALID_PHONE = "El teléfono debe contener un máximo de 13 caracteres y puede contener el símbolo +.";
    private static final String ERROR_UNDERAGE_USER = "El usuario debe ser mayor de edad (18 años o más).";

    public static void validateName(String name, String lastName) {
        if (name == null || name.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new InvalidNameException(ERROR_INVALID_NAME);
        }
    }
    public static void validateDocument(String cc) {
        if (!Pattern.matches("\\d+", cc) || cc.isEmpty()) {
            throw new InvalidDocumentException(ERROR_INVALID_DOCUMENT);
        }
    }
    public static void validateEmail(String email) {
        String emailRegex = "^(?=.*\\d)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!Pattern.matches(emailRegex, email) || email.isEmpty()) {
            throw new InvalidEmailException(ERROR_INVALID_EMAIL);
        }
    }
    public static void validatePhone(String phone) {
        String phoneRegex = "^\\+?\\d{1,13}$";
        if (!Pattern.matches(phoneRegex, phone) || phone.isEmpty()) {
            throw new InvalidPhoneException(ERROR_INVALID_PHONE);
        }
    }
    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula.");
        }
        LocalDate currentDate = LocalDate.now();
        if (birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser en el futuro.");
        }
        return Period.between(birthDate, currentDate).getYears();
    }
    public static void validateAge(LocalDate birthDate) {
        int age = calculateAge(birthDate);
        if (age < 18) {
            throw new InvalidAgeException(ERROR_UNDERAGE_USER);
        }
    }
}
