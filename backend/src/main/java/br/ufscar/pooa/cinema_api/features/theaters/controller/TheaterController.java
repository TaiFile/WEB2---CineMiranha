package br.ufscar.pooa.cinema_api.features.theaters.controller;

import br.ufscar.pooa.cinema_api.features.movies.dto.MovieResponseDTO;
import br.ufscar.pooa.cinema_api.features.theaters.usecase.FindAllMoviesByTheaterIdUseCase;
import br.ufscar.pooa.cinema_api.features.sessions.dto.SessionResponseDTO;
import br.ufscar.pooa.cinema_api.features.theaters.usecase.FindAllSessionsByMovieIdAndTheaterIdUseCase;
import br.ufscar.pooa.cinema_api.features.theaters.dto.RegisterTheaterRequestDTO;
import br.ufscar.pooa.cinema_api.features.theaters.dto.TheaterResponseDTO;
import br.ufscar.pooa.cinema_api.features.theaters.usecase.GetTheatersByDistanceUseCase;
import br.ufscar.pooa.cinema_api.features.theaters.usecase.RegisterTheaterUseCase;
import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import java.util.List;
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

    private final RegisterTheaterUseCase registerTheaterUseCase;
    private final GetTheatersByDistanceUseCase getTheatersByDistanceUseCase;
    private final FindAllMoviesByTheaterIdUseCase findAllMoviesByTheaterIdUseCase;
    private final FindAllSessionsByMovieIdAndTheaterIdUseCase findAllSessionsByMovieIdAndTheaterIdUseCase;

    public TheaterController(RegisterTheaterUseCase registerTheaterUseCase,
        GetTheatersByDistanceUseCase getTheatersByDistanceUseCase,
        FindAllMoviesByTheaterIdUseCase findAllMoviesByTheaterIdUseCase,
        FindAllSessionsByMovieIdAndTheaterIdUseCase findAllSessionsByMovieIdAndTheaterIdUseCase) {
        this.registerTheaterUseCase = registerTheaterUseCase;
        this.getTheatersByDistanceUseCase = getTheatersByDistanceUseCase;
        this.findAllMoviesByTheaterIdUseCase = findAllMoviesByTheaterIdUseCase;
        this.findAllSessionsByMovieIdAndTheaterIdUseCase = findAllSessionsByMovieIdAndTheaterIdUseCase;
    }

    @Operation(summary = "Register a new theater")
    @PostMapping
    public ResponseEntity<TheaterResponseDTO> register(
        @RequestParam String userEmail,
        @RequestBody RegisterTheaterRequestDTO registerRequestBody) {
        var responseDTO = registerTheaterUseCase.execute(userEmail, registerRequestBody);

        var uri = URI.create("/theaters/" + responseDTO.getId());
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @Operation(summary = "Get all theaters sorted by distance from the user")
    @GetMapping("/by-distance")
    public ResponseEntity<List<TheaterResponseDTO>> getTheatersByDistance(
        @RequestParam("latitude") Double latitude,
        @RequestParam("longitude") Double longitude) {
        List<TheaterResponseDTO> response = getTheatersByDistanceUseCase.execute(latitude, longitude);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all movies for a specific theater by its ID")
    @GetMapping("/{id}/movies")
    public ResponseEntity<List<MovieResponseDTO>> findAllMoviesByTheaterId(@PathVariable Long id) {
        List<MovieResponseDTO> response = findAllMoviesByTheaterIdUseCase.execute(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all sessions for a specific movie at a specific theater")
    @GetMapping("/{theaterId}/movies/{movieId}/sessions")
    public ResponseEntity<List<SessionResponseDTO>> findAllSessionsByMovieIdAndTheaterId(
        @PathVariable Long theaterId,
        @PathVariable Long movieId) {
        List<SessionResponseDTO> response = findAllSessionsByMovieIdAndTheaterIdUseCase.execute(movieId, theaterId);
        return ResponseEntity.ok(response);
    }
}
