package com.arnab.irrigation.controller.exception;

import com.arnab.irrigation.domain.ErrorInfo;
import com.arnab.irrigation.exception.BadRequestException;
import com.arnab.irrigation.exception.ResourceNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErrorInfo handleResourceNotFoundException(HttpServletRequest request, final Exception exp) {
        return new ErrorInfo().setErrorMessage(exp.getMessage()).setCode(HttpStatus.NOT_FOUND.value());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    public ErrorInfo handleBadRequestException(HttpServletRequest request, final Exception exp) {
        return new ErrorInfo().setErrorMessage(exp.getMessage()).setCode(HttpStatus.BAD_REQUEST.value());
    }
    
    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<Object> handleMethodArgumentNotValid(ConstraintViolationException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            
        String validationMessage = ex.getMessage();
        
        ErrorMessage errorMessage = new ErrorMessage(validationMessage);
        
        
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(fieldError.getField() + ", " + fieldError.getDefaultMessage());
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            errors.add(objectError.getObjectName() + ", " +
                    "" + objectError.getDefaultMessage());
        }
        ErrorMessage errorMessage = new ErrorMessage(errors.toString());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    
}
