package com.library.libraryBook.infrastructure.database.repository;

import com.library.libraryBook.business.dao.BookDao;
import com.library.libraryBook.domain.Book;
import com.library.libraryBook.infrastructure.database.repository.jpa.BookJpaRepository;
import com.library.libraryBook.infrastructure.database.repository.mapper.BookEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class BookRepository implements BookDao {

    private final BookJpaRepository repository;
    private final BookEntityMapper mapper;

    @Override
    public Book createBook(Book book) {
        return mapper.map(repository.save(mapper.map(book)));
    }

    @Override
    public Optional<Book> getBookByName(String name) {
        return repository.findByName(name).map(mapper::map);
    }

    @Override
    public void deleteBookByName(String name) {
        repository.deleteByName(name);
    }

    @Override
    public Book updateBook(Book book) {
        return mapper.map(repository.save(mapper.map(book)));
    }
}
