package com.library.users.business.service.client;

import com.library.users.controller.dto.RentalDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("rentals")
public interface RentalsFeignClient {

    @GetMapping(value = "/api/fetch/user", consumes = "application/json")
    public ResponseEntity<List<RentalDto>> fetchUserRentals(@RequestParam Integer userId);

}
