package br.ufscar.pooa.cinema_api.features.movies;

import br.ufscar.pooa.cinema_api.domain.entities.Movie;

public interface IFindMovieByIdUseCase {
    Movie execute(Long id);
}
