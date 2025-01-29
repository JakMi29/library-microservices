package com.library.libraryBook.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

@Value
@With
@Builder
@EqualsAndHashCode
public class Book {
    Integer id;
    String name;
    String author;
    Integer publicationDate;
    Integer quantity;
}
