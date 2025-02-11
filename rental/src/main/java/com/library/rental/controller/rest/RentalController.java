package com.library.rental.controller.rest;

import com.library.rental.business.service.RentalService;
import com.library.rental.controller.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
public class RentalController {

    private RentalService rentalService;
    private RentalsBuildInfoDto rentalsBuildInfoDto;

    @Operation(
            summary = "Rent book REST API",
            description = "REST API to rent books"
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
    @PostMapping("/rent")
    public ResponseEntity<ResponseDto> rentBook(@RequestBody RentBookRequestDto rentBookRequestDto) {
        rentalService.borrowBook(rentBookRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Book rented"));
    }

    @Operation(
            summary = "Rent book REST API",
            description = "REST API to return book"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad Request",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PatchMapping("/return")
    public ResponseEntity<ResponseDto> returnBook(@RequestBody RentBookRequestDto rentBookRequestDto) {
        rentalService.returnBook(rentBookRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Book returned"));
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch user rentals details based on user id"
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
    })
    @GetMapping("/fetch/user")
    public ResponseEntity<List<RentalDto>> fetchUserRentals(@RequestParam Integer userId) {
        List<RentalDto> rentals=rentalService.getUserRentals((userId));
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @Operation(
            summary = "Fetch Card Details REST API",
            description = "REST API to fetch user rentals details based on user id"
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
    })
    @GetMapping("/fetch/book")
    public ResponseEntity<List<RentalDto>> fetchBookRentals(@RequestParam Integer bookId) {
        List<RentalDto> rentals=rentalService.getBookRentals((bookId));
        return ResponseEntity.status(HttpStatus.OK).body(rentals);
    }

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into rentals microservice"
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
    @GetMapping("/build-info")
    public ResponseEntity<RentalsBuildInfoDto> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(rentalsBuildInfoDto);
    }

}
