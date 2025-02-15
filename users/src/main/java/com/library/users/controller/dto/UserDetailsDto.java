package com.library.users.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Schema(
        name = "User details",
        description = "Schema to hold user details information"
)
public class UserDetailsDto {
    @Schema(
            description = "User"
    )
    private UserDto user;
    @Schema(
            description = "User rentals"
    )
    private List<RentalDto> rentals;
}
