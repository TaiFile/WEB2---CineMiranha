package br.ufscar.pooa.cinema_api.features.managers;

import br.ufscar.pooa.cinema_api.features.managers.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.features.managers.RegisterManagerRequestDTO;

public interface IRegisterManagerUseCase {
    ManagerResponseDTO execute(RegisterManagerRequestDTO requestDTO);
}
