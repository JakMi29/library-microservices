package com.library.libraryBook.business.service;

import com.library.libraryBook.business.dao.BookDao;
import com.library.libraryBook.business.mapper.BookMapper;
import com.library.libraryBook.controller.dto.BookDto;
import com.library.libraryBook.controller.dto.RentBookRequestDto;
import com.library.libraryBook.domain.Book;
import com.library.libraryBook.domain.BookAlreadyExistException;
import com.library.libraryBook.domain.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class BookService {
    private final StreamBridge streamBridge;
    private BookDao bookDao;
    private BookMapper mapper;

    public BookDto createBook(BookDto bookDto) {
        Optional<Book> book = bookDao.getBookByName(bookDto.getName());
        if (book.isPresent()) {
            throw new BookAlreadyExistException(String.format("Book with name: %s already exist", bookDto.getName()));
        }
        return mapper.map(bookDao.createBook(mapper.map(bookDto)));
    }

    @Bean
    public Consumer<RentBookRequestDto> borrowBook() {
        return rentBookRequestDto -> {
            Book book = bookDao.getBook(rentBookRequestDto.bookId()).orElseThrow(
                    () -> new NotFoundException("Book does not exist"));
            if (book.getQuantity() == 0) {
                streamBridge.send("bookUnavailable-out-0", rentBookRequestDto);
                return;
            }
            bookDao.updateBook(book.withQuantity(book.getQuantity() - 1));
            streamBridge.send("bookAvailable-out-0", rentBookRequestDto);
        };
    }

    @Bean
    public Consumer<RentBookRequestDto> returnBook() {
        return rentBookRequestDto -> {
            Book book = bookDao.getBook(rentBookRequestDto.bookId()).orElseThrow(
                    () -> new NotFoundException("Book does not exist"));
            bookDao.updateBook(book.withQuantity(book.getQuantity() + 1));
        };
    }

    public void deleteBookByName(String name) {
        bookDao.deleteBookByName(name);
    }

    public BookDto getBookByName(String name) {
        Book book = bookDao.getBookByName(name).orElseThrow(
                () -> new NotFoundException(String.format("Book with name: %s do not exist", name)));
        return mapper.map(book);
    }

    public BookDto updateBook(BookDto bookDto) {
        Book book = bookDao.getBookByName(bookDto.getName()).orElseThrow(
                () -> new NotFoundException(String.format("User with phone number: %s do not exist", bookDto.getName())));
        return mapper.map(bookDao.updateBook(mapper.map(bookDto)));
    }

    public List<BookDto> getBooks() {
        return bookDao.getBooks().stream().map(mapper::map).toList();
    }
}
