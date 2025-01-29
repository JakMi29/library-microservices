package com.library.libraryBook.infrastructure.database.repository.jpa;

import com.library.libraryBook.infrastructure.database.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface BookJpaRepository extends JpaRepository<BookEntity,Integer> {
    Optional<BookEntity> findByName(String name);

    void deleteByName(String name);
}
