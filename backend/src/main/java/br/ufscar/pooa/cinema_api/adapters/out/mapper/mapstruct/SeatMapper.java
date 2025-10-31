package br.ufscar.pooa.cinema_api.adapters.out.mapper.mapstruct;

import br.ufscar.pooa.cinema_api.adapters.out.persistence.entities.SeatEntity;
import br.ufscar.pooa.cinema_api.domain.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {

    @Mapping(target = "row", ignore = true)
    @Mapping(target = "tickets", ignore = true)
    Seat toDomain(SeatEntity entity);
}