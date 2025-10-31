package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.manager.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.manager.RegisterManagerRequestDTO;
import br.ufscar.pooa.cinema_api.domain.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IManagerMapper {
    ManagerResponseDTO toManagerResponseDTO(Manager manager);
    Manager toManager(RegisterManagerRequestDTO registerManagerRequestDTO);
}