package com.library.rental.infrastructure.database.repository;

import com.library.rental.business.dao.RentalDao;
import com.library.rental.domain.Rental;
import com.library.rental.infrastructure.database.repository.jpa.RentalsJpaRepository;
import com.library.rental.infrastructure.database.repository.mapper.RentalsEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RentalsRepository implements RentalDao {

    private final RentalsJpaRepository repository;
    private final RentalsEntityMapper mapper;


    @Override
    public Rental createRental(Rental rental) {
        return mapper.map(repository.save(mapper.map(rental)));
    }

    @Override
    public Rental updateRental(Rental rental) {
        return mapper.map(repository.save(mapper.map(rental)));
    }

    @Override
    public Optional<Rental> getByUserAndBook(Integer userId, Integer bookId) {
        return repository.findByUserIdAndBookId(userId,bookId).map(mapper::map);
    }

    @Override
    public List<Rental> getByBookId(Integer bookId) {
        return repository.findByBookId(bookId).stream().map(mapper::map).toList();
    }

    @Override
    public List<Rental> getByUserId(Integer userId) {
        return repository.findByUserId(userId).stream().map(mapper::map).toList();
    }

}
