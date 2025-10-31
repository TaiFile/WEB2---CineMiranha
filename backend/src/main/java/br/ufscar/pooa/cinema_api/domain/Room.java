package br.ufscar.pooa.cinema_api.domain;

import br.ufscar.pooa.cinema_api.domain.enums.RoomType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private Long id;
    private String name;
    private RoomType roomType;
    private Theater theater;
    private List<Row> rows = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();

    public Room() {
    }

    public Room(Long id, String name, RoomType roomType, Theater theater, List<Row> rows, List<Session> sessions) {
        this.id = id;
        this.name = name;
        this.roomType = roomType;
        this.theater = theater;
        this.rows = rows;
        this.sessions = sessions;
    }

    public Long getId() {
        return id;
    }

    public Room setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Room setName(String name) {
        this.name = name;
        return this;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public Room setRoomType(RoomType roomType) {
        this.roomType = roomType;
        return this;
    }

    public Theater getTheater() {
        return theater;
    }

    public Room setTheater(Theater theater) {
        this.theater = theater;
        return this;
    }

    public List<Row> getRows() {
        return rows;
    }

    public Room setRows(List<Row> rows) {
        this.rows = rows;
        return this;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public Room setSessions(List<Session> sessions) {
        this.sessions = sessions;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getId(), room.getId()) && Objects.equals(getName(), room.getName()) && getRoomType() == room.getRoomType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRoomType());
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", roomType=" + roomType +
                '}';
    }
}