package user.domain.utils;

public class Constants {
    private Constants(){
    }
    public static final String USER_EXISTS_BY_EMAIL = "El usuario ya existe con el email proporcionado.";
    public static final String DOCUMENT_ALREADY_REGISTERED = "El documento de identidad ya se encuentra registrado.";
    public static final String INVALID_ROLE = "El rol solicitado no existe o el rol no es un 'AUX_BODEGA o 'CLIENTE'";
    public static final String ERROR_INVALID_NAME = "El nombre o el apellido no puede ser nulo, vacío.";
    public static final String ERROR_INVALID_DOCUMENT = "El documento de identidad debe ser numérico.";
    public static final String ERROR_INVALID_EMAIL = "El correo electrónico no es válido.";
    public static final String ERROR_INVALID_PHONE = "El teléfono debe contener un máximo de 13 caracteres y puede contener el símbolo +.";
    public static final String ERROR_UNDERAGE_USER = "El usuario debe ser mayor de edad (18 años o más).";
    public static final String BIRTHDATE_CANNOT_BE_NULL = "La fecha de nacimiento no puede ser nula.";
    public static final String BIRTHDATE_CANNOT_BE_IN_FUTURE = "La fecha de nacimiento no puede ser en el futuro.";


}
