package nl.asellion.ps.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

/**
 * Error Data Transfer Object for error response
 *
 * @author Alexander Kirillov
 */

@Value
@Builder
public class ErrorDataDto {

    @JsonProperty("status")
    int status;

    @JsonProperty("timestamp")
    LocalDateTime timestamp;

    @JsonProperty("message")
    String message;

}