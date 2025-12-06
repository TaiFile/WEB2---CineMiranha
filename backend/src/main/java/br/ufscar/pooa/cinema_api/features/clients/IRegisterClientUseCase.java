package br.ufscar.pooa.cinema_api.features.clients;

import br.ufscar.pooa.cinema_api.features.clients.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.features.clients.RegisterClientRequestDTO;

public interface IRegisterClientUseCase {
    ClientResponseDTO execute(RegisterClientRequestDTO requestDTO);
}
