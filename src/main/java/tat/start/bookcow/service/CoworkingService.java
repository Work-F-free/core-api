package tat.start.bookcow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tat.start.bookcow.dto.CreateCoworkingRequest;
import tat.start.bookcow.dto.SearchCoworkingRequest;
import tat.start.bookcow.entity.Coworking;
import tat.start.bookcow.entity.Booking;
import tat.start.bookcow.entity.Seat;
import tat.start.bookcow.repository.CoworkingRepository;
import tat.start.bookcow.repository.util.CoworkingSpecification;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CoworkingService {

    private final CoworkingRepository coworkingRepository;
    private final UserService userService;

    public Coworking getById(Long id) {
        return coworkingRepository.findById(id).orElseThrow();
    }

    public void bookSeat(Long coworkingId, Integer seatNumber, Long aLong) {
        var coworking = getById(coworkingId);
        var seat = coworking.getSeats().stream().filter(s -> Objects.equals(s.getNumber(), seatNumber))
                .findFirst().orElseThrow();

    }

    public Coworking create(CreateCoworkingRequest request) {
        var owner = userService.getUserById(request.owner());
        var seats = request.seats().stream().map(r -> new Seat(r.seatNumber(), r.capacity(), null, r.description()))
                .toList();
        var coworking = new Coworking(request.name(), request.address(), owner, seats, request.description(),
                request.xCoordinate(), request.yCoordinate());
        return coworkingRepository.save(coworking);
    }

    public Page<Coworking> search(SearchCoworkingRequest request, Pageable pageable) {
        Specification<Coworking> specification = CoworkingSpecification.byIdIgnoreNull(request.id())
                .and(CoworkingSpecification.byNameIgnoreNull(request.name()))
                .and(CoworkingSpecification.byNameCaseInsensitiveIgnoreNull(request.search()));
        return coworkingRepository.findAll(specification, pageable);
    }

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
