package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.theater.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.domain.Theater;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {IAddressMapper.class, IUserMapper.class})
public interface ITheaterMapper {
    @Mapping(source = "distance", target = "distance")
    TheaterResponseDTO toTheaterResponseDTO(Theater theater);
}
