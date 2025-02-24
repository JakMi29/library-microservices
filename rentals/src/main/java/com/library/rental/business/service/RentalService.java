package com.library.rental.business.service;

import com.library.rental.business.dao.RentalDao;
import com.library.rental.business.mapper.RentalMapper;
import com.library.rental.controller.dto.RentBookRequestDto;
import com.library.rental.controller.dto.RentalDto;
import com.library.rental.domain.NotFoundException;
import com.library.rental.domain.Rental;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Service
@AllArgsConstructor
public class RentalService {
    private final StreamBridge streamBridge;
    private RentalDao rentalDao;
    private RentalMapper mapper;

    public RentBookRequestDto borrowBook(RentBookRequestDto rentBookDto) {
        streamBridge.send("borrowBook-out-0", rentBookDto);
        return rentBookDto;
    }

    @Bean
    public Consumer<RentBookRequestDto> bookAvailable() {
        return rentBookRequestDto -> {
            Rental rental = Rental.builder()
                    .userId(rentBookRequestDto.userId())
                    .rentalDate(OffsetDateTime.now())
                    .bookId(rentBookRequestDto.bookId())
                    .fee(BigDecimal.ZERO)
                    .build();
            rentalDao.createRental(rental);
            streamBridge.send(
                    "sendMessage-out-0",
                    String.format(
                            "User with id %s successfully rent book with id %s",
                            rentBookRequestDto.userId(),
                            rentBookRequestDto.bookId())
            );
        };
    }

    @Bean
    public Consumer<RentBookRequestDto> bookUnavailable() {
        return rentBookRequestDto -> {
            streamBridge.send(
                    "sendMessage-out-0",
                    String.format("Book with id: %s is unavailable", rentBookRequestDto.bookId())
            );
        };
    }

    public void returnBook(RentBookRequestDto rentBookRequestDto) {
        Rental rental = rentalDao.getByUserAndBook(rentBookRequestDto.userId(), rentBookRequestDto.bookId()).orElseThrow(
                () -> new NotFoundException(("Rental for this book does nor exist")));
        rentalDao.updateRental(rental.withReturnDate(OffsetDateTime.now()));
        streamBridge.send(
                "sendMessage-out-0",
                String.format("User with id: %s return book with id: %s", rentBookRequestDto.userId(), rentBookRequestDto.bookId())
        );
    }

    public List<RentalDto> getUserRentals(Integer userId) {
        log.info("Fetching user rentals");
        return rentalDao.getByUserId(userId).stream().map(mapper::map).toList();
    }

    public List<RentalDto> getBookRentals(Integer bookId) {
        return rentalDao.getByBookId(bookId).stream().map(mapper::map).toList();
    }

}
