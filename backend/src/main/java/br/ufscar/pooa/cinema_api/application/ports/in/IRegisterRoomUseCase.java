package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.application.dtos.room.RegisterRoomRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.room.RoomResponseDTO;

public interface IRegisterRoomUseCase {
    RoomResponseDTO execute(RegisterRoomRequestDTO requestDTO);
}
