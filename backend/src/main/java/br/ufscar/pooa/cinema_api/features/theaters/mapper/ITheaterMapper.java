package br.ufscar.pooa.cinema_api.features.theaters.mapper;

import br.ufscar.pooa.cinema_api.domain.entities.Theater;
import br.ufscar.pooa.cinema_api.features.managers.mapper.IManagerMapper;
import br.ufscar.pooa.cinema_api.features.rooms.mapper.IAddressMapper;
import br.ufscar.pooa.cinema_api.features.theaters.dto.TheaterResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IAddressMapper.class, IManagerMapper.class})
public interface ITheaterMapper {

    @Mapping(source = "distance", target = "distance")
    TheaterResponseDTO toTheaterResponseDTO(Theater theater);
}

