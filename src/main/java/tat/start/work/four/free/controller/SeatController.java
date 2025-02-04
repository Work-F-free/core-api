package tat.start.work.four.free.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tat.start.work.four.free.dto.BookingResponse;
import tat.start.work.four.free.dto.CreateBookingRequest;
import tat.start.work.four.free.dto.SeatResponse;
import tat.start.work.four.free.service.SeatService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/seat")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    @GetMapping("/{id}")
    public ResponseEntity<SeatResponse> getSeatById(@PathVariable Long id) {
        var seat = seatService.getById(id);
        var bookings = seat.getBookings().stream().map(b -> new BookingResponse(b.getFromDatetime(), b.getToDatetime()))
                .toList();
        return ResponseEntity.ok(new SeatResponse(seat.getId(), seat.getNumber(), seat.getCapacity(),
                seat.getDescription(), bookings));
    }

    @PostMapping("/{id}/booking")
    public ResponseEntity<Void> addBooking(@PathVariable Long id, @RequestBody CreateBookingRequest request) {
        seatService.bookSeat(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/booking")
    public ResponseEntity<List<BookingResponse>> getSeatBooking(@PathVariable Long id, @RequestParam Instant from,
                                                                @RequestParam Instant to) {
        var bookings = seatService.getSeatSchedule(id, from, to).stream().map(b -> new BookingResponse(
                b.getFromDatetime(), b.getToDatetime())).toList();
        return ResponseEntity.ok(bookings);
    }

}
