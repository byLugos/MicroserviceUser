package user.domain.exceptions;

public class InvalidExistantUserException extends RuntimeException{
    public InvalidExistantUserException(String message) {
        super(message);
    }

}
