package la.com.jdbbank.service.jdbaccountservice.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseMessageModel {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "Asia/Bangkok")
    private LocalDateTime timestamp;
    private Boolean success;

    private String message;
    private Object data;

    public ResponseMessageModel(Boolean success, String resultmsg, Object data) {
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = resultmsg;
        this.data = data;
    }
}