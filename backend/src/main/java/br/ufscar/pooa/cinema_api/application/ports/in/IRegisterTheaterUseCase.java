package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.theater.RegisterTheaterRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.theater.TheaterResponseDTO;

public interface IRegisterTheaterUseCase {
    TheaterResponseDTO execute(String email, RegisterTheaterRequestDTO requestDTO);
}
