package com.epam.hcomtest.datastore.exceptionhandler;

import com.epam.hcomtest.datastore.exception.InvalidDataStoreFormatException;
import com.epam.hcomtest.datastore.exception.DataStoreNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class DataStoreExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String ERROR_VIEW = "error";

    @ExceptionHandler(value = InvalidDataStoreFormatException.class)
    public ModelAndView handleInvalidDataStoreFormatException(RuntimeException e) {
        return getModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(value = DataStoreNotFoundException.class)
    public ModelAndView handleDataStoreNotFoundException(RuntimeException e) {
        return getModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleException(RuntimeException e){
        return getModelAndView(HttpStatus.INTERNAL_SERVER_ERROR, e);
    }

    private ModelAndView getModelAndView(HttpStatus httpStatus, RuntimeException e) {
        ModelMap modelMap = new ModelMap();

        modelMap.put("statusCode", httpStatus.value());
        modelMap.put("statusReasonPhrase", httpStatus.getReasonPhrase());
        modelMap.put("errorMessage", e.getMessage());
        modelMap.put("stacktrace", getStackTraceStirng(e));

        return new ModelAndView(ERROR_VIEW, modelMap);
    }

    private String getStackTraceStirng(RuntimeException e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);

        e.printStackTrace(printWriter);

        return stringWriter.toString();
    }

}
