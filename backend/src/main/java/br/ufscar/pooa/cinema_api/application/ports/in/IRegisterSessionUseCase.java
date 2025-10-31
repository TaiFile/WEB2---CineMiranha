package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.session.RegisterSessionRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.session.SessionResponseDTO;

public interface IRegisterSessionUseCase {
    SessionResponseDTO execute(RegisterSessionRequestDTO requestDTO);
}
