package br.ufscar.pooa.cinema_api.features.movies.controller;

import br.ufscar.pooa.cinema_api.domain.enums.MovieStatus;
import br.ufscar.pooa.cinema_api.features.movies.dto.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.features.movies.mapper.IMovieMapper;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindAllMoviesUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindMovieByIdUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindMoviesByStatusUseCase;
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
    private final FindMoviesByStatusUseCase findMoviesByStatusUseCase;
    private final FindAllSessionsByMovieIdUseCase findAllSessionsByMovieIdUseCase;
    private final IMovieMapper movieMapper;

    public MovieController(RegisterMovieUseCase registerMovieUseCase, 
        FindAllMoviesUseCase findAllMoviesUseCase, 
        FindMovieByIdUseCase findMovieByIdUseCase,
        FindMoviesByStatusUseCase findMoviesByStatusUseCase, 
        FindAllSessionsByMovieIdUseCase findAllSessionsByMovieIdUseCase,
        IMovieMapper movieMapper) {
        this.registerMovieUseCase = registerMovieUseCase;
        this.findAllMoviesUseCase = findAllMoviesUseCase;
        this.findMovieByIdUseCase = findMovieByIdUseCase;
        this.findMoviesByStatusUseCase = findMoviesByStatusUseCase;
        this.findAllSessionsByMovieIdUseCase = findAllSessionsByMovieIdUseCase;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> register(@RequestBody RegisterMovieRequestDTO registerRequestBody) {
        var responseDTO = registerMovieUseCase.execute(registerRequestBody);
        URI uri = URI.create("/movies/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAll(@RequestParam(required = false) MovieStatus status) {
        List<MovieResponseDTO> movies;
        if (status != null) {
            movies = findMoviesByStatusUseCase.execute(status).stream().map(movieMapper::toMovieResponseDTO).toList();
        } else {
            movies = findAllMoviesUseCase.execute();
        }


        return ResponseEntity.ok(movies);
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
