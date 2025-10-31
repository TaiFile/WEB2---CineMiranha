package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.room.RoomResponseDTO;
import br.ufscar.pooa.cinema_api.domain.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IRowMapper.class})
public interface IRoomMapper {
    @Mapping(source = "theater.id", target = "theaterId")
    RoomResponseDTO toRoomResponseDTO(Room room);
}
