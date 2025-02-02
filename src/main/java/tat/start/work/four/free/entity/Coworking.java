package tat.start.work.four.free.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Entity(name = "coworking")
@Getter
@AllArgsConstructor
public class Coworking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false, updatable = false)
    private Owner owner;

    @OneToMany(mappedBy = "coworking")
    private List<Seat> seats;

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    public Coworking(String name, String address, Owner owner, List<Seat> seats, String description, Float longitude,
                     Float latitude) {
        this.id = null;
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.seats = seats;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
