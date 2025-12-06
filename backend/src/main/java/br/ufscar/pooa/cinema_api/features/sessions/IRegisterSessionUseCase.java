package br.ufscar.pooa.cinema_api.features.sessions;

import br.ufscar.pooa.cinema_api.features.sessions.RegisterSessionRequestDTO;
import br.ufscar.pooa.cinema_api.features.sessions.SessionResponseDTO;

public interface IRegisterSessionUseCase {
    SessionResponseDTO execute(RegisterSessionRequestDTO requestDTO);
}
