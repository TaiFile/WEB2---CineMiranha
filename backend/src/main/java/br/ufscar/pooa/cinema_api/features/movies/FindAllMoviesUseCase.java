package br.ufscar.pooa.cinema_api.features.movies;

import br.ufscar.pooa.cinema_api.features._shared.gateways.repository.IMovieRepository;
import br.ufscar.pooa.cinema_api.features.movies.IFindAllMoviesUseCase;
import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllMoviesUseCase implements IFindAllMoviesUseCase {

    private final IMovieRepository movieRepository;

    public FindAllMoviesUseCase(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> execute() {
        return movieRepository.findAll();
    }
}
