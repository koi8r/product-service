package nl.asellion.ps.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.dto.ErrorDataDto;

/**
 * Handles exceptions in the application
 *
 * @author Alexander Kirillov
 */

@Slf4j
@ControllerAdvice
public class ProductServiceExceptionHandler extends ResponseEntityExceptionHandler {

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
        final ErrorDataDto error = createAnError(INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase());
        return new ResponseEntity<>(error, BAD_REQUEST);

    }

    /**
     * Handles HttpServerErrorException
     *
     * @param ex An exception message
     * @return ResponseEntity containing a ErrorDataDto objects
     */

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorDataDto> handleHttpServerErrorException(HttpServerErrorException ex) {
        final String code = String.valueOf(ex.getStatusCode().value());
        final String message = ProductServiceException.ERROR_MESSAGE_MAP.get(code);
        final ErrorDataDto error = createAnError(INTERNAL_SERVER_ERROR.value(), message);
        log.error("Service is unavailable: {}", message);
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    /**
     * Handles ProductServiceException
     *
     * @param ex An exception message as internal {@code ProductServiceException} status code
     * @return A ResponseEntity containing a ErrorDataDto objects
     */

    @ExceptionHandler(ProductServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorDataDto> handleProductServiceException(ProductServiceException ex) {
        final String code = ex.getMessage();
        final String message = ProductServiceException.ERROR_MESSAGE_MAP.get(code);
        final ErrorDataDto error = createAnError(NOT_FOUND.value(), message);
        log.error("Service error: {}", message);
        return new ResponseEntity<>(error, NOT_FOUND);
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