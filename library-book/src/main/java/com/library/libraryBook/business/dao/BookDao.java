package com.library.libraryBook.business.dao;


import com.library.libraryBook.domain.Book;

import java.util.Optional;

public interface BookDao {

    Book createBook(Book book);

    Optional<Book> getBookByName(String name);

    void deleteBookByName(String name);

    Book updateBook(Book book);
}
