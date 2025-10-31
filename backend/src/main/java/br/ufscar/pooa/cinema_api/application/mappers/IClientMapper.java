package br.ufscar.pooa.cinema_api.application.mappers;

import br.ufscar.pooa.cinema_api.application.dtos.client.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.client.RegisterClientRequestDTO;
import br.ufscar.pooa.cinema_api.domain.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClientMapper {
    ClientResponseDTO toClientResponseDTO(Client client);
    Client toClient(RegisterClientRequestDTO registerClientRequestDTO);
}