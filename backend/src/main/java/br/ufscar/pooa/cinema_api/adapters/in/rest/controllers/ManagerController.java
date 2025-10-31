package br.ufscar.pooa.cinema_api.adapters.in.rest.controllers;

import br.ufscar.pooa.cinema_api.application.dtos.manager.ManagerResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.manager.RegisterManagerRequestDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterManagerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final IRegisterManagerUseCase registerManagerUseCase;

    public ManagerController(IRegisterManagerUseCase registerManagerUseCase) {
        this.registerManagerUseCase = registerManagerUseCase;
    }

    @PostMapping
    public ResponseEntity<ManagerResponseDTO> register(@RequestBody RegisterManagerRequestDTO registerRequestBody) {
        var responseDTO = registerManagerUseCase.execute(registerRequestBody);

        URI uri = URI.create("/managers/" + responseDTO.getId());

        return ResponseEntity.created(uri).body(responseDTO);
    }
}
