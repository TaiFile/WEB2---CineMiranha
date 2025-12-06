package br.ufscar.pooa.cinema_api.features.rooms.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Seat;
import br.ufscar.pooa.cinema_api.features.theaters.dto.SeatDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISeatMapper {

    SeatDTO toSeatDTO(Seat seat);

    Seat toSeat(SeatDTO seatDTO);
}
