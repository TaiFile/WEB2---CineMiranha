package br.ufscar.pooa.cinema_api.features.admins;

public interface IRegisterUserUseCase {
    UserResponseDTO execute(RegisterUserRequestDTO requestDTO);
}
