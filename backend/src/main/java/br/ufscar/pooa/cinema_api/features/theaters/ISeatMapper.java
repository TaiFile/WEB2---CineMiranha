package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.SeatDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISeatMapper {
    SeatDTO toSeatDTO(Seat seat);
    Seat toSeat(SeatDTO seatDTO);
}
