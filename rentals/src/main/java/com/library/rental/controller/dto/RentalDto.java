package com.library.rental.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
@Schema(
        name = "Rental",
        description = "Schema to hold rental information"
)
public class RentalDto {
    @Schema(
            description = "Rental id"
    )
    private Integer id;
    @Schema(
            description = "User id",
            example = "1"
    )
    private Integer userId;
    @Schema(
            description = "Book id",
            example = "1"
    )
    private Integer bookId;
    @Schema(
            description = "User phone number",
            example ="143654767"

    )
    @Min(value = 1000, message = "Publication date must be a 4-digit year")
    @Max(value = 9999, message = "Publication date must be a 4-digit year")
    private OffsetDateTime returnDate;
    @Min(value = 1000, message = "Publication date must be a 4-digit year")
    @Max(value = 9999, message = "Publication date must be a 4-digit year")
    private OffsetDateTime rentalDate;
    @Schema(
            description = "Book quantity"
    )
    private BigDecimal fee;


}
