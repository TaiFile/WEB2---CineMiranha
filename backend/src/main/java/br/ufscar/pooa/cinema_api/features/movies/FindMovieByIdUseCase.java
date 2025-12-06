package br.ufscar.pooa.cinema_api.features.movies;

import br.ufscar.pooa.cinema_api.features._shared.exceptions.ResourceNotFoundException;
import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IMovieRepository;
import br.ufscar.pooa.cinema_api.features.movies.IFindMovieByIdUseCase;
import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindMovieByIdUseCase implements IFindMovieByIdUseCase {

    private final IMovieRepository movieRepository;

    public FindMovieByIdUseCase(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie execute(Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id.toString()));
    }
}
