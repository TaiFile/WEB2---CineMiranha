package br.ufscar.pooa.cinema_api.features.movies.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.repositories.movie.IMovieRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FindAllMoviesUseCase {

    private final IMovieRepository movieRepository;

    public FindAllMoviesUseCase(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> execute() {
        return movieRepository.findAll();
    }
}

