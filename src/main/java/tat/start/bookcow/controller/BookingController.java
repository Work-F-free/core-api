package tat.start.bookcow.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tat.start.bookcow.dto.CreateBookingRequest;
import tat.start.bookcow.service.BookingService;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<Void> addBooking(@RequestBody CreateBookingRequest request) {
        bookingService.create(request);
        return ResponseEntity.noContent().build();
    }
}
