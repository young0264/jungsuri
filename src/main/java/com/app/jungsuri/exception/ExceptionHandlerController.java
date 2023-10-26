package com.app.jungsuri.exception;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;


@Slf4j
@Controller
public class ExceptionHandlerController implements ErrorController {

    @GetMapping("/error")
    public void handleError(MethodArgumentNotValidException ex) {
    }

}
