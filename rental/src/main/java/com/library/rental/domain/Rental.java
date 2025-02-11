package com.library.rental.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.With;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Value
@With
@Builder
@EqualsAndHashCode
public class Rental {
    Integer id;
    Integer bookId;
    Integer userId;
    OffsetDateTime rentalDate;
    OffsetDateTime returnDate;
    BigDecimal fee;
}
