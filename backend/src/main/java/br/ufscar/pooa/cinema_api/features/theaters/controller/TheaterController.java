package br.ufscar.pooa.cinema_api.features.theaters.controller;


import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.entities.Theater;
import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.movies.mapper.IMovieMapper;
import br.ufscar.pooa.cinema_api.features.movies.usecase.IFindAllMoviesByTheaterIdUseCase;
import br.ufscar.pooa.cinema_api.features.theaters.dto.RegisterTheaterRequestDTO;
import br.ufscar.pooa.cinema_api.features.theaters.dto.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.features.theaters.mapper.ITheaterMapper;
import br.ufscar.pooa.cinema_api.features.theaters.usecase.IGetTheatersByDistanceUseCase;
import br.ufscar.pooa.cinema_api.features.theaters.usecase.IRegisterTheaterUseCase;
import jakarta.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/theaters")
@RestController
public class TheaterController {

    private final IRegisterTheaterUseCase registerTheaterUseCase;
    private final IGetTheatersByDistanceUseCase getTheatersByDistanceUseCase;
    private final IFindAllMoviesByTheaterIdUseCase findAllMoviesByTheaterIdUseCase;
    private final ITheaterMapper theaterMapper;
    private final IMovieMapper movieMapper;

    public TheaterController(IRegisterTheaterUseCase registerTheaterUseCase,
        IGetTheatersByDistanceUseCase getTheatersByDistanceUseCase,
        IFindAllMoviesByTheaterIdUseCase findAllMoviesByTheaterIdUseCase,
        ITheaterMapper theaterMapper,
        IMovieMapper movieMapper) {
        this.registerTheaterUseCase = registerTheaterUseCase;
        this.getTheatersByDistanceUseCase = getTheatersByDistanceUseCase;
        this.findAllMoviesByTheaterIdUseCase = findAllMoviesByTheaterIdUseCase;
        this.theaterMapper = theaterMapper;
        this.movieMapper = movieMapper;
    }

    @PostMapping
    public ResponseEntity<TheaterResponseDTO> register(
        @PathParam("userEmail") String userEmail,
        @RequestBody RegisterTheaterRequestDTO registerRequestBody) {
        var responseDTO = registerTheaterUseCase.execute(userEmail, registerRequestBody);

        var uri = URI.create("/theaters/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping("/by-distance")
    public ResponseEntity<List<TheaterResponseDTO>> getTheatersByDistance(
        @RequestParam("latitude") Double latitude,
        @RequestParam("longitude") Double longitude) {
        List<Theater> theaters = getTheatersByDistanceUseCase.execute(latitude, longitude);
        List<TheaterResponseDTO> response = theaters.stream()
            .map(theaterMapper::toTheaterResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieResponseDTO>> findAllMoviesByTheaterId(@PathVariable Long id) {
        List<Movie> movies = findAllMoviesByTheaterIdUseCase.execute(id);
        List<MovieResponseDTO> response = movies.stream()
            .map(movieMapper::toMovieResponseDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
