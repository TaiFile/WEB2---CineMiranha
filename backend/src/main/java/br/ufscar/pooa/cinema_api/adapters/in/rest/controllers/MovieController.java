package br.ufscar.pooa.cinema_api.adapters.in.rest.controllers;

import br.ufscar.pooa.cinema_api.application.dtos.movie.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.application.dtos.movie.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.application.mappers.IMovieMapper;
import br.ufscar.pooa.cinema_api.application.ports.in.IFindAllMoviesUseCase;
import br.ufscar.pooa.cinema_api.application.ports.in.IFindMovieByIdUseCase;
import br.ufscar.pooa.cinema_api.application.ports.in.IRegisterMovieUseCase;
import br.ufscar.pooa.cinema_api.domain.Movie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final IRegisterMovieUseCase registerMovieUseCase;
    private final IFindAllMoviesUseCase findAllMoviesUseCase;
    private final IFindMovieByIdUseCase findMovieByIdUseCase;
    private final IMovieMapper movieMapper;

    public MovieController(IRegisterMovieUseCase registerMovieUseCase, IFindAllMoviesUseCase findAllMoviesUseCase, IFindMovieByIdUseCase findMovieByIdUseCase, IMovieMapper movieMapper) {
        this.registerMovieUseCase = registerMovieUseCase;
        this.findAllMoviesUseCase = findAllMoviesUseCase;
        this.findMovieByIdUseCase = findMovieByIdUseCase;
        this.movieMapper = movieMapper;
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
}
