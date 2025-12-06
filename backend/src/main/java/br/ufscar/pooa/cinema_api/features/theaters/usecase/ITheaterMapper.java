package br.ufscar.pooa.cinema_api.features.theaters.usecase;

import br.ufscar.pooa.cinema_api.features.rooms.usecase.IAddressMapper;
import br.ufscar.pooa.cinema_api.domain.entities.Theater;
import br.ufscar.pooa.cinema_api.features.managers.usecase.IUserMapper;
import br.ufscar.pooa.cinema_api.features.theaters.dto.TheaterResponseDTO;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IAddressMapper.class, IUserMapper.class})
public interface ITheaterMapper {
    @Mapping(source = "distance", target = "distance")
    TheaterResponseDTO toTheaterResponseDTO(Theater theater);
}
