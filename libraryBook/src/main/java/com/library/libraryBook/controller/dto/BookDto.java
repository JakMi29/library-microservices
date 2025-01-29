package com.library.libraryBook.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(
        name = "Book",
        description = "Schema to hold book information"
)
public class BookDto {
    @Schema(
            description = "Book name",
            example = "Hobbit"
    )
    private String name;
    @Schema(
            description = "Author",
            example = "J.R.R. Tolkien"
    )
    private String author;
    @Schema(
            description = "User phone number",
            example ="143654767"

    )
    @Min(value = 1000, message = "Publication date must be a 4-digit year")
    @Max(value = 9999, message = "Publication date must be a 4-digit year")
    private Integer publicationDate;
    @Schema(
            description = "Book quantity"
    )
    private Integer quantity;


}
