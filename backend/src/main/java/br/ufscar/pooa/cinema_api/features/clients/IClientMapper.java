package br.ufscar.pooa.cinema_api.features.clients;

import br.ufscar.pooa.cinema_api.features.clients.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.features.clients.RegisterClientRequestDTO;
import br.ufscar.pooa.cinema_api.domain.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapper {
    ClientResponseDTO toClientResponseDTO(Client client);
    Client toClient(RegisterClientRequestDTO registerClientRequestDTO);
}
