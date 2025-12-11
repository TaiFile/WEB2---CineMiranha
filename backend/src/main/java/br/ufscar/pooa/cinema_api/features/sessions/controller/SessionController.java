package br.ufscar.pooa.cinema_api.features.sessions.controller;

import br.ufscar.pooa.cinema_api.features.sessions.dto.RegisterSessionRequestDTO;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionDetailResponseDTO;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.features.sessions.usecase.FindSessionByIdUseCase;
import br.ufscar.pooa.cinema_api.features.sessions.usecase.RegisterSessionUseCase;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    private final RegisterSessionUseCase registerSessionUseCase;
    private final FindSessionByIdUseCase findSessionByIdUseCase;

    public SessionController(RegisterSessionUseCase registerSessionUseCase,
                             FindSessionByIdUseCase findSessionByIdUseCase) {
        this.registerSessionUseCase = registerSessionUseCase;
        this.findSessionByIdUseCase = findSessionByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<SessionResponseDTO> registerSession(@Valid @RequestBody RegisterSessionRequestDTO requestDTO) {
        var responseDTO = registerSessionUseCase.execute(requestDTO);

        URI uri = URI.create("/sessions/" + responseDTO.getId());

        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDetailResponseDTO> getSessionById(@PathVariable Long id) {
        var responseDTO = findSessionByIdUseCase.execute(id);
        return ResponseEntity.ok(responseDTO);
    }
}
