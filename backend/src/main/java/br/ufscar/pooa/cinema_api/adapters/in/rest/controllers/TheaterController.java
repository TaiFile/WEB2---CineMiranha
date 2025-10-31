package br.ufscar.pooa.cinema_api.adapters.in.rest.controllers;

import br.ufscar.pooa.cinema_api.application.dtos.theater.RegisterTheaterRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.theater.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterTheaterUseCase;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RequestMapping("/theaters")
@RestController
public class TheaterController {
    private final IRegisterTheaterUseCase registerTheaterUseCase;

    public TheaterController(IRegisterTheaterUseCase registerTheaterUseCase) {
        this.registerTheaterUseCase = registerTheaterUseCase;
    }

    @PostMapping
    public ResponseEntity<TheaterResponseDTO> register(@PathParam("userEmail") String userEmail , @RequestBody RegisterTheaterRequestDTO registerRequestBody) {
        var responseDTO = registerTheaterUseCase.execute(userEmail, registerRequestBody);

        var uri = URI.create("/theaters/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
