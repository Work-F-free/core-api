package tat.start.work.four.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tat.start.work.four.free.dto.CreateBookingRequest;
import tat.start.work.four.free.entity.Booking;
import tat.start.work.four.free.entity.Seat;
import tat.start.work.four.free.repository.SeatRepository;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public void bookSeat(Long id, CreateBookingRequest request) {
        var seat = getById(id);
        if (!seat.isBooked(request.fromDatetime(), request.toDatetime())) {
            seat.getBookings().add(new Booking(request.fromDatetime(), request.toDatetime(), request.phoneNumber(), false));
            seatRepository.save(seat);
        }
    }

    public Seat getById(Long id) {
        return seatRepository.findById(id).orElseThrow();
    }

    public List<Booking> getSeatSchedule(Long id, Instant from, Instant to) {
        var seat = this.getById(id);
        return seat.getBookings().stream().filter(s -> s.getFromDatetime().isAfter(from) && s.getToDatetime().isBefore(to))
                .toList();
    }
}
