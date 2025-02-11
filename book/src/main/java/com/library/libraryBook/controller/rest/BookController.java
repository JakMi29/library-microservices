package com.library.libraryBook.controller.rest;

import com.library.libraryBook.business.service.BookService;
import com.library.libraryBook.controller.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
public class BookController {

    private BookService bookService;
    private BookBuildInfoDto bookBuildInfoDto;

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
        BookDto bookDto = bookService.getBookByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(bookDto);
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
    @GetMapping("/books/fetch")
    public ResponseEntity<List<BookDto>> fetchBooks() {
        List<BookDto> books = bookService.getBooks();
        return ResponseEntity.status(HttpStatus.OK).body(books);
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
        BookDto book = bookService.updateBook(bookDto);
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

    @Operation(
            summary = "Get Build information",
            description = "Get Build information that is deployed into books microservice"
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
    public ResponseEntity<BookBuildInfoDto> getBuildInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(bookBuildInfoDto);
    }


}
