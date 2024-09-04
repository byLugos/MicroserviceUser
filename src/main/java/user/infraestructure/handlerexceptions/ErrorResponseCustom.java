package user.infraestructure.handlerexceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseCustom {
    private String message;
    private String errorCode;
    private LocalDateTime timestamp;

    public ErrorResponseCustom(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }
}