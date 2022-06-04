package com.flask.app.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@EnableWebMvc
@ControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleException(HttpServletRequest request, ResourceNotFoundException exp) {
        log.error("Resource Not Found{}", exp);
        ErrorResponse response = getErrorResponse(exp.getCode(), exp.getFeature(), exp.getReason());
        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DatabaseException.class)
    @ResponseBody
    public ErrorResponse handleDatabaseException(HttpServletRequest request, DatabaseException exp) {
        log.error("Database Exception {}", exp);
        ErrorResponse response = getErrorResponse(exp.getCode(), exp.getFeature(), exp.getReason());
        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RemoteApiException.class)
    @ResponseBody
    public ErrorResponse handleRemoteApiException(HttpServletRequest request, RemoteApiException exp) {
        log.error("Remote Api Exception {}", exp);
        ErrorResponse response = getErrorResponse(exp.getCode(), exp.getFeature(), exp.getReason());
        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse methodArgumentExceptionHandler(HttpServletRequest request, MethodArgumentNotValidException exp) {
        log.error("Generic Exception ", exp);
        ErrorResponse response = getErrorResponse(ErrorCodes.CODE.METHOD_ARG_NOT_VALID,
                ErrorCodes.Feature.UNKNOWN,
                getErrorMessage(exp.getBindingResult()));
        return response;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorResponse genericExceptionHandler(HttpServletRequest request, Exception exp) {
        log.error("Generic Exception ", exp);
        ErrorResponse response = getErrorResponse(ErrorCodes.CODE.GENERIC_ERROR,
                ErrorCodes.Feature.UNKNOWN,
                ErrorCodes.REASON_MAP.get(ErrorCodes.CODE.GENERIC_ERROR));
        return response;
    }

    public static String getErrorMessage(BindingResult bindingResult){
        StringBuilder message = new StringBuilder();

        List<ObjectError> objectErrors = bindingResult.getGlobalErrors();
        for (ObjectError objectError : objectErrors) {
            message.append(objectError.getObjectName())
                    .append(" : ")
                    .append(" [")
                    .append(objectError.getDefaultMessage())
                    .append("] ");
        }

        return message.toString();
    }


    private ErrorResponse getErrorResponse(String code, String feature, String reason) {
        ErrorResponse response = new ErrorResponse();
        response.setCode(code);
        response.setFeature(feature);
        response.setMessage(reason);
        return response;
    }
}