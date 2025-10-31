package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.theater.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.domain.Theater;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {IAddressMapper.class, IUserMapper.class})
public interface ITheaterMapper {
    TheaterResponseDTO toTheaterResponseDTO(Theater theater);
}
