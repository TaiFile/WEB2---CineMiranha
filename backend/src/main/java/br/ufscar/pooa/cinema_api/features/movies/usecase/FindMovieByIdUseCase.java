package br.ufscar.pooa.cinema_api.features.movies.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.repositories.movie.IMovieRepository;
import br.ufscar.pooa.cinema_api.features._shared.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindMovieByIdUseCase {

    private final IMovieRepository movieRepository;

    public FindMovieByIdUseCase(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie execute(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id.toString()));
    }
}

