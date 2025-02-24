package com.library.rental.infrastructure.database.repository.mapper;

import com.library.rental.domain.Rental;
import com.library.rental.infrastructure.database.entity.RentalEntity;
import org.springframework.stereotype.Component;

@Component
public class RentalsEntityMapper {
    public Rental map(RentalEntity entity) {
        return Rental.builder()
                .id(entity.getId())
                .bookId(entity.getBookId())
                .userId(entity.getUserId())
                .rentalDate(entity.getRentalDate())
                .returnDate(entity.getReturnDate())
                .fee(entity.getFee())
                .build();
    }

    public RentalEntity map(Rental rental) {
        return RentalEntity.builder()
                .id(rental.getId())
                .bookId(rental.getBookId())
                .userId(rental.getUserId())
                .rentalDate(rental.getRentalDate())
                .returnDate(rental.getReturnDate())
                .fee(rental.getFee())
                .build();
    }
}
