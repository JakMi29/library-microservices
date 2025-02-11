package com.library.rental.business.mapper;

import com.library.rental.controller.dto.RentalDto;
import com.library.rental.domain.Rental;
import org.springframework.stereotype.Component;

@Component
public class RentalMapper {
    public Rental map(RentalDto rentalDto) {
        return Rental.builder()
                .id(rentalDto.getId())
                .userId(rentalDto.getUserId())
                .bookId(rentalDto.getBookId())
                .returnDate(rentalDto.getReturnDate())
                .rentalDate(rentalDto.getRentalDate())
                .fee(rentalDto.getFee())
                .build();
    }

    public RentalDto map(Rental rental) {
        return RentalDto.builder()
                .userId(rental.getUserId())
                .id(rental.getId())
                .bookId(rental.getBookId())
                .returnDate(rental.getReturnDate())
                .rentalDate(rental.getRentalDate())
                .fee(rental.getFee())
                .build();
    }
}
