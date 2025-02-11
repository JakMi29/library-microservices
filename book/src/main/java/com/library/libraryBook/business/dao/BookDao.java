package com.library.libraryBook.business.dao;


import com.library.libraryBook.controller.dto.BookDto;
import com.library.libraryBook.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    Book createBook(Book book);

    Optional<Book> getBook(Integer bookId);
    Optional<Book> getBookByName(String name);

    void deleteBookByName(String name);

    Book updateBook(Book book);

    List<Book> getBooks();
}
