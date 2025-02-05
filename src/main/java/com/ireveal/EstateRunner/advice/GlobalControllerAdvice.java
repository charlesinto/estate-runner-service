package com.ireveal.EstateRunner.advice;

import com.ireveal.EstateRunner.entity.EstateRunnerResponse;
import com.ireveal.EstateRunner.util.ResponseUtilService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.AuthorizationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CHARLES ONUORAH
 * @project com.ireveal.EstateRunner.advice
 * @Date 03/02/2025
 */

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalControllerAdvice {
    protected final ResponseUtilService responseUtilService;

    protected static final String ACCESS_DENIED = "You are not authorised to perform this action. Kindly contact administrator to review your access.";

    @Value("${server.max-http-header-size:52428800}")
    private Long maxHeaderSize;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public EstateRunnerResponse handleGeneralException(Exception e, HttpServletRequest httpServletRequest) {
        log.error("Unknown server error", e);

        return responseUtilService.buildErrorResponse("There was an error processing the request, Please try again later...", httpServletRequest);
    }

    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public EstateRunnerResponse handleAuthorizationException(Exception e, HttpServletRequest httpServletRequest){
        log.error("Authourization exception: {}", e);
        return responseUtilService.buildErrorResponse(e.getMessage(), httpServletRequest);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public EstateRunnerResponse handleAuthenticationException(Exception e, HttpServletRequest httpServletRequest){
        log.error("Unauthorized error: {}", e);
        return responseUtilService.buildErrorResponse(e.getMessage(), httpServletRequest);
    }

    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public EstateRunnerResponse handleReflectionException(UndeclaredThrowableException e, HttpServletRequest httpServletRequest) {
        log.error("Proxy checked exception", e);
        Throwable cause = e.getUndeclaredThrowable();

        String errorMessage = cause != null ? cause.getMessage() : e.getMessage();

        return responseUtilService.buildErrorResponse(errorMessage, httpServletRequest);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EstateRunnerResponse handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest httpServletRequest) {
        log.error("Illegal argument exception", e);
        return responseUtilService.buildErrorResponse("Please ensure that the details supplied in your request are appropriate.", httpServletRequest);
    }


    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public EstateRunnerResponse handleMissingHeaderException(MissingRequestHeaderException e, HttpServletRequest httpServletRequest) {
        log.error("access denied", e);

        return responseUtilService.buildErrorResponse("Unauthorized, supply authorization header", httpServletRequest);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EstateRunnerResponse handleMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest httpServletRequest) {
        log.error("Malformed request", e);

        return responseUtilService.buildErrorResponse("The request could not be completed due to malformed syntax. Kindly crosscheck and try again.", httpServletRequest);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EstateRunnerResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest httpServletRequest) {
        log.error("Invalid request", e);
        if (e.getBindingResult() == null || e.getBindingResult().getFieldError() == null) {
            return responseUtilService.buildErrorResponse("invalid arguments", httpServletRequest);
        }

        FieldError error = e.getBindingResult().getFieldError();
        String field = error.getField() == null ? "" : error.getField();
        log.info("Validation error occurred. Field {} {}", field, error.getDefaultMessage());
        return responseUtilService.buildErrorResponse(error.getDefaultMessage(), httpServletRequest);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public EstateRunnerResponse handleRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest httpServletRequest) {
        return responseUtilService.buildErrorResponse(e.getMessage(), e, httpServletRequest);
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public EstateRunnerResponse handleAccessDeniedException(Exception e, HttpServletRequest httpServletRequest) {
        return responseUtilService.buildErrorResponse(ACCESS_DENIED, e, httpServletRequest);
    }


    @ExceptionHandler(BindException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public EstateRunnerResponse handleBindException(BindException ex, HttpServletRequest httpServletRequest) {
        List<ObjectError> objectErrors = ex.getAllErrors();

        List<String> violationMessages = new ArrayList<>();

        for (ObjectError objectError : objectErrors) {

            String defaultMessage = objectError.getDefaultMessage();

            FieldError fieldError = (FieldError) objectError;
            String customErrorMessage = String.format("Invalid value '%s' for field '%s'", fieldError.getRejectedValue(), ((FieldError) objectError).getField());

            if (StringUtils.isEmpty(defaultMessage)) {
                violationMessages.add(customErrorMessage);
                continue;
            }

            violationMessages.add(defaultMessage);
        }

        return responseUtilService.buildErrorResponse(String.join(" | ", violationMessages), httpServletRequest);
    }
}
