package com.library.libraryBook.business.service;

import com.library.libraryBook.business.dao.BookDao;
import com.library.libraryBook.business.mapper.BookMapper;
import com.library.libraryBook.controller.dto.BookDto;
import com.library.libraryBook.domain.Book;
import com.library.libraryBook.domain.BookAlreadyExistException;
import com.library.libraryBook.domain.BookOutOfStockException;
import com.library.libraryBook.domain.NotFoundException;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private BookDao bookDao;
    private BookMapper mapper;

    @Transactional
    public BookDto createBook(BookDto bookDto) {
        Optional<Book> book = bookDao.getBookByName(bookDto.getName());
        if (book.isPresent()) {
            throw new BookAlreadyExistException(String.format("Book with name: %s already exist", bookDto.getName()));
        }
        return mapper.map(bookDao.createBook(mapper.map(bookDto)));
    }

    @Transactional
    public BookDto lentBook(String name) {
        Book book = bookDao.getBookByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Book with name: %s do not exist", name)));
        if (book.getQuantity() == 0) {
            throw new BookOutOfStockException(String.format("Book with name: %s is currently out of stock.", name));
        }
        return mapper.map(bookDao.updateBook(book.withQuantity(book.getQuantity() - 1)));
    }

    @Transactional
    public BookDto returnBook(String name) {
        Book book = bookDao.getBookByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Book with name: %s do not exist", name)));
        return mapper.map(bookDao.updateBook(book.withQuantity(book.getQuantity() + 1)));
    }

    @Transactional
    public void deleteBookByName(String name) {
        bookDao.deleteBookByName(name);
    }

    @Transactional
    public BookDto getUserByPhoneNumber(String name) {
        Book book = bookDao.getBookByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Book with name: %s do not exist", name)));
        return mapper.map(book);
    }

    @Transactional
    public BookDto updateUser(BookDto bookDto) {
        Book user = bookDao.getBookByName(bookDto.getName()).orElseThrow(
                () -> new NotFoundException(String.format("User with phone number: %s do not exist", bookDto.getName())));
        return mapper.map(bookDao.updateBook(mapper.map(bookDto)));
    }
}
