package br.ufscar.pooa.cinema_api.features.managers.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Manager;
import br.ufscar.pooa.cinema_api.features.managers.dto.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.features.managers.dto.RegisterManagerRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IManagerMapper {

    ManagerResponseDTO toManagerResponseDTO(Manager manager);

    Manager toManager(RegisterManagerRequestDTO registerManagerRequestDTO);
}
