package com.library.users.business.service.client;

import com.library.users.controller.dto.RentalDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class RentalsFallback implements RentalsFeignClient {
    @Override
    public ResponseEntity<List<RentalDto>> fetchUserRentals(Integer userId) {
        return ResponseEntity.ok(Collections.emptyList());
    }
}
