package nl.asellion.ps.api.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Value;

/**
 * Error Data Transfer Object for error responses
 *
 * @author Alexander Kirillov
 */

@Value
@Builder
public class ErrorDataDto {

    private static final String ERROR_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    @JsonProperty("status")
    int status;

    @JsonFormat(pattern = ERROR_DATE_TIME_PATTERN)
    @JsonProperty("timestamp")
    LocalDateTime timestamp;

    @JsonProperty("message")
    String message;

}