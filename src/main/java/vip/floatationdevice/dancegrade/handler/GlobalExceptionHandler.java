package vip.floatationdevice.dancegrade.handler;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import vip.floatationdevice.dancegrade.component.CommonUtil;
import vip.floatationdevice.dancegrade.data.http.CommonMappedResult;
import vip.floatationdevice.dancegrade.exception.DataNotFoundException;

@ControllerAdvice
@Order(value = PriorityOrdered.HIGHEST_PRECEDENCE)
@Slf4j
public class GlobalExceptionHandler
{
    @Autowired
    CommonUtil util;

    // exceptions thrown when the requested resource was not found in the database
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ResponseEntity<?> handleDataNotFoundException(DataNotFoundException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        HttpStatus.NOT_FOUND.value(),
                        e.getMessage()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    // exceptions thrown when a DELETE request was sent to a URL that only supports GET method, or something like this
    // e.g. DELETE /api/dataCount
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        HttpStatus.METHOD_NOT_ALLOWED.value(),
                        e.getMessage()
                ),
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    // exceptions thrown by controllers when a required query parameter was not present
    // e.g. GET /api/data
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        40001,
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    // exceptions thrown by controllers when a required query parameter has invalid type
    // e.g. GET /api/data?page=A
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        40002,
                        e.getErrorCode() + "(" + e.getName() + "): " + e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    // exceptions thrown when an POST request was not using the "content-type: application/json" header
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<?> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        40003,
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    // exceptions thrown by deserializer when processing the HTTP request body
    // e.g. json syntax errors, json filed type mismatch
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        40004,
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    // exceptions thrown by @Validated annotated DanceData object when a field's value validation fails
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        40005,
                        e.getFieldError().getDefaultMessage(),
                        e.toString()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    // exceptions thrown by validator annotated methods in @Validated services
    // e.g. GET /api/data?page=-1
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e)
    {
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        40006,
                        e.getMessage()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handleOtherExceptions(Exception e)
    {
        log.error("UNHANDLED EXCEPTION: " + e +
                "\n==================== BEGIN STACK TRACE ====================\n" +
                util.getStackTraceAsString(e) +
                "\n===================== END STACK TRACE ====================="
        );
        return new ResponseEntity<>(
                new CommonMappedResult<>(
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        "Internal server error: " + e,
                        util.getStackTraceAsString(e)
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
