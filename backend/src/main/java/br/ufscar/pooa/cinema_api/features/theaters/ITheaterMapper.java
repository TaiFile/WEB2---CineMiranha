package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Theater;
import br.ufscar.pooa.cinema_api.features.managers.IUserMapper;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IAddressMapper.class, IUserMapper.class})
public interface ITheaterMapper {
    @Mapping(source = "distance", target = "distance")
    TheaterResponseDTO toTheaterResponseDTO(Theater theater);
}
