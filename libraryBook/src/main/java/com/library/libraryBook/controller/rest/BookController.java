package com.library.libraryBook.controller.rest;

import com.library.libraryBook.business.service.BookService;
import com.library.libraryBook.controller.dto.BookDto;
import com.library.libraryBook.controller.dto.ErrorResponseDto;
import com.library.libraryBook.controller.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class BookController {

    private BookService bookService;

    @Operation(
            summary = "Create book REST API",
            description = "REST API to create books"
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
    public ResponseEntity<ResponseDto> createBook(@Valid @RequestBody BookDto book) {
        bookService.createBook(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Successfully create book"));
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
    public ResponseEntity<BookDto> fetchBook(
            @RequestParam
            String name) {
        BookDto userDto = bookService.getUserByPhoneNumber(name);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @Operation(
            summary = "Lent book REST API",
            description = "REST API to lent book"
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
    @PatchMapping("/lent")
    public ResponseEntity<ResponseDto> lentBook(String name) {
        BookDto book = bookService.lentBook(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully lent book"));

    }

    @Operation(
            summary = "Return book REST API",
            description = "REST API to return book"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "HTTP Status Bad request"
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
    @PatchMapping("/return")
    public ResponseEntity<ResponseDto> returnBook(String name) {
        BookDto book = bookService.returnBook(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully return book"));

    }

    @Operation(
            summary = "Update Book REST API",
            description = "REST API to update book"
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
    public ResponseEntity<ResponseDto> updateBook(@Valid @RequestBody BookDto bookDto) {
        BookDto book = bookService.updateUser(bookDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully update book"));

    }


    @Operation(
            summary = "Delete book REST API",
            description = "REST API to delete book by name"
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
    public ResponseEntity<ResponseDto> deleteBook(
            @RequestParam
            String name) {
        bookService.deleteBookByName(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200", "Successfully delete book"));

    }


}
