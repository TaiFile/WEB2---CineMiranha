package br.ufscar.pooa.cinema_api.application.usecases.movie;

import br.ufscar.pooa.cinema_api.application.exceptions.ResourceNotFoundException;
import br.ufscar.pooa.cinema_api.application.ports.in.IFindMovieByIdUseCase;
import br.ufscar.pooa.cinema_api.application.ports.out.repository.IMovieRepository;
import br.ufscar.pooa.cinema_api.domain.Movie;
import org.springframework.stereotype.Service;

@Service
public class FindMovieByIdUseCase implements IFindMovieByIdUseCase {

    private final IMovieRepository movieRepository;

    public FindMovieByIdUseCase(IMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie execute(Long id) {
        return movieRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Movie", "id", id.toString()));
    }
}
