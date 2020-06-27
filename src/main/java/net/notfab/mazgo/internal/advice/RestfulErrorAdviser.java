package net.notfab.mazgo.internal.advice;

import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestfulErrorAdviser extends ResponseEntityExceptionHandler {

    private final HttpHeaders headers = new HttpHeaders();

    public RestfulErrorAdviser() {
        headers.add("Content-Type", "application/json");
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
        JSONObject object = new JSONObject();
        object.put("code", 400);
        object.put("message", ex.getMessage());
        return handleExceptionInternal(ex, object.toString(), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleInternalError(Exception ex, WebRequest request) {
        JSONObject object = new JSONObject();
        object.put("code", 500);
        object.put("message", "Internal server error");
        return handleExceptionInternal(ex, object.toString(), headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}
