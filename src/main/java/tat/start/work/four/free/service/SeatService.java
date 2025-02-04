package tat.start.work.four.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tat.start.work.four.free.dto.CreateBookingRequest;
import tat.start.work.four.free.dto.SearchCoworkingRequest;
import tat.start.work.four.free.entity.Booking;
import tat.start.work.four.free.entity.Seat;
import tat.start.work.four.free.repository.SeatRepository;
import tat.start.work.four.free.repository.util.SeatSpecification;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static tat.start.work.four.free.repository.util.SeatSpecification.byIdIgnoreNull;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepository seatRepository;

    @Transactional
    public Seat bookSeat(UUID id, CreateBookingRequest request) {
        var seat = getById(id);
        return seat.book(request.phoneNumber(), request.fromDatetime(), request.toDatetime());
    }

    public Seat getById(UUID id) {
        return seatRepository.findById(id).orElseThrow();
    }

    public List<Booking> getSeatSchedule(UUID id, Instant from, Instant to) {
        var seat = this.getById(id);
        return seat.getBookings().stream().filter(s -> s.getFromDatetime().isAfter(from) && s.getToDatetime().isBefore(to))
                .toList();
    }

    public List<Seat> search(SearchCoworkingRequest request) {
        Specification<Seat> specification = SeatSpecification.distinct()
                .and(byIdIgnoreNull(request.id()))
                .and(SeatSpecification.byNameIgnoreNull(request.name()))
                .and(SeatSpecification.byPriceGreaterOrEqualThanIgnoreNull(request.priceFrom()))
                .and(SeatSpecification.byPriceLessOrEqualThanIgnoreNull(request.priceTo()))
                .and(SeatSpecification.byTypeInIgnoreNull(request.types()))
                .and(SeatSpecification.byCapacityEqualsToIgnoreNull(request.capacity()))
                .and(SeatSpecification.byBookingAvailableAt(request.availableAt()));
        return seatRepository.findAll(specification);

    }
}
