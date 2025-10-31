package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.manager.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.manager.RegisterManagerRequestDTO;

public interface IRegisterManagerUseCase {
    ManagerResponseDTO execute(RegisterManagerRequestDTO requestDTO);
}
