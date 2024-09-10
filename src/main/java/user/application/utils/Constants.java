package user.application.utils;
public final class Constants {
    private Constants() {
    }
    public static final String NAME_NOT_BLANK = "El nombre no puede estar vacío.";
    public static final String LASTNAME_NOT_BLANK = "El apellido no puede estar vacío.";
    public static final String DOCUMENT_NOT_BLANK = "El documento de identidad no puede estar vacío.";
    public static final String DOCUMENT_NUMERIC = "El documento de identidad debe ser numérico.";
    public static final String BIRTHDATE_NOT_NULL = "La fecha de nacimiento no puede estar vacía.";
    public static final String BIRTHDATE_PAST = "La fecha de nacimiento debe ser en el pasado.";
    public static final String EMAIL_NOT_BLANK = "El correo no puede estar vacío.";
    public static final String EMAIL_VALID = "Debe proporcionar un correo electrónico válido.";
    public static final String PASSWORD_NOT_BLANK = "La contraseña no puede estar vacía.";
    public static final String PHONE_NOT_BLANK = "El teléfono no puede estar vacío.";
    public static final String PHONE_PATTERN = "El teléfono debe contener un máximo de 13 caracteres y puede contener el símbolo +.";
    public static final String IDROLE_NOT_NULL = "El ID del rol no puede estar vacío.";
    public static final String ROLE_NOT_FOUND = "El rol solicitado no existe";

}
