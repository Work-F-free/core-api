package tat.start.bookcow.controller;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tat.start.bookcow.dto.BookingResponse;
import tat.start.bookcow.dto.CoworkingResponse;
import tat.start.bookcow.dto.CreateCoworkingRequest;
import tat.start.bookcow.dto.CreateCoworkingResponse;
import tat.start.bookcow.dto.SearchCoworkingRequest;
import tat.start.bookcow.dto.SearchCoworkingResponse;
import tat.start.bookcow.service.CoworkingService;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/coworking")
@RequiredArgsConstructor
public class CoworkingController {

    private final CoworkingService coworkingService;

    @GetMapping("/{id}")
    public ResponseEntity<CoworkingResponse> getCoworking(@PathVariable Long id) {
        var coworking = coworkingService.getById(id);
        return ResponseEntity.ok(new CoworkingResponse(coworking.getId(), coworking.getName(), coworking.getAddress(),
                coworking.getOwner().getId(), coworking.getSeats(), coworking.getDescription()));
    }

    @GetMapping("/{id}/seat/{seatNum}/booking")
    public ResponseEntity<List<BookingResponse>> getSeatBooking(@PathVariable Long id, @PathVariable Integer seatNum,
                                                                @RequestParam Instant from, @RequestParam Instant to) {
        var bookings = coworkingService.getSeatSchedule(id, seatNum, from, to).stream().map(b -> new BookingResponse(
                b.getFromDatetime(), b.getToDatetime())).toList();
        return ResponseEntity.ok(bookings);
    }

    @GetMapping
    public ResponseEntity<Page<SearchCoworkingResponse>> searchCoworkings(
            SearchCoworkingRequest request,
            @PageableDefault(sort = "name", direction = Sort.Direction.DESC)
            @Nullable
            Pageable pageable
    ) {
        var res = coworkingService.search(request, pageable).map(r -> new SearchCoworkingResponse(r.getId(),
                r.getName(), r.getAddress(), r.getOwner().getId(), r.getDescription()));
        return ResponseEntity.ok(res);
    }

    @PostMapping
    public ResponseEntity<CreateCoworkingResponse> createCoworking(@RequestBody CreateCoworkingRequest request) {
        var coworking = coworkingService.create(request);
        var seats = coworking.getSeats().stream().map(s -> new CreateCoworkingResponse.CreateSeatResponse(s.getId(),
                s.getNumber(), s.getCapacity(), s.getDescription())).toList();
        return ResponseEntity.ok(new CreateCoworkingResponse(coworking.getId(), coworking.getAddress(),
                coworking.getName(), coworking.getDescription(), coworking.getOwner().getId(), seats));
    }

    @PutMapping("/{id}")
    public void updateCoworking(@PathVariable Long id) {
        throw new UnsupportedOperationException("Unimplemented yet");
    }

    @DeleteMapping
    public void deleteCoworking() {
        throw new UnsupportedOperationException("Unimplemented yet");
    }

}
