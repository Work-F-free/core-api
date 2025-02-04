package tat.start.work.four.free.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tat.start.work.four.free.dto.BookingResponse;
import tat.start.work.four.free.dto.CoworkingResponse;
import tat.start.work.four.free.dto.CreateCoworkingRequest;
import tat.start.work.four.free.dto.CreateCoworkingResponse;
import tat.start.work.four.free.dto.SearchCoworkingRequest;
import tat.start.work.four.free.dto.SearchCoworkingResponse;
import tat.start.work.four.free.dto.SeatResponse;
import tat.start.work.four.free.dto.UpdateCoworkingRequest;
import tat.start.work.four.free.entity.Booking;
import tat.start.work.four.free.service.CoworkingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/coworking")
@RequiredArgsConstructor
public class CoworkingController {

    private final CoworkingService coworkingService;

    @GetMapping("/{id}")
    public ResponseEntity<CoworkingResponse> getCoworking(@PathVariable UUID id) {
        var coworking = coworkingService.getById(id);
        var seats = coworking.getSeats().stream().map(s -> {
            var bookings = s.getBookings().stream().map(b -> new BookingResponse(b.getFromDatetime(), b.getToDatetime()))
                    .toList();
            return new SeatResponse(s.getId(), s.getType(), s.getNumber(), s.getCapacity(), s.getPrice(),
                    s.getDescription(), bookings);
        }).toList();
        return ResponseEntity.ok(new CoworkingResponse(coworking.getId(), coworking.getName(), coworking.getAddress(),
                coworking.getOwner(), seats, coworking.getDescription()));
    }

    @GetMapping
    public ResponseEntity<Page<SearchCoworkingResponse>> searchCoworkings(
            @ParameterObject SearchCoworkingRequest request,
            @ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.DESC) @Nullable Pageable pageable
    ) {
        return ResponseEntity.ok(coworkingService.search(request, pageable));
    }

    @PostMapping
    public ResponseEntity<CreateCoworkingResponse> createCoworking(@RequestBody CreateCoworkingRequest request) {
        var coworking = coworkingService.create(request);
        var seats = coworking.getSeats().stream().map(s -> new CreateCoworkingResponse.CreateSeatResponse(s.getId(),
                s.getType(), s.getNumber(), s.getCapacity(), s.getPrice(), s.getDescription())).toList();
        return ResponseEntity.ok(new CreateCoworkingResponse(coworking.getId(), coworking.getAddress(),
                coworking.getName(), coworking.getDescription(), coworking.getOwner(), seats,
                coworking.getLongitude(), coworking.getLatitude()));
    }

    @PutMapping("/{id}")
    public void updateCoworking(@PathVariable UUID id, @RequestBody UpdateCoworkingRequest request) {
        var coworking = coworkingService.update(id, request);
    }

    @DeleteMapping
    public void deleteCoworking() {
        throw new UnsupportedOperationException("Unimplemented yet");
    }

}
