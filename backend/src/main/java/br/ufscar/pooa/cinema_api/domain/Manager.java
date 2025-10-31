package br.ufscar.pooa.cinema_api.domain;

import br.ufscar.pooa.cinema_api.domain.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Manager extends User {
    private String cpf;
    private LocalDate birthDate;
    private Theater theater;

    public Manager(String email,
                   String password,
                   String cpf,
                   LocalDate birthDate,
                   Theater theater,
                   Role role) {
        super(email, password, role);
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.theater = theater;
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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Manager manager = (Manager) o;
        return Objects.equals(cpf, manager.cpf) && Objects.equals(birthDate, manager.birthDate) && Objects.equals(theater, manager.theater);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cpf, birthDate, theater);
    }
}
