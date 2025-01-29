package com.library.users.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(
        name = "User",
        description = "Schema to hold user information"
)
public class UserDto {
    @Schema(
            description = "User name",
            example = "Marek"
    )
    private String name;
    @Schema(
            description = "User email",
            example = "email@gmail.com"
    )
    @Email(message = "Invalid email")
    private String email;
    @Schema(
            description = "User phone number",
            example ="143654767"

    )
    @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 9 digits")
    private String phoneNumber;
    @Schema(
            description = "User surname"
    )
    private String surname;
    @Schema(
            description = "User phone"
    )
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
