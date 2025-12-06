package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.RegisterTheaterRequestDTO;
import br.ufscar.pooa.cinema_api.features.theaters.TheaterResponseDTO;

public interface IRegisterTheaterUseCase {
    TheaterResponseDTO execute(String email, RegisterTheaterRequestDTO requestDTO);
}
