package br.ufscar.pooa.cinema_api.domain;

import br.ufscar.pooa.cinema_api.domain.enums.Gender;
import br.ufscar.pooa.cinema_api.domain.enums.Role;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "managers")
public class Manager extends User {
    @Column
    private String cpf;

    @Column
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Manager(String email,
                   String password,
                   String cpf,
                   LocalDate birthDate,
                   Theater theater,
                   Role role,
                   Gender gender) {
        super(email, password, role);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.theater = theater;
        this.gender = gender;
    }

    public Manager() {
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(cpf, manager.cpf) && Objects.equals(birthDate, manager.birthDate) && Objects.equals(theater, manager.theater) && Objects.equals(gender, manager.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf, birthDate, theater, gender);
    }
}