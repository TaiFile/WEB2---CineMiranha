package br.ufscar.pooa.cinema_api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Row {
	private Long id;
	private Character letter;
	private Room room;
	private List<Seat> seats = new ArrayList<>();

	public Row(Character letter, Room room, List<Seat> seats) {
		this.letter = letter;
		this.room = room;
		this.seats = seats;
	}

	public Row(){

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

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Row row = (Row) o;
		return Objects.equals(getId(), row.getId()) && Objects.equals(getLetter(), row.getLetter()) && Objects.equals(getRoom(), row.getRoom()) && Objects.equals(getSeats(), row.getSeats());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getLetter(), getRoom(), getSeats());
	}

	@Override
	public String toString() {
		return "Row{" +
				"id=" + id +
				", letter=" + letter +
				", room=" + room +
				", seats=" + seats +
				'}';
	}
}