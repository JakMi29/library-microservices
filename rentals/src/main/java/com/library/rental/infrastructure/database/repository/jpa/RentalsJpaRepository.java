package com.library.rental.infrastructure.database.repository.jpa;

import com.library.rental.infrastructure.database.entity.RentalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
public interface RentalsJpaRepository extends JpaRepository<RentalEntity,Integer> {
    List<RentalEntity> findByUserId(Integer userId);
    List<RentalEntity> findByBookId(Integer BookId);

    Optional<RentalEntity> findByUserIdAndBookId(Integer userId, Integer bookId);
}
