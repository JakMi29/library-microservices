package com.library.rental.business.dao;


import com.library.rental.domain.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalDao {

    Rental createRental(Rental rental);

    Rental updateRental(Rental rental);

    Optional<Rental> getByUserAndBook(Integer userId, Integer bookId);

    List<Rental> getByBookId(Integer bookId);

    List<Rental> getByUserId(Integer userId);
}