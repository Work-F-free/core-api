package tat.start.work.four.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tat.start.work.four.free.dto.CreateCoworkingRequest;
import tat.start.work.four.free.dto.SearchCoworkingRequest;
import tat.start.work.four.free.entity.Coworking;
import tat.start.work.four.free.entity.Booking;
import tat.start.work.four.free.entity.Seat;
import tat.start.work.four.free.repository.CoworkingRepository;
import tat.start.work.four.free.repository.SeatRepository;
import tat.start.work.four.free.repository.util.CoworkingSpecification;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CoworkingService {

    private final CoworkingRepository coworkingRepository;
    private final SeatRepository seatRepository;

    public Coworking getById(Long id) {
        return coworkingRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void bookSeat(Long coworkingId, Integer seatNumber, Long aLong) {
        var coworking = getById(coworkingId);
        var seat = coworking.getSeats().stream().filter(s -> Objects.equals(s.getNumber(), seatNumber))
                .findFirst().orElseThrow();

    }

    @Transactional
    public Coworking create(CreateCoworkingRequest request) {
        var seats = request.seats().stream().map(r -> new Seat(r.seatNumber(), r.capacity(), null, r.description()))
                .toList();
        var coworking = new Coworking(request.name(), request.address(), request.owner(), seats, request.description(),
                request.xCoordinate(), request.yCoordinate());
        return coworkingRepository.save(coworking);
    }

    public Page<Coworking> search(SearchCoworkingRequest request, Pageable pageable) {
        Specification<Coworking> specification = CoworkingSpecification.byIdIgnoreNull(request.id())
                .and(CoworkingSpecification.byNameIgnoreNull(request.name()))
                .and(CoworkingSpecification.byNameCaseInsensitiveIgnoreNull(request.search()));
        return coworkingRepository.findAll(specification, pageable);
    }

    @Transactional
    public Coworking update(Coworking coworking) {
        return coworkingRepository.save(coworking);
    }

    public List<Booking> getSeatSchedule(Long coworkingId, Integer seatNum, Instant from, Instant to) {
        var seat = this.getById(coworkingId).getSeats().stream()
                .filter(s -> Objects.equals(s.getNumber(), seatNum)).findFirst().orElseThrow();
        var schedule = seat.getBookings().stream()
                .filter(s -> s.getFromDatetime().isAfter(from) && s.getToDatetime().isBefore(to)).toList();
        return schedule;

    }
}
