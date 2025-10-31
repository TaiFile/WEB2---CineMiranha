package br.ufscar.pooa.cinema_api.domain;

import jakarta.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

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
	private Set<Seat> seats = new LinkedHashSet<>();

	public Row(Character letter, Room room, Set<Seat> seats) {
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

	public Set<Seat> getSeats() {
		return seats;
	}

	public void setSeats(Set<Seat> seats) {
		this.seats = seats;
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
