package la.com.jdbbank.service.jdbaccountservice.exception;


import la.com.jdbbank.service.jdbaccountservice.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    private LogService logService;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, body.toString()));

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                 final HttpHeaders headers,final HttpStatus status,final WebRequest request) {
        String error = "Invalid input format";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error));
    }


    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<Object> handleInvalidInputException(InvalidInputException ex, WebRequest request) {

        // user activity log error
        logService.logResErr(ex.userInfo, Integer.toString(HttpStatus.BAD_REQUEST.value()), ex.message, ex.mgsDesc);

        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex.message.toUpperCase(), ex.userInfo.getKeyData()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(UnauthorizedException ex, WebRequest request) {

        // user activity log error
        logService.logResErr(ex.userInfo, Integer.toString(HttpStatus.UNAUTHORIZED.value()), ex.message, ex.mgsDesc);

        return buildResponseEntity(new ApiError(HttpStatus.UNAUTHORIZED, ex.message.toUpperCase(), ex.userInfo.getKeyData()));
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailableException(ServiceUnavailableException ex, WebRequest request) {

        // user activity log error
        logService.logResErr(ex.userInfo, Integer.toString(HttpStatus.SERVICE_UNAVAILABLE.value()), "SERVICE UNAVAILABLE, " + ex.message, ex.mgsDesc);

        return buildResponseEntity(new ApiError(HttpStatus.SERVICE_UNAVAILABLE, "SERVICE UNAVAILABLE,  ", ex.userInfo.getKeyData()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, WebRequest request) {
        //System.out.println(new ObjectMapper ().writeValueAsString(ex));
        // user activity log error
        logService.logResErr(ex.userInfo, Integer.toString(HttpStatus.NOT_FOUND.value()), ex.message, ex.mgsDesc);
        //return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.message, ex.userInfo.getKeyData()));
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.message, ex.userInfo.getKeyData()));
    }

//	@ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
//		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error", ex);
//		return buildResponseEntity(apiError);
//    }


    private ResponseEntity<Object> buildResponseEntity(ApiError e, String... additionalMessages) {

        String additionalMessage = "";
        if (additionalMessages.length > 0)
            additionalMessage = ", " + additionalMessages[0];

        return new ResponseEntity<>(e, e.getStatus());
    }

}