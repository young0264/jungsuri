package com.app.jungsuri.exception;

import lombok.extern.slf4j.Slf4j;
//import org.hibernate.tool.schema.spi.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
//@Controller
@ControllerAdvice
public class ExceptionControllerTest extends ExceptionControllerTest {
    private final Logger logger = LoggerFactory.getLogger(ExceptionControllerTest.class);




//    @Override
//    public void handleException(CommandAcceptanceException exception) {
//        System.out.println("exception = " + exception);
//    }


    @ExceptionHandler(org.thymeleaf.exceptions.TemplateInputException.class)
    public String handleTemplateInputException(org.thymeleaf.exceptions.TemplateInputException e) {
        // 에러 처리 로직
        log.info("TemplateInputException start");
        log.info(e.getLocalizedMessage());
        return "error/page"; // 에러 템플릿으로 리다이렉트
    }
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFoundPage(NoHandlerFoundException e) {
        log.info("no handle 404 error start");
        log.info("error message : {}", e.getMessage());
        return "/error/page";
    }
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException e){
        log.info("no handle 404 error start");
        log.info("error message : {}", e.getMessage());
        return "/error/page";
    }

    @ExceptionHandler(Exception.class)
    public String AllExceptionHandleError(Exception e, Model model) {
        log.info("Exception start");

//        logger.error("error message : " + e.getMessage());
        logger.error("error message : " + e.getLocalizedMessage());
        logger.error("error message : " + e.getStackTrace());
        model.addAttribute("status_code", "RuntimeException");
        model.addAttribute("error_message", e.getLocalizedMessage());

        return "/error/page";
    }

    @ExceptionHandler(RuntimeException.class)
    public String RuntimeHandleError(RuntimeException e, Model model) {

        model.addAttribute("status_code", "RuntimeException");
        model.addAttribute("error_message", e.getLocalizedMessage());

        return "/error/page";

////        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
////        Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
////        Object status = request.getAttribute("javax.servlet.error.status_code");
////        Object message = request.getAttribute("javax.servlet.error.message");
//        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE, RequestAttributes.SCOPE_REQUEST);
//
//        log.info("statusCode : {}", statusCode);
//        String description1 = request.getDescription(true);
//        String description2 = request.getDescription(false);
//        String error_message1 = (String) model.getAttribute("error_message");
//        String error_message2 = (String) session.getAttribute("error_message");
//        log.info("errorMessage1 : {}", error_message1);
//        log.info("errorMessage2 : {}", error_message2);
//        log.info("description1 : {}", description1);
//        log.info("description2 : {}", description2);
//
////        Integer statusCode = Integer.valueOf(status.toString());
//
////        if(400 <= statusCode && statusCode < 500) {
//////            if(statusCode == HttpStatus.NOT_FOUND.value()) {
////            model.addAttribute("status_code", statusCode);
////            model.addAttribute("error_message", "요청하신 페이지를 찾을 수 없습니다.");
////        } else if (500 <= statusCode && statusCode < 600) {
//////            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
////            model.addAttribute("status_code", statusCode);
////            model.addAttribute("error_message", "서버에 오류가 발생하였습니다.");
////        } else {
////            model.addAttribute("status_code", "RuntimeException");
////            model.addAttribute("error_message", "알 수 없는 에러가 발생하였습니다.");
////        }

    }


}
