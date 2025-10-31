package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.seat.SeatDTO;
import br.ufscar.pooa.cinema_api.domain.Seat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISeatMapper {
    SeatDTO toSeatDTO(Seat seat);
    Seat toSeat(SeatDTO seatDTO);
}
