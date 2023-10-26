package com.app.jungsuri.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionController  {

    @ExceptionHandler(Exception.class)
    public String AllExceptionHandleError(Exception e, Model model) {

        log.info("status code BadRequest ::: " + HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("status_code", HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("exception_type", "Exception" );
        model.addAttribute("error_message", extractErrorMessages((MethodArgumentNotValidException) e));

        return "/error/page";
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public String RuntimeHandleError(HttpClientErrorException.BadRequest e, Model model) {

        log.info("status code BadRequest ::: " + HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("status_code",HttpStatus.INTERNAL_SERVER_ERROR );
        model.addAttribute("exception_type", "BadRequest" );
        model.addAttribute("error_message", e.getLocalizedMessage());

        return "/error/page";

    }

    @ExceptionHandler(InternalError.class)
    public String RuntimeHandleError(InternalError e, Model model) {

        log.info("status code InternalError ::: " + HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("status_code",HttpStatus.INTERNAL_SERVER_ERROR );
        model.addAttribute("exception_type", "InternalError" );
        model.addAttribute("error_message", e.getLocalizedMessage());

        return "/error/page";

    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public String RuntimeHandleError(HttpServerErrorException.InternalServerError e, Model model) {

        log.info("status code InternalServerError ::: " + HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("status_code",HttpStatus.INTERNAL_SERVER_ERROR );
        model.addAttribute("exception_type", "InternalServerError" );
        model.addAttribute("error_message", e.getLocalizedMessage());

        return "/error/page";

    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeHandleError(RuntimeException e, Model model) {

        log.info("status code RuntimeException ::: " + HttpStatus.INTERNAL_SERVER_ERROR);
        model.addAttribute("status_code",HttpStatus.INTERNAL_SERVER_ERROR );
        model.addAttribute("exception_type", "RuntimeException" );
        model.addAttribute("error_message", e.getLocalizedMessage());

        return "/error/page";

    }

    private List<String> extractErrorMessages(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .map(String::new)
                .collect(Collectors.toList());
    }

}
