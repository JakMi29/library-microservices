package com.library.libraryBook.infrastructure.database.repository.mapper;

import com.library.libraryBook.domain.Book;
import com.library.libraryBook.infrastructure.database.entity.BookEntity;
import org.springframework.stereotype.Component;

@Component
public class BookEntityMapper {
    public Book map(BookEntity entity) {
        return Book.builder()
                .id(entity.getId())
                .name(entity.getName())
                .author(entity.getAuthor())
                .quantity(entity.getQuantity())
                .publicationDate(entity.getPublicationDate())
                .build();
    }

    public BookEntity map(Book book) {
        return BookEntity.builder()
                .id(book.getId())
                .name(book.getName())
                .author(book.getAuthor())
                .quantity(book.getQuantity())
                .publicationDate(book.getPublicationDate())
                .build();
    }
}
