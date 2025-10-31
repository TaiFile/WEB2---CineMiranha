package br.ufscar.pooa.cinema_api.domain;

import br.ufscar.pooa.cinema_api.domain.enums.SeatType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Seat {
    private Long id;
    private Character number;
    private SeatType seatType;
    private Row row;
    private List<Ticket> tickets = new ArrayList<>();

    public Seat() {
    }

    public Seat(Character number, Row row, List<Ticket> tickets, SeatType seatType) {
        this.number = number;
        this.row = row;
        this.tickets = tickets;
        this.seatType = seatType;
    }

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

    public Row getRow() {
        return row;
    }

    public void setRow(Row row) {
        this.row = row;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return Objects.equals(getId(), seat.getId()) && Objects.equals(getNumber(), seat.getNumber()) && getSeatType() == seat.getSeatType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getSeatType());
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", number=" + number +
                ", seatType=" + seatType +
                '}';
    }
}
