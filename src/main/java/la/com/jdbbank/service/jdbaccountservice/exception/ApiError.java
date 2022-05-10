package la.com.jdbbank.service.jdbaccountservice.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiError {
    private Boolean success;
    private HttpStatus status;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss",timezone = "Asia/Bangkok")
    private LocalDateTime timestamp;
    private long nanoTime;
    private String debugMessage;
    private String keyData;

    private ApiError() {
        success = false;
        timestamp = LocalDateTime.now();
        nanoTime = System.nanoTime();
    }

    public ApiError(HttpStatus status) {
        this();
        this.status = status;
    }

    ApiError(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }
    ApiError(HttpStatus status, String message, String keyData) {
        this();
        this.status = status;
        this.message = message;
        this.keyData=keyData;
    }

    ApiError(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

    ApiError(HttpStatus status, String message, Throwable ex, String keyData) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
        this.keyData = keyData;
    }
}
