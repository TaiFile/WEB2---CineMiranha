package br.ufscar.pooa.cinema_api.features.movies.controller;

import br.ufscar.pooa.cinema_api.features.movies.dto.RegisterMovieRequestDTO;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.usecase.IMovieMapper;
import br.ufscar.pooa.cinema_api.features.movies.usecase.IFindAllMoviesUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.IFindMovieByIdUseCase;
import br.ufscar.pooa.cinema_api.features.movies.usecase.IRegisterMovieUseCase;
import br.ufscar.pooa.cinema_api.domain.entities.Movie;
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
