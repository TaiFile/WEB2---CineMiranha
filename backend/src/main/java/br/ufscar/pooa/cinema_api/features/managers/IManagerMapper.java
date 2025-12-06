package br.ufscar.pooa.cinema_api.features.managers;

import br.ufscar.pooa.cinema_api.features.managers.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.features.managers.RegisterManagerRequestDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Manager;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IManagerMapper {
    ManagerResponseDTO toManagerResponseDTO(Manager manager);
    Manager toManager(RegisterManagerRequestDTO registerManagerRequestDTO);
}
