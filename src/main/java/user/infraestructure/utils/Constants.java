package user.infraestructure.utils;

public class Constants {
    private Constants(){
    }
    public static final String AUTHENTICATION_ERROR = "Error de autenticación: ";
    public static final String INVALID_CREDENTIALS = "Credenciales inválidas";
    public static final String USER_NOT_FOUND = "Usuario no encontrado con email: ";
    public static final String PASSWORD_NULL = "Contraseña para el usuario con email %s es null!";
    public static final String PASSWORD_RETRIEVED = "Contraseña cifrada obtenida de la base de datos para %s: %s";
    public static final String ROLE_NULL = "Rol para el usuario con email %s es null!";
    public static final String ROLE_RETRIEVED = "Rol obtenido para el usuario con email %s: %s";
    public static final String UNAUTHORIZED = "Error: No autorizado";
    public static final String USER_AUTHENTICATION_FAILED = "No se puede establecer la autenticación de usuario: {}";
    public static final String INVALID_JWT_TOKEN = "Token JWT no válido: ";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ADMIN_EMAIL = "admin123@ejemplo.com";
    public static final String ADMIN_FIRST_NAME = "Admin";
    public static final String ADMIN_LAST_NAME = "R";
    public static final String ADMIN_CC = "12345";
    public static final String ADMIN_PHONE = "5551234567";
    public static final String ADMIN_PASSWORD = "password123";
    public static final String ADMIN_CREATION_SUCCESS = "Administrador creado con éxito con email: %s y contraseña: %s";
    public static final String ADMIN_ALREADY_EXISTS = "El usuario administrador ya existe. No se creó un nuevo administrador.";
    public static final int ADMIN_BIRTH_YEAR = 1990;
    public static final int ADMIN_BIRTH_MONTH = 5;
    public static final int ADMIN_BIRTH_DAY = 15;
}
