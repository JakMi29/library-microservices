package com.library.rental.infrastructure.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental")
public class RentalEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "rental_date")
    private OffsetDateTime rentalDate;
    @Column(name = "return_date")
    private OffsetDateTime returnDate;
    @Column(name="fee")
    private BigDecimal fee;
}
