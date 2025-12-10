package br.ufscar.pooa.cinema_api.features.rooms.controller;

import br.ufscar.pooa.cinema_api.features.rooms.dto.RegisterRoomRequestDTO;
import br.ufscar.pooa.cinema_api.features.rooms.dto.RoomResponseDTO;
import br.ufscar.pooa.cinema_api.features.rooms.usecase.RegisterRoomUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/rooms")
public class RoomController {
    private final RegisterRoomUseCase registerRoomUseCase;

    public RoomController(RegisterRoomUseCase registerRoomUseCase) {
        this.registerRoomUseCase = registerRoomUseCase;
    }

    @PostMapping
    public ResponseEntity<RoomResponseDTO> create(@Valid @RequestBody RegisterRoomRequestDTO requestDTO) {
        RoomResponseDTO responseDTO = registerRoomUseCase.execute(requestDTO);

        URI uri = URI.create("/rooms/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
