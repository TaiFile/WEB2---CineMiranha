package br.ufscar.pooa.cinema_api.features.clients.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Client;
import br.ufscar.pooa.cinema_api.features.clients.dto.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.features.clients.dto.RegisterClientRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapper {

    ClientResponseDTO toClientResponseDTO(Client client);

    Client toClient(RegisterClientRequestDTO registerClientRequestDTO);
}
