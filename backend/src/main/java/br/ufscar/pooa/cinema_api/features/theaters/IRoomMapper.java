package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.RoomResponseDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IRowMapper.class})
public interface IRoomMapper {
    @Mapping(source = "theater.id", target = "theaterId")
    RoomResponseDTO toRoomResponseDTO(Room room);
}
