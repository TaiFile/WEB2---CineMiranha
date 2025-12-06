package br.ufscar.pooa.cinema_api.features.sessions.controller;

import br.ufscar.pooa.cinema_api.features.sessions.dto.RegisterSessionRequestDTO;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.features.sessions.usecase.IRegisterSessionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final IRegisterSessionUseCase registerSessionUseCase;

    public SessionController(IRegisterSessionUseCase registerSessionUseCase) {
        this.registerSessionUseCase = registerSessionUseCase;
    }

    @PostMapping
    public ResponseEntity<SessionResponseDTO> registerSession(@Valid @RequestBody RegisterSessionRequestDTO requestDTO) {
        var responseDTO = registerSessionUseCase.execute(requestDTO);

        URI uri = URI.create("/sessions/" + responseDTO.getId());

        return ResponseEntity.created(uri).body(responseDTO);
    }
}
