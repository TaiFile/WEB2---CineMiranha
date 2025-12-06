package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.SeatDTO;

import java.util.List;

public class RowDTO {
    private Character letter;
    private List<SeatDTO> seats;

    public RowDTO() {
    }

    public Character getLetter() {
        return letter;
    }

    public void setLetter(Character letter) {
        this.letter = letter;
    }

    public List<SeatDTO> getSeats() {
        return seats;
    }

    public void setSeats(List<SeatDTO> seats) {
        this.seats = seats;
    }
}
