package com.library.users.controller.rest;

import com.library.users.business.service.UserService;
import com.library.users.controller.dto.ErrorResponseDto;
import com.library.users.controller.dto.ResponseDto;
import com.library.users.controller.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author JakMi29
 */

@Tag(
        name = "REST APIs for library",
        description = "REST APIs in library to CREATE, UPDATE, FETCH AND DELETE users"
)
@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
@Validated
public class UsersController {

    private UserService userService;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create users"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Successfully create user"));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch users"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetch")
    public ResponseEntity<UserDto> fetchAccountDetails(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 9 digits")
            String phoneNumber) {
        UserDto userDto = userService.getUserByPhoneNumber(phoneNumber);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody UserDto userDto) {
        UserDto user = userService.updateUser(userDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully update user"));

    }

    @Operation(
            summary = "Delete user REST API",
            description = "REST API to delete user by phone number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteUser(
            @RequestParam
            @Pattern(regexp = "(^$|[0-9]{9})", message = "Mobile number must be 9 digits")
            String phoneNumber) {
        userService.deleteUserByPhoneNumber(phoneNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully delete user"));

    }


}
