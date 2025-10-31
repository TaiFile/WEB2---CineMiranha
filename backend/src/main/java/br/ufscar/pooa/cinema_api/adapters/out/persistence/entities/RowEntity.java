package br.ufscar.pooa.cinema_api.adapters.out.persistence.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "rows")
public class RowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Character letter;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private RoomEntity room;

    @OneToMany(mappedBy = "row", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<SeatEntity> seats = new LinkedHashSet<>();

    public RowEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public Set<SeatEntity> getSeats() {
        return seats;
    }

    public void setSeats(Set<SeatEntity> seats) {
        this.seats = seats;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RowEntity rowEntity = (RowEntity) o;
        return Objects.equals(getId(), rowEntity.getId()) && Objects.equals(getLetter(), rowEntity.getLetter());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLetter());
    }

    @Override
    public String toString() {
        return "RowEntity{" +
                "id=" + id +
                ", letter=" + letter +
                '}';
    }
}
