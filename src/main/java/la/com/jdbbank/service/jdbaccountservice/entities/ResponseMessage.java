package la.com.jdbbank.service.jdbaccountservice.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseMessage {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Bangkok")
    private LocalDateTime timestamp;
    private Boolean success;
    private int result;
    private String message;
    private String keyData;
    private Object data;

    public ResponseMessage(Boolean success, int result, String message, String keyData, Object data) {
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.result = result;
        this.message = message;
        this.keyData = keyData;
        this.data = data;
    }
}
