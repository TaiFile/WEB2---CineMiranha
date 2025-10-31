package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.client.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.client.RegisterClientRequestDTO;

public interface IRegisterClientUseCase {
    ClientResponseDTO execute(RegisterClientRequestDTO requestDTO);
}
