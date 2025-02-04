package tat.start.work.four.free.entity;

import jakarta.persistence.CascadeType;
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
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "coworking")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coworking {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "owner_id", nullable = false, updatable = false)
    private String owner;

    @OneToMany(mappedBy = "coworking", cascade = {CascadeType.ALL})
    private List<Seat> seats = new ArrayList<>();

    @Column(name = "description")
    private String description;

    @Column(name = "longitude")
    private Float longitude;

    @Column(name = "latitude")
    private Float latitude;

    public Coworking(String name, String address, String owner, List<Seat> seats, String description, Float longitude,
                     Float latitude) {
        this.id = null;
        this.name = name;
        this.address = address;
        this.owner = owner;
        this.seats = seats;
        if (!CollectionUtils.isEmpty(seats)) {
            seats.forEach(s -> s.setCoworking(this));
        }
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Coworking update(String name, String address, String owner, String description, Float longitude,
                            Float latitude) {
        this.name = name;
        this.address = address;
        this.owner = owner;
        if (!CollectionUtils.isEmpty(seats)) {
            seats.forEach(s -> s.setCoworking(this));
        }
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;

        return this;
    }
}
