package nl.asellion.ps.configuration.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.api.dto.ErrorDataDto;

/**
 * Handles exceptions in the application
 *
 * @author Alexander Kirillov
 */

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ProductServiceExceptionHandler {

    /**
     * Handles Throwable
     *
     * @param t Throwable
     * @return A ResponseEntity containing a ErrorDataDto objects
     */

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ErrorDataDto> handleHttpServerErrorException(Throwable t) {
        log.error("Internal server error: {}", t.getMessage());
        final ErrorDataDto error = createAnError(BAD_REQUEST.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(error, BAD_REQUEST);

    }

    /**
     * Handles all Exceptions not addressed by more specific <code>@ExceptionHandler</code> methods
     *
     * @param ex An exception
     * @return ResponseEntity containing a ErrorDataDto objects
     */

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorDataDto> handleException(Exception ex) {
        final String message = "Something went wrong, try again later...";
        final ErrorDataDto error = createAnError(BAD_REQUEST.value(), message);
        log.error("Error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    /**
     * Handles HttpServerErrorException
     *
     * @param ex An exception
     * @return ResponseEntity containing a ErrorDataDto objects
     */

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorDataDto> handleHttpServerErrorException(HttpServerErrorException ex) {
        final String code = String.valueOf(ex.getStatusCode().value());
        final String message = ProductServiceException.ERROR_MESSAGE_MAP.get(code);
        final ErrorDataDto error = createAnError(BAD_REQUEST.value(), message);
        log.error("Service is unavailable: {}", ex.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    /**
     * Handles ConstraintViolationException
     *
     * @param ex An exception
     * @return A ResponseEntity containing a ErrorDataDto objects
     */

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDataDto> handleConstraintViolationException(DataIntegrityViolationException ex) {
        final String message = ProductServiceException.ERROR_MESSAGE_MAP.get(ProductServiceException.ERROR_UNIQUE_KEY_VIOLATION);
        final ErrorDataDto error = createAnError(BAD_REQUEST.value(), message);
        log.error("Service error: {}", ex.getMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }


    /**
     * Handles ProductServiceException
     *
     * @param ex An exception message as internal {@code ProductServiceException} status code
     * @return A ResponseEntity containing a ErrorDataDto objects
     */

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorDataDto> handleProductServiceException(ProductServiceException ex) {
        final String code = ex.getMessage();
        final String message = ProductServiceException.ERROR_MESSAGE_MAP.get(code);
        final ErrorDataDto error = createAnError(BAD_REQUEST.value(), message);
        log.error("Service error: {}", message);
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    /**
     * Creates an error response object
     *
     * @param code HttpStatus code
     * @param message An Error message
     * @return An Error object as a {@code ErrorDataDto}
     */

    private ErrorDataDto createAnError(int code, String message) {
        return ErrorDataDto.builder().status(code)
                .message(message).timestamp(LocalDateTime.now()).build();
    }
}