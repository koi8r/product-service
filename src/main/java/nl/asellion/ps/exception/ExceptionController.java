package nl.asellion.ps.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import nl.asellion.ps.dto.ErrorData;

/**
 * Handles exceptions in the application
 *
 * @author Alexander Kirillov
 */

@Slf4j
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity<ErrorData> handleGlobalException(Throwable t) {
        log.error("Unable to process request {}", t);
        return new ResponseEntity<>(SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> handleGlobalException(HttpServerErrorException e) {
        final ErrorData error = createAnError(e.getStatusCode().value(), e.getMessage());
        log.error("Service is unavailable: {}", e.getMessage());
        return new ResponseEntity<>(e.getStatusCode());
    }

    @ExceptionHandler(ProductServiceException.class)
    @ResponseBody
    public ResponseEntity<ErrorData> handleProductServiceException(ProductServiceException e) {
        final ErrorData error = createAnError(BAD_REQUEST.value(), e.getMessage());
        log.error("Service error: {}", e.getLocalizedMessage());
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    /**
     * Creates an error object
     *
     * @param statusCode HttpStatus code
     * @param errorMessage error message
     * @return error as a {@code ErrorData}
     */
    private ErrorData createAnError(int statusCode, String errorMessage) {
        return ErrorData.builder().statusCode(statusCode)
                .errorMessage(errorMessage).build();
    }
}