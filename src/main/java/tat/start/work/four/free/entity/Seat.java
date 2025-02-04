package tat.start.work.four.free.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity(name = "seat")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "coworking_id", nullable = false, updatable = false)
    @Setter
    private Coworking coworking;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private SeatType type;

    @Column(name = "number")
    private Integer number;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "price")
    private Float price;

    @ElementCollection
    @CollectionTable(name = "booking", joinColumns = {@JoinColumn(name = "id")})
    private List<Booking> bookings;

    @Column(name = "description")
    private String description;

    public Seat(SeatType type, Integer seatNumber, Integer capacity, Float price, List<Booking> bookings,
                String description) {
        this.id = null;
        this.type = type;
        this.number = seatNumber;
        this.capacity = capacity;
        this.price = price;
        this.bookings = bookings;
        this.description = description;
    }

    public boolean isBooked(Instant from, Instant to) {
        for (var item: bookings) {
            if (to.isBefore(item.getToDatetime()) && to.isAfter(item.getFromDatetime())) {
                return true;
            }
            if (from.isBefore(item.getToDatetime()) && from.isAfter(item.getFromDatetime())) {
                return true;
            };
        }
        return false;
    }


    public Seat book(String phoneNumber, Instant fromDatetime, Instant toDatetime) {
        if (!this.isBooked(fromDatetime, toDatetime)) {
            this.getBookings().add(new Booking(fromDatetime, toDatetime, phoneNumber, false));
            return this;
        }
        throw new RuntimeException("Seat is already booked");
    }
}

