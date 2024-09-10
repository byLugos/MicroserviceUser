package user.domain.validations;
import user.domain.exceptions.*;
import user.domain.utils.Constants;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;
public class ValidateUser {
    private ValidateUser(){

    }
    public static void validateName(String name, String lastName) {
        if (name == null || name.isEmpty() || lastName == null || lastName.isEmpty()) {
            throw new InvalidNameException(Constants.ERROR_INVALID_NAME);
        }
    }
    public static void validateDocument(String cc) {
        if (!Pattern.matches("\\d+", cc) || cc.isEmpty()) {
            throw new InvalidDocumentException(Constants.ERROR_INVALID_DOCUMENT);
        }
    }
    public static void validateEmail(String email) {
        String emailRegex = "^(?=.*\\d)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!Pattern.matches(emailRegex, email) || email.isEmpty()) {
            throw new InvalidEmailException(Constants.ERROR_INVALID_EMAIL);
        }
    }
    public static void validatePhone(String phone) {
        String phoneRegex = "^\\+?\\d{1,13}$";
        if (!Pattern.matches(phoneRegex, phone) || phone.isEmpty()) {
            throw new InvalidPhoneException(Constants.ERROR_INVALID_PHONE);
        }
    }
    public static int calculateAge(LocalDate birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException(Constants.BIRTHDATE_CANNOT_BE_NULL);
        }
        LocalDate currentDate = LocalDate.now();
        if (birthDate.isAfter(currentDate)) {
            throw new IllegalArgumentException(Constants.BIRTHDATE_CANNOT_BE_IN_FUTURE);
        }
        return Period.between(birthDate, currentDate).getYears();
    }
    public static void validateAge(LocalDate birthDate) {
        int age = calculateAge(birthDate);
        if (age < 18) {
            throw new InvalidAgeException(Constants.ERROR_UNDERAGE_USER);
        }
    }
}
