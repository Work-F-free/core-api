package tat.start.work.four.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tat.start.work.four.free.dto.CreateCoworkingRequest;
import tat.start.work.four.free.dto.SearchCoworkingRequest;
import tat.start.work.four.free.dto.UpdateCoworkingRequest;
import tat.start.work.four.free.entity.Coworking;
import tat.start.work.four.free.entity.Booking;
import tat.start.work.four.free.entity.Seat;
import tat.start.work.four.free.repository.CoworkingRepository;
import tat.start.work.four.free.repository.SeatRepository;
import tat.start.work.four.free.repository.util.CoworkingSpecification;
import tat.start.work.four.free.repository.util.SeatProjection;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CoworkingService {

    private final CoworkingRepository coworkingRepository;
    private final SeatService seatService;

    public Coworking getById(UUID id) {
        return coworkingRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Coworking create(CreateCoworkingRequest request) {
        var seats = request.seats().stream().map(r -> new Seat(r.type(), r.seatNumber(), r.capacity(), r.price(),
                        null, r.description()))
                .toList();
        var coworking = new Coworking(request.name(), request.address(), request.owner(), seats, request.description(),
                request.longitude(), request.latitude());
        return coworkingRepository.save(coworking);
    }

    public Page<Coworking> search(SearchCoworkingRequest request, Pageable pageable) {
        var seatProjections = seatService.search(request);
        var coworkingIds = seatProjections.stream().map(s -> s.getCoworking().getId()).collect(Collectors.toSet());
        return coworkingRepository.findAllByIdIn(coworkingIds, pageable);
    }

    @Transactional
    public Coworking update(UUID id, UpdateCoworkingRequest request) {
        var coworking = this.getById(id);
        coworking.update(request.name(), request.address(), request.owner(), request.description(), request.longitude(),
                request.latitude());
        return coworkingRepository.save(coworking);
    }
}
