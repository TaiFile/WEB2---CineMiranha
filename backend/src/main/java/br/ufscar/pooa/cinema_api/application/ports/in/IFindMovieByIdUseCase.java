package br.ufscar.pooa.cinema_api.application.ports.in;

import br.ufscar.pooa.cinema_api.domain.Movie;

public interface IFindMovieByIdUseCase {
    Movie execute(Long id);
}
