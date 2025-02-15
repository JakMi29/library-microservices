package com.library.users.controller.dto;

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
            description = "Book return dater"
    )
    private OffsetDateTime returnDate;
    @Schema(
            description = "Book rental date"
    )
    private OffsetDateTime rentalDate;
    @Schema(
            description = "Rental fee"
    )
    private BigDecimal fee;
}
