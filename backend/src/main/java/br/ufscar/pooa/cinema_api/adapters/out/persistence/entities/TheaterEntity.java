package br.ufscar.pooa.cinema_api.adapters.out.persistence.entities;

import br.ufscar.pooa.cinema_api.domain.Manager;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

@Entity
@Table(name = "theaters")
public class TheaterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String logoUrl;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private AddressEntity address;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<RoomEntity> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "theater")
    private List<ManagerEntity> managers = new ArrayList<>();

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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    public List<ManagerEntity> getManagers() {
        return managers;
    }

    public void setManagers(List<ManagerEntity> managers) {
        this.managers = managers;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TheaterEntity that = (TheaterEntity) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getLogoUrl(), that.getLogoUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getLogoUrl());
    }
}
