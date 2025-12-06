package br.ufscar.pooa.cinema_api.features.theaters;

import br.ufscar.pooa.cinema_api.features.theaters.RegisterRoomRequestDTO;
import br.ufscar.pooa.cinema_api.features.theaters.RoomResponseDTO;

public interface IRegisterRoomUseCase {
    RoomResponseDTO execute(RegisterRoomRequestDTO requestDTO);
}
