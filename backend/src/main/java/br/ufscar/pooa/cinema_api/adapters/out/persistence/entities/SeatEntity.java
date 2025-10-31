package br.ufscar.pooa.cinema_api.adapters.out.persistence.entities;

import br.ufscar.pooa.cinema_api.domain.enums.SeatType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "seats")
public class SeatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Character number;

    @Column
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne
    @JoinColumn(name = "row_id", nullable = false)
    private RowEntity row;

    @OneToMany(mappedBy = "seat")
    private List<TicketEntity> tickets = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Character getNumber() {
        return number;
    }

    public void setNumber(Character number) {
        this.number = number;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public RowEntity getRow() {
        return row;
    }

    public void setRow(RowEntity row) {
        this.row = row;
    }

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SeatEntity that = (SeatEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getNumber(), that.getNumber()) && getSeatType() == that.getSeatType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getSeatType());
    }

    @Override
    public String toString() {
        return "SeatEntity{" +
                "id=" + id +
                ", number=" + number +
                ", seatType=" + seatType +
                '}';
    }
}
