package com.library.rental.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data @AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(
            description = "The API endpoint invoked by the client"
    )
    private  String endpointPath;

    @Schema(
            description = "Error code representing the error happened"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Detailed message explaining the error"
    )
    private  String message;

    @Schema(
            description = "Timestamp indicating when the error occurred"
    )
    private LocalDateTime errorTime;

}
