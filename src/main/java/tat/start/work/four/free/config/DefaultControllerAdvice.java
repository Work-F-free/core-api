package tat.start.work.four.free.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import tat.start.work.four.free.controller.AuthController;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    @ExceptionHandler(UnsupportedOperationException.class)
    public void handleUnimplemented() {
        // Nothing to do
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthController.HttpUnauthorizedException.class)
    public void handleUnauthorized() {
        // Nothing to do
    }
}
