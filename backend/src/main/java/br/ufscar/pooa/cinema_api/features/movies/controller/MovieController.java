package br.ufscar.pooa.cinema_api.features.movies.controller;

import br.ufscar.pooa.cinema_api.features.movies.dto.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindAllMoviesUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindMovieByIdUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.RegisterMovieUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindAllSessionsByMovieIdUseCase;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final RegisterMovieUseCase registerMovieUseCase;
    private final FindAllMoviesUseCase findAllMoviesUseCase;
    private final FindMovieByIdUseCase findMovieByIdUseCase;
    private final FindAllSessionsByMovieIdUseCase findAllSessionsByMovieIdUseCase;

    public MovieController(RegisterMovieUseCase registerMovieUseCase, FindAllMoviesUseCase findAllMoviesUseCase, FindMovieByIdUseCase findMovieByIdUseCase,
        FindAllSessionsByMovieIdUseCase findAllSessionsByMovieIdUseCase) {
        this.registerMovieUseCase = registerMovieUseCase;
        this.findAllMoviesUseCase = findAllMoviesUseCase;
        this.findMovieByIdUseCase = findMovieByIdUseCase;
        this.findAllSessionsByMovieIdUseCase = findAllSessionsByMovieIdUseCase;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> register(@RequestBody RegisterMovieRequestDTO registerRequestBody) {
        var responseDTO = registerMovieUseCase.execute(registerRequestBody);
        URI uri = URI.create("/movies/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAll() {
        List<MovieResponseDTO> response = findAllMoviesUseCase.execute();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findById(@PathVariable Long id) {
        MovieResponseDTO response = findMovieByIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/sessions")
    public ResponseEntity<List<SessionResponseDTO>> findAllSessionsByMovieId(@PathVariable Long id) {
        List<SessionResponseDTO> response = findAllSessionsByMovieIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }
}
