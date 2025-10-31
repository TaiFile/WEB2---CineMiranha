package br.ufscar.pooa.cinema_api.domain;

import br.ufscar.pooa.cinema_api.domain.enums.Format;
import br.ufscar.pooa.cinema_api.domain.enums.Subtitle;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
    private Format format;
    private LocalDateTime date;
    private Subtitle subtitle;
    private Integer priceInCents;
    private Room room;
    private Movie movie;
    private List<Ticket> tickets = new ArrayList<>();

    public boolean isSeatAvailable(Seat newSeat) {
        if ( newSeat == null ) return false;
        if ( room == null ) return true;
        if ( tickets == null ) return true;

        List<List<Seat>> allSeats = room.getRows().stream().map(Row::getSeats).toList();
        List<Seat> takenSeats = tickets.stream().map(Ticket::getSeat).toList();

        List<Seat> availableSeats = allSeats.stream()
                .flatMap(List::stream)
                .filter(seat -> !takenSeats.contains(seat))
                .toList();

        return availableSeats.contains(newSeat);
    }

    public Session() {
    }

    public Session(Format format, LocalDateTime date, Subtitle subtitle, Integer priceInCents, Room room, Movie movie, List<Ticket> tickets) {
        this.format = format;
        this.date = date;
        this.subtitle = subtitle;
        this.priceInCents = priceInCents;
        this.room = room;
        this.movie = movie;
        this.tickets = tickets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Subtitle getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(Subtitle subtitle) {
        this.subtitle = subtitle;
    }

    public Integer getPriceInCents() {
        return priceInCents;
    }

    public void setPriceInCents(Integer priceInCents) {
        this.priceInCents = priceInCents;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
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
        Session session = (Session) o;
        return Objects.equals(getId(), session.getId()) && getFormat() == session.getFormat() && Objects.equals(getDate(), session.getDate()) && getSubtitle() == session.getSubtitle() && Objects.equals(getPriceInCents(), session.getPriceInCents());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFormat(), getDate(), getSubtitle(), getPriceInCents());
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", format=" + format +
                ", date=" + date +
                ", subtitle=" + subtitle +
                ", priceInCents=" + priceInCents +
                '}';
    }
}
