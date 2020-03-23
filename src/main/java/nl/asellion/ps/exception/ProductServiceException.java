package nl.asellion.ps.exception;

/**
 * Product service exception class
 *
 * @author Alexander Kirillov
 */

public class ProductServiceException extends RuntimeException {

    public ProductServiceException(final String message) {
        super(message);
    }

}
