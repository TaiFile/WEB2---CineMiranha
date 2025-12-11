package br.ufscar.pooa.cinema_api.features.movies.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.enums.MovieStatus;
import br.ufscar.pooa.cinema_api.domain.repositories.movie.IMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindMoviesByStatusUseCase implements IFindMoviesByStatusUseCase {

    private final IMovieRepository movieRepository;

    public FindMoviesByStatusUseCase(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> execute(MovieStatus status) {
        return movieRepository.findByStatus(status);
    }
}
