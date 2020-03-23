package nl.asellion.ps.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

/**
 * @author Alexander Kirillov
 */

@Value
@Builder
public class ErrorData {

    @JsonProperty("statusCode")
    @NonNull
    private int statusCode;

    @JsonProperty("errorMessage")
    private String errorMessage;

}
