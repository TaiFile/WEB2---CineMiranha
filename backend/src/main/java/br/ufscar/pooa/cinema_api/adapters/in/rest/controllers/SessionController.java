package br.ufscar.pooa.cinema_api.adapters.in.rest.controllers;

import br.ufscar.pooa.cinema_api.application.dtos.session.RegisterSessionRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.session.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterSessionUseCase;
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
