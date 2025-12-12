package br.ufscar.pooa.cinema_api.domain.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "rows")
public class Row {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Character letter;

	@ManyToOne
	@JoinColumn(name = "room_id", nullable = false)
	private Room room;

	@OneToMany(mappedBy = "row", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@OrderBy("number ASC")
	private List<Seat> seats = new ArrayList<>();

	public Row(){

	}

	public Long getId() {
		return id;
	}

	public Row setId(Long id) {
		this.id = id;
		return this;
	}

	public Character getLetter() {
		return letter;
	}

	public Row setLetter(Character letter) {
		this.letter = letter;
		return this;
	}

	public Room getRoom() {
		return room;
	}

	public Row setRoom(Room room) {
		this.room = room;
		return this;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public Row setSeats(List<Seat> seats) {
		this.seats = seats;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		Row row = (Row) o;
		return Objects.equals(getId(), row.getId()) && Objects.equals(getLetter(), row.getLetter());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getLetter());
	}

	@Override
	public String toString() {
		return "Row{" +
				"id=" + id +
				", letter=" + letter +
				'}';
	}
}
