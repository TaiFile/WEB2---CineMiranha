package br.ufscar.pooa.cinema_api.application.usecases.movie;

import br.ufscar.pooa.cinema_api.application.ports.in.IFindAllMoviesUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IMovieRepository;
import br.ufscar.pooa.cinema_api.domain.Movie;
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
