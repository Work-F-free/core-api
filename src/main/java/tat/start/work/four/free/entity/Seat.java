package tat.start.work.four.free.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
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

@Entity(name = "seat")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "coworking_id", nullable = false, updatable = false)
    @Setter
    private Coworking coworking;

    @Column(name = "number")
    private Integer number;

    @Column(name = "capacity")
    private Integer capacity;

    @ElementCollection
    @CollectionTable(name = "booking", joinColumns = {@JoinColumn(name = "id")})
    private List<Booking> bookings;

    @Column(name = "description")
    private String description;

    public Seat(Integer number, Integer capacity, List<Booking> bookings, String description) {
        this.id = null;
        this.number = number;
        this.capacity = capacity;
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


    public void book(String phoneNumber, Instant fromDatetime, Instant toDatetime) {

    }
}

