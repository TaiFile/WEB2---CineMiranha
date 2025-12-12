package br.ufscar.pooa.cinema_api.features.theaters.dto;

import br.ufscar.pooa.cinema_api.domain.enums.SeatType;

public class SeatDTO {
    private Long id;
    private Integer number;
    private SeatType seatType;

    public SeatDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }
}
