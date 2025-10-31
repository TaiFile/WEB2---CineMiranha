package br.ufscar.pooa.cinema_api.adapters.in.rest.controllers;

import br.ufscar.pooa.cinema_api.application.dtos.movie.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.movie.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterMovieUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final IRegisterMovieUseCase registerMovieUseCase;

    public MovieController(IRegisterMovieUseCase registerMovieUseCase) {
        this.registerMovieUseCase = registerMovieUseCase;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> register(@RequestBody RegisterMovieRequestDTO registerRequestBody) {
        var responseDTO = registerMovieUseCase.execute(registerRequestBody);

        URI uri = URI.create("/movies/" + responseDTO.getId());

        return ResponseEntity.created(uri).body(responseDTO);
    }
}
