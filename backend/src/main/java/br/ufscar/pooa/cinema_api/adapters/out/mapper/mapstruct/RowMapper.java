package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.RowEntity;
import br.ufscar.pooa.cinema_api.domain.Row;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {SeatMapper.class})
public interface RowMapper {

    @Mapping(target = "room", ignore = true)
    Row toDomain(RowEntity entity);
}