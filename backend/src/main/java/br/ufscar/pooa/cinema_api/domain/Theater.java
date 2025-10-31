package br.ufscar.pooa.cinema_api.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Theater {
    private Long id;
    private String name;
    private String logoUrl;
    private Address address;
    private List<Room> rooms = new ArrayList<>();
    private List<User> managers = new ArrayList<>();

    public Theater() {}

    public Theater(String name, String logoUrl, List<Room> rooms, Address address, List<User> managers) {
        this.name = name;
        this.logoUrl = logoUrl;
        this.rooms = rooms;
        this.address = address;
        this.managers = managers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<User> getManagers() {
        return managers;
    }

    public void setManagers(List<User> managers) {
        this.managers = managers;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Theater theater = (Theater) o;
        return Objects.equals(id, theater.id) &&
                Objects.equals(name, theater.name) &&
                Objects.equals(logoUrl, theater.logoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, logoUrl);
    }

    @Override
    public String toString() {
        return "Theater{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
