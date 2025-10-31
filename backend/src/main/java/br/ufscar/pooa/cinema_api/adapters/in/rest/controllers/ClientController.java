package br.ufscar.pooa.cinema_api.adapters.in.rest.controllers;

import br.ufscar.pooa.cinema_api.application.dtos.client.ClientResponseDTO;
import br.ufscar.pooa.cinema_api.application.dtos.client.RegisterClientRequestDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IRegisterClientUseCase registerClientUseCase;

    public ClientController(
            IRegisterClientUseCase registerClientUseCase) {
        this.registerClientUseCase = registerClientUseCase;
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> register(@RequestBody RegisterClientRequestDTO registerRequestBody) {
        var responseDTO = registerClientUseCase.execute(registerRequestBody);

        URI uri = URI.create("/clients/" + responseDTO.getId());

        return ResponseEntity.created(uri).body(responseDTO);
    }
}
