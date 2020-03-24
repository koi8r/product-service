package nl.asellion.ps.exception;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

/**
 * Product service exception class
 *
 * @author Alexander Kirillov
 */

public class ProductServiceException extends RuntimeException {

    /**
     * Internal error codes
     */
    public static final String ERROR_PRODUCT_NOT_FOUND = "454";
    public static final String ERROR_GENERAL = "455";

    /**
     * A map of error messages
     */
    public static final Map<String, String> ERROR_MESSAGE_MAP = ImmutableMap.of(
            ERROR_PRODUCT_NOT_FOUND, "Product was not found in the database",
            ERROR_GENERAL, "General error occurred, try again later"
    );

    public ProductServiceException(final String code) {
        super(code);
    }

}
