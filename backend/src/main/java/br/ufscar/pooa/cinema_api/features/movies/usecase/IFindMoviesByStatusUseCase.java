package br.ufscar.pooa.cinema_api.features.movies.usecase;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;
import br.ufscar.pooa.cinema_api.domain.enums.MovieStatus;

import java.util.List;

public interface IFindMoviesByStatusUseCase {
    List<Movie> execute(MovieStatus status);
}
