package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.user.RegisterUserRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.user.UserResponseDTO;

public interface IRegisterUserUseCase {
    UserResponseDTO execute(RegisterUserRequestDTO requestDTO);
}
