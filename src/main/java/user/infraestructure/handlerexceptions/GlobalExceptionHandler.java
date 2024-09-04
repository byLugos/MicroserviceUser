package user.infraestructure.handlerexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import user.domain.exceptions.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidNameException.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidNameException ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "NOMBRE_INVALIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidAgeException.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidAgeException ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "EDAD_INVALIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidDocumentException.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidDocumentException ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "DOCUMENTO_CC_INVALIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidEmailException ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "CORREO_INVALIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidExistantUserException.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidExistantUserException ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "USUARIO_INVALIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidPhoneException ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "NÚMERO_INVALIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorResponseCustom> handleInvalidNameException(InvalidRoleException ex, WebRequest request) {
        ErrorResponseCustom errorResponse = new ErrorResponseCustom(ex.getMessage(), "ROL_INVALIDO");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}