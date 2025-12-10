package br.ufscar.pooa.cinema_api.features.movies.controller;

import br.ufscar.pooa.cinema_api.domain.entities.Session;
import br.ufscar.pooa.cinema_api.features.movies.dto.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.mapper.IMovieMapper;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindAllMoviesUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindMovieByIdUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.RegisterMovieUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.FindAllSessionsByMovieIdUseCase;
import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.features.sessions.mapper.ISessionMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final RegisterMovieUseCase registerMovieUseCase;
    private final FindAllMoviesUseCase findAllMoviesUseCase;
    private final FindMovieByIdUseCase findMovieByIdUseCase;
    private final FindAllSessionsByMovieIdUseCase findAllSessionsByMovieIdUseCase;
    private final IMovieMapper movieMapper;
    private final ISessionMapper sessionMapper;

    public MovieController(RegisterMovieUseCase registerMovieUseCase, FindAllMoviesUseCase findAllMoviesUseCase, FindMovieByIdUseCase findMovieByIdUseCase,
        FindAllSessionsByMovieIdUseCase findAllSessionsByMovieIdUseCase, IMovieMapper movieMapper, ISessionMapper sessionMapper) {
        this.registerMovieUseCase = registerMovieUseCase;
        this.findAllMoviesUseCase = findAllMoviesUseCase;
        this.findMovieByIdUseCase = findMovieByIdUseCase;
        this.findAllSessionsByMovieIdUseCase = findAllSessionsByMovieIdUseCase;
        this.movieMapper = movieMapper;
        this.sessionMapper = sessionMapper;
    }

    @PostMapping
    public ResponseEntity<MovieResponseDTO> register(@RequestBody RegisterMovieRequestDTO registerRequestBody) {
        var responseDTO = registerMovieUseCase.execute(registerRequestBody);
        URI uri = URI.create("/movies/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> findAll() {
        List<Movie> movies = findAllMoviesUseCase.execute();
        List<MovieResponseDTO> response = movies.stream()
                .map(movieMapper::toMovieResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponseDTO> findById(@PathVariable Long id) {
        Movie movie = findMovieByIdUseCase.execute(id);
        return ResponseEntity.ok(movieMapper.toMovieResponseDTO(movie));
    }

    @GetMapping("/{id}/sessions")
    public ResponseEntity<List<SessionResponseDTO>> findAllSessionsByMovieId(@PathVariable Long id) {
        List<Session> sessions = findAllSessionsByMovieIdUseCase.execute(id);
        List<SessionResponseDTO> response = sessions.stream()
            .map(sessionMapper::toSessionResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
