package nl.asellion.ps.configuration.exception;

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
    public static final String ERROR_NO_CHANGES_APPLIED = "455";
    public static final String ERROR_PRODUCT_ALREADY_EXISTS = "456";
    public static final String ERROR_UNIQUE_KEY_VIOLATION = "457";

    /**
     * A map of error messages
     */
    public static final Map<String, String> ERROR_MESSAGE_MAP = ImmutableMap.of(
            ERROR_PRODUCT_NOT_FOUND, "Product was not found in the database",
            ERROR_NO_CHANGES_APPLIED, "Changes were not applied and product was not updated/created",
            ERROR_PRODUCT_ALREADY_EXISTS, "Product already exists, no new product created",
            ERROR_UNIQUE_KEY_VIOLATION, "Product can't be created. The name already exists"
    );

    public ProductServiceException(final String code) {
        super(code);
    }

}
