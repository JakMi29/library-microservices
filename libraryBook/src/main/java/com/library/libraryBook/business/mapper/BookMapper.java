package com.library.libraryBook.business.mapper;

import com.library.libraryBook.controller.dto.BookDto;
import com.library.libraryBook.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {
    public Book map(BookDto bookDto) {
        return Book.builder()
                .name(bookDto.getName())
                .author(bookDto.getAuthor())
                .quantity(bookDto.getQuantity())
                .publicationDate(bookDto.getPublicationDate())
                .build();
    }

    public BookDto map(Book book) {
        return BookDto.builder()
                .name(book.getName())
                .author(book.getAuthor())
                .quantity(book.getQuantity())
                .publicationDate(book.getPublicationDate())
                .build();
    }
}
