package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.RoomEntity;
import br.ufscar.pooa.cinema_api.domain.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {RowMapper.class})
public interface RoomMapper {

    @Mapping(target = "theater", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    Room toDomain(RoomEntity entity);
}