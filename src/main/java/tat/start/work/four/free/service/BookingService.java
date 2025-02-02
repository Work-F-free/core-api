package tat.start.work.four.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tat.start.work.four.free.dto.CreateBookingRequest;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final CoworkingService coworkingService;

    public void create(CreateBookingRequest request) {
        Long coworkingId = request.coworkingId();
        var coworking = coworkingService.getById(coworkingId);
        Integer seatNumber = request.seatNumber();
        var seat = coworking.getSeats().stream().filter(s -> Objects.equals(s.getNumber(), seatNumber))
                .findFirst().orElseThrow();
        if (seat.isBooked(request.fromDatetime(), request.toDatetime())) {
            throw new IllegalStateException("This seat is already booked");
        }
        seat.book(request.phoneNumber(), request.fromDatetime(), request.toDatetime());
        coworkingService.update(coworking);
    }
}
